package snake.model;

/**
 * @author afeher
 */
public class BodyPart {

    private int x = 0;
    private int y = 0;

    BodyPart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public BodyPart copy() {
        return new BodyPart(x, y);
    }
    
}
