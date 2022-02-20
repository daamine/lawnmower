package com.project.lawnmower;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to move mowers in a sequential way
 * 
 * @author adaoud
 *
 */
public class LawnMowerInstructor {

	private final Map<LawnMower, List<MowerInstruction>> lawnMowerInstructionMap = new HashMap<LawnMower, List<MowerInstruction>>();

	public LawnMowerInstructor(List<LawnMower> lawnMowers,  Map<Integer, List<MowerInstruction>> lawnMowersInstructions) {
		lawnMowers.forEach(
				lawnMower -> { 
					List<MowerInstruction> instructions = lawnMowersInstructions.get(lawnMower.getID());
					lawnMowerInstructionMap.put(lawnMower, instructions);

				}); 
	}

	/**
	 * Execute instructions for each mower. 
	 * The next mower moves only after the first one executes all its instructions.  
	 */
	public void execute() {
		lawnMowerInstructionMap.forEach(
				(lawnMower, instructions) -> instructions.forEach(lawnMower::executeInstruction));
	}
}