package snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import snake.controller.GameController;
import snake.model.BodyPart;
import snake.model.Direction;
import snake.model.Field;
import snake.model.Snake;

/**
 * @author afeher
 */
public class View extends JFrame {

    private int widthUnits = 10;
    private int heightUnits = 10;
    private int scale = 1;
    private boolean showGrid = false;
    
    private GameController controller;

    public View(int widthUnits, int heightUnits, int scale) {
	setTitle("Snake");
	setWidthUnits(widthUnits);
	setHeightUnits(heightUnits);
	setScale(scale);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setWidthUnits(int units) {
	this.widthUnits = units;
	setScale(scale);
    }

    private void setHeightUnits(int units) {
	this.heightUnits = units;
	setScale(scale);
    }

    private void setScale(int scale) {
	this.scale = scale;
	setSize(scale * widthUnits, scale * heightUnits);
    }

    public void bindController(final GameController controller) {
	this.controller = controller;
	controller.init(widthUnits, heightUnits);
	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP: controller.turn(Direction.UP); break;
		case KeyEvent.VK_RIGHT: controller.turn(Direction.RIGHT); break;
		case KeyEvent.VK_DOWN: controller.turn(Direction.DOWN); break;
		case KeyEvent.VK_LEFT: controller.turn(Direction.LEFT); break;
                case KeyEvent.VK_ESCAPE: controller.togglePause(View.this); return;
                case KeyEvent.VK_G: toggleShowGrid(); return;
                case KeyEvent.VK_N: startNewGame(); return;
		default:
                    return;
		}
		if (!controller.isStarted()) {
		    controller.start(View.this);
		}
	    }
	});
    }
    
    private void startNewGame() {
        controller.init(widthUnits, heightUnits);
        updateScene();
    }
    
    private void toggleShowGrid() {
        showGrid = !showGrid;
        updateScene();
    }

    public void updateScene() {
        if (!controller.isInProgress()) {
            setTitle("Snake - GAME OVER");
        }
        final int width = getWidth();
        final int height = getHeight();
        Image scene = createImage(width, height);
        Graphics sceneGraphics = scene.getGraphics();
        drawBackground(width, height, sceneGraphics);
        sceneGraphics.setColor(Color.black);
        draw(controller.getCourt(), sceneGraphics);
        draw(controller.getSnake(), sceneGraphics);
        getContentPane().getGraphics().drawImage(scene, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        updateScene();
    }

    private void draw(Field[][] court, Graphics g) {
        for (Field[] rows : court) {
            for (Field field : rows) {
                int xOffset = field.getX() * scale;
                int yOffset = field.getY() * scale;
                if (Field.Terrain.FOOD.equals(field.getTerrain())) {
                    g.setColor(Color.gray);
                    g.fillRect(xOffset, yOffset, scale, scale);
                    g.setColor(Color.black);
                }
                if (showGrid) {
                    g.setColor(Color.black);
                    g.drawRect(xOffset, yOffset, scale, scale);
                }
            }
        }
    }

    private void drawBackground(int width, int height, Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
    }

    private void draw(Snake snake, Graphics g) {
        for (BodyPart bodyPart : snake.getBody()) {
            draw(bodyPart, g);
        }
    }

    private void draw(BodyPart part, Graphics g) {
        int xOffset = getOffsetX(part);
        int yOffset = getOffsetY(part);
        g.fillRect(xOffset, yOffset, scale, scale);
    }

    private int getOffsetX(BodyPart part) {
        return part.getX() * scale;
    }

    private int getOffsetY(BodyPart part) {
        return part.getY() * scale;
    }

}
