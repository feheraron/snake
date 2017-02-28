package snake.model.state;

import snake.model.Field;
import snake.model.Snake;

/**
 * @author afeher
 */
public abstract class State {
    
    public boolean isInProgress() {
        return true;
    }

    public abstract State proceed(Field[][] court, Snake snake);

    public int getTimeout() {
        return 0;
    }

}
