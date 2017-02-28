package snake.controller;

import javax.swing.Timer;
import snake.model.Direction;
import snake.model.Field;
import snake.model.Snake;
import snake.model.state.Treat;
import snake.model.state.State;
import snake.model.state.Wait;
import snake.view.View;

/**
 * @author afeher
 */
public class GameController {

    private Field[][] court;
    private Snake snake;
    private State currentState;
    private Timer timer;
    private boolean started;

    public void init(int width, int height) {
        court = createCourt(width, height);
        snake = new Snake();
        snake.append(3);
        currentState = new Treat().proceed(court, snake);
        started = false;
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

    public Snake getSnake() {
        return snake;
    }

    public void turn(Direction direction) {
        snake.setDirection(direction);
    }

    public void start(final View view) {
        started = true;
        timer = new Timer(0, view);
        proceed(view);
    }

    public void proceed(View view) {
        currentState = currentState.proceed(court, snake);
        if (currentState instanceof Wait) {
            timer.stop();
            timer = new Timer(100, view);
        }
        timer.setDelay(0);
        timer.setRepeats(false);
        timer.restart();
    }

    public boolean isInProgress() {
        return currentState.isInProgress();
    }

    public boolean isStarted() {
        return started;
    }
}
