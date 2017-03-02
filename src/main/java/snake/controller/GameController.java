package snake.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import snake.model.Direction;
import snake.model.Field;
import snake.model.Snake;
import snake.model.state.State;
import snake.model.state.Treat;
import snake.view.View;

/**
 * @author afeher
 */
public class GameController {

    private Field[][] court;
    private Snake snake;
    private State currentState;
    private boolean started;
    private Timer timer;
    private int delay;

    public void init(int width, int height) {
        court = createCourt(width, height);
        snake = new Snake();
        snake.append(3);
        currentState = new Treat().proceed(court, snake);
        started = false;
	timer = new Timer();
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

    public Snake getSnake() {
        return snake;
    }

    public void turn(Direction direction) {
        snake.setDirection(direction);
    }

    public void start(final View view) {
        started = true;
        proceed(view);
    }
        
    private void proceed(View view) {
	if (currentState.isInProgress()) {
	    if(currentState.isFastForward())
		proceedFastForward(view);
	    else
		proceedWait(view);
	}
    }

    private void proceedFastForward(final View view) {
	while (currentState.isFastForward()) {
	    currentState = currentState.proceed(court, snake);
	    view.updateScene();
	    proceed(view);
	}
    }
    
    private void proceedWait(final View view) {
	timer.schedule(new TimerTask() {
	    @Override
	    public void run() {
		try {
		    SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
			    currentState = currentState.proceed(court, snake);
			    view.updateScene();
			    proceed(view);
			}
		    });
		} catch (InvocationTargetException | InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}, delay);
    }

    public boolean isInProgress() {
        return currentState.isInProgress();
    }

    public boolean isStarted() {
        return started;
    }
}
