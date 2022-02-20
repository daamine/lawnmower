package com.project.lawnmower;

import org.apache.log4j.Logger;

/**
 * class for handling the position and orientation of the mower
 * @author adaoud
 *
 */
public class MowerPosition {

	private static final Logger LOG = Logger.getLogger("com.project.lawnmower.position");

	private int x;
	private int y;
	private CardinalDirection orientation;
	public MowerPosition(int x,
			int y, CardinalDirection orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public void forward(LawnDimension dimension) {
		switch (orientation) {
		case NORTH:
			if (this.y < dimension.getLength() - 1) {
				this.y += 1;
			}
			break;
		case WEST:
			if (this.x != 0) {
				this.x -= 1;
			}
			break;
		case SOUTH:
			if (this.y != 0) {
				this.y -= 1;
			}
			break;
		case EAST:
			if (this.x < dimension.getWidth() - 1) {
				this.x += 1;
			}
			break;
		default:
			break;
		}				
	}

	public void left() {
		switch (orientation) {
		case NORTH:
			this.orientation = CardinalDirection.WEST;
			break;
		case WEST:
			this.orientation = CardinalDirection.SOUTH;
			break;
		case SOUTH:
			this.orientation = CardinalDirection.EAST;
			break;
		case EAST:
			this.orientation = CardinalDirection.NORTH;
			break;
		default:
			LOG.error("left: unkown cardinal orientation");
			break;
		}			
	}

	public void right() {
		switch (orientation) {
		case NORTH:
			this.orientation = CardinalDirection.EAST;
			break;
		case EAST:
			this.orientation = CardinalDirection.SOUTH;
			break;
		case SOUTH:
			this.orientation = CardinalDirection.WEST;
			break;
		case WEST:
			this.orientation = CardinalDirection.NORTH;
			break;
		default:
			LOG.error("right: unkown cardinal orientation");
			break;
		}			
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public CardinalDirection getOrientation() {
		return orientation;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof MowerPosition)) {
			return false;
		}
		MowerPosition position = (MowerPosition) o;
		return position.getX() == x && position.getY() == y && position.getOrientation() == orientation;
	}

	@Override
	public String toString () {
		StringBuilder str = new StringBuilder();
		str.append(x).append(" ");
		str.append(y).append(" ");
		str.append(orientation.name().charAt(0));
		return str.toString();
	}
}
