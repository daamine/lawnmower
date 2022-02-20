package com.project.lawnmower;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class LawnMowerTest {

	@Test
	public void testMowerSeriesOfInstructions1()
	{
		LawnDimension lawnDimension = new LawnDimension(6, 6);
		MowerPosition mowerPosition = new MowerPosition(1, 2, CardinalDirection.NORTH);
		List<MowerInstruction> mowerInstructions = Arrays.asList(new MowerInstruction [] {MowerInstruction.LEFT, MowerInstruction.FORWARD, MowerInstruction.LEFT, 
				MowerInstruction.FORWARD, MowerInstruction.LEFT, MowerInstruction.FORWARD, MowerInstruction.LEFT, MowerInstruction.FORWARD, MowerInstruction.FORWARD}); 
		LawnMower lawnMower = new LawnMower(1, mowerPosition,lawnDimension);
		for (MowerInstruction mowerInstruction : mowerInstructions) {
			lawnMower.executeInstruction(mowerInstruction);
		}
		MowerPosition expectedMowerPosition = new MowerPosition(1, 3, CardinalDirection.NORTH);
		assertEquals(expectedMowerPosition, lawnMower.getPosition());
	}

	@Test
	public void testMowerSeriesOfInstructions2()
	{
		LawnDimension lawnDimension = new LawnDimension(6, 5);
		MowerPosition mowerPosition = new MowerPosition(3, 3, CardinalDirection.SOUTH);
		List<MowerInstruction> mowerInstructions = Arrays.asList(new MowerInstruction [] {MowerInstruction.FORWARD, MowerInstruction.RIGHT, MowerInstruction.FORWARD, 
				MowerInstruction.LEFT, MowerInstruction.LEFT, MowerInstruction.FORWARD, MowerInstruction.RIGHT, MowerInstruction.FORWARD, MowerInstruction.FORWARD}); 
		LawnMower lawnMower = new LawnMower(1, mowerPosition,lawnDimension);
		for (MowerInstruction mowerInstruction : mowerInstructions) {
			lawnMower.executeInstruction(mowerInstruction);
		}
		MowerPosition expectedMowerPosition = new MowerPosition(3, 0, CardinalDirection.SOUTH);
		assertEquals(expectedMowerPosition, lawnMower.getPosition());
	}
}
