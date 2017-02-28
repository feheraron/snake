package snake.model;

public enum Direction {

    UP, RIGHT, DOWN, LEFT;

    private Direction shiftClockwise(int shiftValue) {
        return values()[(ordinal() + shiftValue) % values().length];
    }

    public Direction getRelativeRight() {
        return shiftClockwise(1);
    }

    public Direction getRelativeLeft() {
        return shiftClockwise(3);
    }
}
