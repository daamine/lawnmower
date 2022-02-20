package com.project.lawnmower.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.project.lawnmower.CardinalDirection;
import com.project.lawnmower.LawnConfiguration;
import com.project.lawnmower.LawnDimension;
import com.project.lawnmower.MowerPosition;


public class MowerInstructionReaderTest {

	@Test
	public void testEmptyFile()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("empty_file.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (IllegalArgumentException | IOException e) {
			assertEquals("Empty file " + lanwMowerInputFile.getAbsolutePath(), e.getMessage());
		}
	}

	@Test
	public void testFileWithWrongLawnDimension()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("wrong_dimension.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (IllegalArgumentException | IOException e) {
			assertEquals("Wrong coordinates format 5 5 6 in file " + lanwMowerInputFile.getAbsolutePath(), e.getMessage());
		}
	}

	@Test
	public void testFileWithNegativeLawnDimension()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("negative_lawn_dimension.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (IllegalArgumentException | IOException e) {
			assertEquals("Negative upper-right lawn coordinates 5 -1 in file " + lanwMowerInputFile.getAbsolutePath(), e.getMessage());
		}
	}

	@Test
	public void testFileWithNoInitialPositionOrInstructions()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("no_initial_position_or_instructions.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (IllegalArgumentException | IOException e) {
			assertEquals("No mower configuration found in file " + lanwMowerInputFile.getAbsolutePath(), e.getMessage());
		}
	}

	@Test
	public void testFileWithNoMowerInstructions()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("no_mower_instructions.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (IllegalArgumentException | IOException e) {
			assertEquals("Missing mower instruction line in file " + lanwMowerInputFile.getAbsolutePath(), e.getMessage());
		}
	}

	@Test
	public void testFileWithNegativeMowerInitialPosition()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("negative_mower_initial_position.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (IllegalArgumentException | IOException e) {
			assertEquals("Negative mower initial position coordinates -1 3 E in file " + lanwMowerInputFile.getAbsolutePath(), e.getMessage());
		}
	}

	@Test
	public void testMowerCoordinateNotInteger()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("string_mower_coordinate.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (NumberFormatException e) {
			assertEquals("For input string: \"x\"", e.getMessage());
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testFileWithWrongMowerInstruction()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("wrong_mower_instruction.txt").getFile());
		try {
			MowerInstructionReader.read(lanwMowerInputFile);
			fail();
		} catch (IllegalArgumentException | IOException e) {
			assertEquals("Unsupported mower instruction S in file " + lanwMowerInputFile.getAbsolutePath(), e.getMessage());
		}
	}

	@Test
	public void testFileWithGoodFormat()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File lanwMowerInputFile = new File(classLoader.getResource("good_format.txt").getFile());
		try {
			LawnConfiguration config = MowerInstructionReader.read(lanwMowerInputFile);
			LawnDimension expectedDimension = new LawnDimension(6, 9);
			MowerPosition expectedMower1Position = new MowerPosition(1, 2, CardinalDirection.SOUTH);
			MowerPosition expectedMower2Position = new MowerPosition(3, 0, CardinalDirection.WEST);
			assertEquals(expectedDimension, config.getLawnDimension());
			assertEquals(expectedMower1Position, config.getInitialMowersPosition().get(0));
			assertEquals(expectedMower2Position,  config.getInitialMowersPosition().get(1));
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
