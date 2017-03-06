package snake.model.state;

import snake.model.BodyPart;
import snake.model.Direction;
import snake.model.Field;
import snake.model.Snake;

/**
 * @author afeher
 */
public class Eat extends State {

    @Override
    public State proceed(Field[][] court, Snake snake, Direction direction) {
        BodyPart head = snake.getHead();
        Field eaten = court[head.getX()][head.getY()];
        eaten.setTerrain(Field.Terrain.PLAIN);
        // scores?
        snake.append(1);
        return new Treat();
    }

}
