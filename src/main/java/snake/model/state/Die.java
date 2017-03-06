package snake.model.state;

import snake.model.Direction;
import snake.model.Field;
import snake.model.Snake;

/**
 * @author afeher
 */
public class Die extends State {

    @Override
    public boolean isInProgress() {
        return false;
    }
    
    @Override
    public boolean isFastForward() {
	return false;
    }

    @Override
    public State proceed(Field[][] court, Snake snake, Direction direction) {
        throw new IllegalStateException("Final state");
    }

}
