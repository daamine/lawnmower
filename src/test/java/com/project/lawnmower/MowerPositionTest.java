package com.project.lawnmower;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MowerPositionTest {

	@Test
	public void testMowerPosition()
	{
		LawnDimension lawnDimension = new LawnDimension(6, 6);
		MowerPosition mowerPosition = new MowerPosition(1, 2, CardinalDirection.NORTH);
		mowerPosition.left();
		MowerPosition expectedMowerPosition = new MowerPosition(1, 2, CardinalDirection.WEST);

		mowerPosition.left();
		expectedMowerPosition = new MowerPosition(1, 2, CardinalDirection.SOUTH);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.left();
		expectedMowerPosition = new MowerPosition(1, 2, CardinalDirection.EAST);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(2, 2, CardinalDirection.EAST);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

	@Test
	public void testMowerUpperRightCornerPosition()
	{
		LawnDimension lawnDimension = new LawnDimension(3, 4);
		MowerPosition mowerPosition = new MowerPosition(2, 3, CardinalDirection.NORTH);
		mowerPosition.forward(lawnDimension);
		MowerPosition expectedMowerPosition = new MowerPosition(2, 3, CardinalDirection.NORTH);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.right();
		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(2, 3, CardinalDirection.EAST);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

	@Test
	public void testMowerUpperLeftCornerPosition()
	{
		LawnDimension lawnDimension = new LawnDimension(3, 4);
		MowerPosition mowerPosition = new MowerPosition(0, 3, CardinalDirection.NORTH);
		mowerPosition.forward(lawnDimension);
		MowerPosition expectedMowerPosition = new MowerPosition(0, 3, CardinalDirection.NORTH);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.left();
		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(0, 3, CardinalDirection.WEST);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

	@Test
	public void testMowerDownLeftCornerPosition()
	{
		LawnDimension lawnDimension = new LawnDimension(3, 4);
		MowerPosition mowerPosition = new MowerPosition(0, 0, CardinalDirection.SOUTH);
		mowerPosition.forward(lawnDimension);
		MowerPosition expectedMowerPosition = new MowerPosition(0, 0, CardinalDirection.SOUTH);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.right();
		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(0, 0, CardinalDirection.WEST);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

	@Test
	public void testMowerDownRightCornerPosition()
	{
		LawnDimension lawnDimension = new LawnDimension(3, 4);
		MowerPosition mowerPosition = new MowerPosition(2, 0, CardinalDirection.SOUTH);
		mowerPosition.forward(lawnDimension);
		MowerPosition expectedMowerPosition = new MowerPosition(2, 0, CardinalDirection.SOUTH);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.left();
		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(2, 0, CardinalDirection.EAST);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

	@Test
	public void testMowerTurnRightFourConsecutiveTimes()
	{
		MowerPosition mowerPosition = new MowerPosition(1, 1, CardinalDirection.SOUTH);
		for (int i= 0; i<4; i++) { 
			mowerPosition.right();
		}
		MowerPosition expectedMowerPosition = new MowerPosition(1, 1, CardinalDirection.SOUTH);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

	@Test
	public void testMowerTurnLeftFourConsecutiveTimes()
	{
		MowerPosition mowerPosition = new MowerPosition(1, 1, CardinalDirection.SOUTH);
		for (int i= 0; i<4; i++) { 
			mowerPosition.left();
		}
		MowerPosition expectedMowerPosition = new MowerPosition(1, 1, CardinalDirection.SOUTH);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

	@Test
	public void testSingleCellLawnMower()
	{
		LawnDimension lawnDimension = new LawnDimension(1, 1);
		MowerPosition mowerPosition = new MowerPosition(0, 0, CardinalDirection.NORTH);
		mowerPosition.forward(lawnDimension);
		MowerPosition expectedMowerPosition = new MowerPosition(0, 0, CardinalDirection.NORTH);

		mowerPosition.left();
		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(0, 0, CardinalDirection.WEST);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.left();
		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(0, 0, CardinalDirection.SOUTH);
		assertEquals(expectedMowerPosition, mowerPosition);

		mowerPosition.left();
		mowerPosition.forward(lawnDimension);
		expectedMowerPosition = new MowerPosition(0, 0, CardinalDirection.EAST);
		assertEquals(expectedMowerPosition, mowerPosition);
	}

}