package snake.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import snake.model.Direction;
import snake.model.Field;
import snake.view.View;

/**
 * @author afeher
 */
public class GameController {

    private Field[][] court;
    private Player player;
    private List<Direction> directions;
    private boolean autoDirection;
    private boolean started;
    private Timer timer;
    private int delay;

    public void init(int width, int height) {
        player = new Player();
        court = player.introduce(createCourt(width, height));
        directions = new LinkedList<>();
        autoDirection = false;
        started = false;
        timer = null;
        delay = 80;
    }

    private Field[][] createCourt(int x, int y) {
        Field[][] court = new Field[x][y];
        for (int col = 0; col < x; col++) {
            for (int row = 0; row < y; row++) {
                court[col][row] = new Field(col, row);
            }
        }
        return court;
    }

    public Field[][] getCourt() {
        return court;
    }

    public void turn(Direction direction) {
        if (!directions.isEmpty() && directions.get(directions.size() - 1).isOpposite(direction)) {
            return;
        }
        if (autoDirection) {
            directions.clear();
        }
        directions.add(direction);
        autoDirection = false;
    }

    public void start(final View view) {
        started = true;
        timer = new Timer("Snake-timer", true);
        scheduleTimer(view);
    }

    private Direction getDirection() {
        Direction direction = directions.get(0);
        directions.remove(0);
        if (directions.isEmpty()) {
            directions.add(direction);
            autoDirection = true;
        }
        return direction;
    }

    public boolean isInProgress() {
        return player.isAlive();
    }

    public boolean isStarted() {
        return started;
    }

    public void togglePause(View view) {
        if (player.isAlive()) {
            if (started) {
                pause();
            } else {
                start(view);
            }
        }
    }

    private void scheduleTimer(final View view) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            if (started && player.isAlive()) {
                                court = player.tick(court, getDirection());
                            } else {
                                timer.cancel();
                            }
                            view.updateScene();
                        }
                    });
                } catch (InvocationTargetException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, delay, delay);
    }

    private void pause() {
        timer.cancel();
        timer.purge();
        started = false;
    }
    
}
