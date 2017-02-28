package snake.model.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import snake.model.BodyPart;
import snake.model.Field;
import snake.model.Snake;

/**
 * @author afeher
 */
public class Treat extends State {

    @Override
    public State proceed(Field[][] court, Snake snake) {
        List<Field> available = getAvailableFields(court);
        List<Field> occupied = getOccupiedFields(snake);
        available.removeAll(occupied);
        Collections.shuffle(available);
        int random = new Random().nextInt(available.size());
        Field choosen = available.get(random);
        court[choosen.getX()][choosen.getY()].setTerrain(Field.Terrain.FOOD);
        return new Wait();
    }
    
    private List<Field> getAvailableFields(Field[][] court) {
        List<Field> available = new ArrayList<>();
        for (Field[] fields : court) {
            available.addAll(Arrays.asList(fields));
        }
        return available;
    }

    private List<Field> getOccupiedFields(Snake snake) {
        List<Field> occupied = new ArrayList<>();
        for (BodyPart part : snake.getBody()) {
            occupied.add(new Field(part.getX(), part.getY()));
        }
        return occupied;
    }

}
