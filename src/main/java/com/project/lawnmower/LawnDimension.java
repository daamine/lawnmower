package com.project.lawnmower;

/**
 * Class containing the lawn dimensions (width and length)
 * 
 * @author adaoud
 *
 */
public class LawnDimension {
	private final int width; // upper right corner x coordinate + 1
	private final int length; // upper right corner y coordinate + 1
	public LawnDimension(final int width, final int length) {
		this.width = width;
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public boolean containsPosition(MowerPosition initialPosition) {
		return initialPosition.getX() < width && initialPosition.getY() < length;
	}

	@Override
	public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof LawnDimension)) {
            return false;
        }
        LawnDimension dimension = (LawnDimension) o;
        return dimension.getWidth() == width && dimension.getLength() == length;
	}
}