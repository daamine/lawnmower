package com.project.lawnmower;

import java.util.List;
import java.util.Map;

/**
 * Class used to store the loaded mower configuration from the input file.
 * @author adaoud
 *
 */
public class LawnConfiguration {
	private final LawnDimension dimension;
	private final List<MowerPosition> initialMowersPosition;
	private final Map<Integer, List<MowerInstruction>> mowersInstructions;

	public LawnConfiguration(LawnDimension dimension, List<MowerPosition> initialMowersPosition,
			Map<Integer, List<MowerInstruction>> mowersInstructions) {
		this.dimension = dimension;
		this.initialMowersPosition = initialMowersPosition;
		this.mowersInstructions = mowersInstructions;
	}

	public LawnDimension getLawnDimension() {
		return dimension;
	}

	public List<MowerPosition> getInitialMowersPosition(){
		return initialMowersPosition;
	}

	public Map<Integer, List<MowerInstruction>> getLawnMowersInstructions() {
		return this.mowersInstructions;
	}
}
