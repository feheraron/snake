package snake.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import snake.model.BodyPart;
import snake.model.Direction;
import snake.model.Field;
import snake.model.Field.Terrain;
import snake.model.Snake;

/**
 * @author afeher
 */
public class Player {
    
    private Snake snake;
    private boolean alive;

    public Player() {
        snake = new Snake();
        snake.append(3);
        alive = true;
    }
    
    public Field[][] introduce(Field[][] court) {
        Field[][] snapshot = snapshot(court);
        dropFood(snapshot);
        updateTerrainOccupied(snapshot, snake);
        return snapshot;
    }

    public Field[][] tick(Field[][] court, Direction direction) {
        Field[][] snapshot = snapshot(court);
        Snake beforeMove = snake.copy();
        Terrain terrain = move(snapshot, direction);
        switch(terrain) {
            case FOOD: eat(); dropFood(snapshot); break;
            case SNAKE: break;
            case OFF_GRID: die(); return snapshot;
            case PLAIN: // nothing to do
            default: 
        }
        updateTerrainLeft(snapshot, beforeMove);
        updateTerrainOccupied(snapshot, snake);
        return snapshot;
    }

    private Terrain move(Field[][] court, Direction direction) {
        snake.move(direction);
        BodyPart head = snake.getHead();
        if (court.length <= head.getX() 
                || court[0].length <= head.getY()
                || head.getY() < 0
                || head.getX() < 0) {
            return Terrain.OFF_GRID;
        } else {
            return getField(court, head).getTerrain();
        }
    }
    
    private Field getField(Field[][] court, BodyPart bodyPart) {
        return court[bodyPart.getX()][bodyPart.getY()];
    }
    
    private void updateTerrainLeft(Field[][] court, Snake beforeMove) {
        for (BodyPart bodyPart : beforeMove.getBody()) {
            getField(court, bodyPart).setTerrain(Terrain.PLAIN);
        }
    }

    private void updateTerrainOccupied(Field[][] court, Snake afterMove) {
        for (BodyPart bodyPart : afterMove.getBody()) {
            getField(court, bodyPart).setTerrain(Terrain.SNAKE);
        }
    }

    private Field[][] snapshot(Field[][] court) {
        Field[][] snapshot = new Field[court.length][court[0].length];
        for (int y = 0; y < court.length; y++) {
            for (int x = 0; x < court[0].length; x++) {
                snapshot[y][x] = court[y][x].copy();
            }
        }
        return snapshot;
    }

    private void eat() {
        snake.append(1);
    }

    private void dropFood(Field[][] court) {
        List<Field> available = getAvailableFields(court);
        List<Field> occupied = getOccupiedFields(snake);
        available.removeAll(occupied);
        Collections.shuffle(available);
        int random = new Random().nextInt(available.size());
        Field choosen = available.get(random);
        court[choosen.getX()][choosen.getY()].setTerrain(Field.Terrain.FOOD);
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

    private void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
    
}
