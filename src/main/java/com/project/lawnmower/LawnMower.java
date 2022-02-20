package com.project.lawnmower;

import org.apache.log4j.Logger;

/**
 * Class responsible of tracking and updating the position and orientation of a Mower
 * @author adaoud
 *
 */
public class LawnMower {

	private static final Logger LOG = Logger.getLogger("com.project.lawnmower");

	private MowerPosition position; 
	private final LawnDimension lawnDimension;
	private final Integer id;

	public LawnMower(Integer id, MowerPosition position, LawnDimension lawnDimension) {
		this.id = id;
		this.position = position;
		this.lawnDimension = lawnDimension;
	}

	public MowerPosition getPosition() {
		return position;
	}

	public Integer getID() {
		return id;
	}

   public MowerPosition executeInstruction(MowerInstruction instruction) {
		switch (instruction) {
		case FORWARD:
			position.forward(lawnDimension);
			break;
		case LEFT:
			position.left();
			break;
		case RIGHT:
			position.right();
			break;
		default:
			LOG.error("Unsupported mower instruction " + instruction.name());
			break;
		}
		return position;
	}
}
