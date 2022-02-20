package com.project.lawnmower.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.project.lawnmower.CardinalDirection;
import com.project.lawnmower.LawnConfiguration;
import com.project.lawnmower.LawnDimension;
import com.project.lawnmower.LawnMower;
import com.project.lawnmower.MowerInstruction;
import com.project.lawnmower.MowerPosition;

/**
 * Class used to read and validate the lawn configuration and mower instructions from the input file
 * @author adaoud
 *
 */
public class MowerInstructionReader {
	private static final Logger LOG = Logger.getLogger("com.project.lawnmower.reader");

	public static LawnConfiguration read(File lanwMowerInputFile) throws FileNotFoundException, IOException, IllegalArgumentException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("MowerInstructionReader: read file " + lanwMowerInputFile.getAbsolutePath());
		}
		try(BufferedReader br = new BufferedReader(new FileReader(lanwMowerInputFile))) {
			StringBuilder sb = new StringBuilder();

			// The first line is the coordinates of the upper-right corner of the lawn.
			String line = br.readLine();
			LawnDimension lawnDimension;
			if (line != null) {
				line = line.trim();
				String[] coordinatesStr = line.split(" ");
				if (coordinatesStr.length != 2) {
					throw new IllegalArgumentException("Wrong coordinates format " + line + " in file " + lanwMowerInputFile.getAbsolutePath());
				}
				Integer[] coordinates = Arrays.stream(coordinatesStr).map(Integer::parseInt).toArray(Integer[]::new);
				int width = coordinates[0].intValue() + 1;
				int length = coordinates[1].intValue() + 1;
				if (width < 1 || length <1) {
					throw new IllegalArgumentException("Negative upper-right lawn coordinates " + line + " in file " + lanwMowerInputFile.getAbsolutePath());
				}
				lawnDimension = new LawnDimension(width, length);
			} else {
				throw new IllegalArgumentException("Empty file " + lanwMowerInputFile.getAbsolutePath());
			}

			// the new two lines for each mower are:
			// 1- the initial position and orientation
			// 2- The instructions to be processed by the mower
			line = br.readLine();
			List<MowerPosition> initialMowersPosition = new ArrayList<MowerPosition>();
			Map<Integer, List<MowerInstruction>> mowersInstructions = new LinkedHashMap<Integer, List<MowerInstruction>>();
			Integer mowerId = 0; 
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = line.trim();
				String[] moverPositionAndOrientation = line.split(" ");
				if (moverPositionAndOrientation.length != 3) {
					throw new IllegalArgumentException("Wrong mower position and orientation format " + line + " in file " + lanwMowerInputFile.getAbsolutePath());
				}
				int mowerXPosition = Integer.parseInt(moverPositionAndOrientation[0]);
				int mowerYPosition = Integer.parseInt(moverPositionAndOrientation[1]);
				if (mowerXPosition < 0 || mowerYPosition <0) {
					throw new IllegalArgumentException("Negative mower initial position coordinates " + line + " in file " + lanwMowerInputFile.getAbsolutePath());
				}
				String mowerDirection = moverPositionAndOrientation[2].trim();
				if (mowerDirection.length() != 1) {
					throw new IllegalArgumentException("Wrong mower orientation format " + line + " in file " + lanwMowerInputFile.getAbsolutePath());
				}
				CardinalDirection cardinalDirection = transformToCardinalDirection(moverPositionAndOrientation[2].trim().charAt(0), lanwMowerInputFile);
				MowerPosition initialMowerPosition = new MowerPosition(mowerXPosition, mowerYPosition, cardinalDirection);
				initialMowersPosition.add(initialMowerPosition);

				line = br.readLine();
				List<MowerInstruction> mowerInstructions;
				if (line != null && !line.isEmpty()) {
					line = line.trim();
					mowerInstructions = createInstructionsFromInputLine(line, lanwMowerInputFile);
				} else {
					throw new IllegalArgumentException("Missing mower instruction line in file " + lanwMowerInputFile.getAbsolutePath());
				}
				mowersInstructions.put(mowerId, mowerInstructions);
				mowerId++;
				line = br.readLine();
			}
			if (initialMowersPosition.isEmpty()) {
				throw new IllegalArgumentException("No mower configuration found in file " + lanwMowerInputFile.getAbsolutePath());
			}
			return new LawnConfiguration(lawnDimension, initialMowersPosition, mowersInstructions);
		}
	}

	private static List<MowerInstruction> createInstructionsFromInputLine(String line, File lanwMowerInputFile) {
		List<MowerInstruction> mowerInstructions = new ArrayList<MowerInstruction>();
		for(int i = 0; i < line.length(); i++) {
			char instruction = line.charAt(i);
			switch(instruction) {
			case 'L':
				mowerInstructions.add(MowerInstruction.LEFT);
				break;
			case 'R':
				mowerInstructions.add(MowerInstruction.RIGHT);
				break;
			case 'F':
				mowerInstructions.add(MowerInstruction.FORWARD);
				break;
			default:
				throw new IllegalArgumentException("Unsupported mower instruction " + instruction + " in file " + lanwMowerInputFile.getAbsolutePath());
			}
		}
		return mowerInstructions;
	}

	private static CardinalDirection transformToCardinalDirection(char cardinalCharacter, File lanwMowerInputFile) {
		switch (cardinalCharacter) {
		case 'S':
			return CardinalDirection.SOUTH;
		case 'N':
			return CardinalDirection.NORTH;
		case 'W':
			return CardinalDirection.WEST;
		case 'E':
			return CardinalDirection.EAST;
		default: 
			throw new IllegalArgumentException("Wrong mower orientation " + cardinalCharacter + " in file " + lanwMowerInputFile.getAbsolutePath());
		}
	}

	public static List<LawnMower> createLawnMowersFromConfiguration(LawnConfiguration configuration) {
		List<MowerPosition> initialMowerPositions = configuration.getInitialMowersPosition();
		Iterator<Integer> mowerIdsIterator = configuration.getLawnMowersInstructions().keySet().iterator();
		LawnDimension lawnDimension = configuration.getLawnDimension();
		List<LawnMower> lawnMowers = new ArrayList<LawnMower>();
		for(int i = 0; i< initialMowerPositions.size(); i++) {
			MowerPosition initialPosition = initialMowerPositions.get(i);
			Integer mowerId = mowerIdsIterator.next();
			LawnMower mower;
			if (lawnDimension.containsPosition(initialPosition)) {
				mower = new LawnMower(mowerId, initialPosition, lawnDimension);
			} else {
				throw new IllegalArgumentException("Mower initial position not inside the lawn matrix");
			}
			lawnMowers.add(mower);
		}
		return lawnMowers;
	}
}