package snake.model.state;

import snake.model.Field;
import snake.model.Snake;

/**
 * @author afeher
 */
public class Wait extends State {

    @Override
    public int getTimeout() {
        return 1000;
    }

    @Override
    public State proceed(Field[][] court, Snake snake) {
        return new Move();
    }

}
