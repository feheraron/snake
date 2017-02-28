package snake.model.state;

import snake.model.BodyPart;
import snake.model.Field;
import snake.model.SelfBiteException;
import snake.model.Snake;

/**
 * @author afeher
 */
public class Move extends State {

    @Override
    public State proceed(Field[][] court, Snake snake) {
        try {
            snake.move();
            BodyPart head = snake.getHead();
            Field target = court[head.getX()][head.getY()];
            return getNextState(target);
        } catch (SelfBiteException | ArrayIndexOutOfBoundsException e) {
            return new Die();
        }
    }

    private State getNextState(Field target) throws RuntimeException {
        State next;
        switch (target.getTerrain()) {
            case PLAIN:
                next = new Wait();
                break;
            case FOOD:
                next = new Eat();
                break;
            case OBSTACLE:
                next = new Die();
                break;
            default:
                throw new RuntimeException("Unsupported terrain: " + target.getTerrain());
        }
        return next;
    }
    
}
