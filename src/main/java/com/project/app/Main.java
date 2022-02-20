package com.project.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

import com.project.lawnmower.LawnConfiguration;
import com.project.lawnmower.LawnMower;
import com.project.lawnmower.LawnMowerInstructor;
import com.project.lawnmower.reader.MowerInstructionReader;


public class Main {

	private static final Logger LOG = Logger.getLogger("com.project.main");

	/**
	 * Main program entry point
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		if (args.length == 0 || args[0].trim().isEmpty()) {
			LOG.error("The path to mower instructions file should be specified!");
			return;
		} 
		final File lanwMowerInputFile = new File(args[0]);
		final LawnConfiguration configuration = MowerInstructionReader.read(lanwMowerInputFile);
		final List<LawnMower> lawnMowers = MowerInstructionReader.createLawnMowersFromConfiguration(configuration);
		LawnMowerInstructor lawnMowerInstructor = new LawnMowerInstructor(lawnMowers, configuration.getLawnMowersInstructions());
		lawnMowerInstructor.execute();
		for (final LawnMower lawnMower : lawnMowers) {
			LOG.info(lawnMower.getPosition());
		}
	}
}
