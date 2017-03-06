package snake.model;

public enum Direction {

    UP, RIGHT, DOWN, LEFT;

    private Direction shiftClockwise(int shiftValue) {
	return values()[(ordinal() + shiftValue) % values().length];
    }

    public Direction opposite() {
	return shiftClockwise(2);
    }

    public boolean isOpposite(Direction direction) {
	return opposite().equals(direction);
    }

}
