package snake.model.state;

import snake.model.Direction;
import snake.model.Field;
import snake.model.Snake;

/**
 * @author afeher
 */
public class Wait extends State {
    
    @Override
    public boolean isFastForward() {
	return false;
    }

    @Override
    public State proceed(Field[][] court, Snake snake, Direction direction) {
        return new Move();
    }

}
