package com.project.lawnmower;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Unit test for simple LawnMowerInstructor.
 */
public class LawnMowerInstructorTest 
{

    @Test
    public void testWith2Mowers()
    {
    	LawnDimension lawnDimension = new LawnDimension(6, 6);
    	MowerPosition mower1Position = new MowerPosition(1, 2, CardinalDirection.NORTH);
    	List<MowerInstruction> mower1Instructions = Arrays.asList(new MowerInstruction [] {MowerInstruction.LEFT, MowerInstruction.FORWARD, MowerInstruction.LEFT, 
    		MowerInstruction.FORWARD, MowerInstruction.LEFT, MowerInstruction.FORWARD, MowerInstruction.LEFT, MowerInstruction.FORWARD, MowerInstruction.FORWARD}); 
    	List<MowerInstruction> mower2Instructions = Arrays.asList(new MowerInstruction [] {MowerInstruction.FORWARD, MowerInstruction.FORWARD, MowerInstruction.RIGHT,
    			MowerInstruction.FORWARD, MowerInstruction.FORWARD, MowerInstruction.RIGHT, MowerInstruction.FORWARD, MowerInstruction.RIGHT, MowerInstruction.RIGHT,
    			MowerInstruction.FORWARD}); 
    	MowerPosition mower2Position = new MowerPosition(3, 3, CardinalDirection.EAST);
    	List<LawnMower> lawnMowers = new ArrayList<LawnMower>(); 
    	LawnMower lawnMower1 = new LawnMower(1, mower1Position,lawnDimension);
    	lawnMowers.add(lawnMower1);
    	LawnMower lawnMower2 = new LawnMower(2, mower2Position,lawnDimension);
    	lawnMowers.add(lawnMower2);
    	
    	Map<Integer, List<MowerInstruction>> lawnMowersInstructions = new LinkedHashMap<Integer, List<MowerInstruction>>();
    	lawnMowersInstructions.put(1, mower1Instructions);
    	lawnMowersInstructions.put(2, mower2Instructions);

    	LawnMowerInstructor lawnMowerInstructor = new LawnMowerInstructor(lawnMowers, lawnMowersInstructions);
    	lawnMowerInstructor.execute();
    	
    	MowerPosition expectedMower1Position = new MowerPosition(1, 3, CardinalDirection.NORTH);
    	MowerPosition expectedMower2Position = new MowerPosition(5, 1, CardinalDirection.EAST);

        assertEquals(expectedMower1Position, lawnMower1.getPosition());
        assertEquals(expectedMower2Position, lawnMower2.getPosition());
    }
}
