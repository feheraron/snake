package snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import snake.model.Field;
import snake.controller.GameController;
import snake.model.BodyPart;
import snake.model.Direction;
import snake.model.Snake;

/**
 * @author afeher
 */
public class View extends JFrame implements ActionListener {
  
    private int width = 1;
    private int height = 1;
    private int scale = 1;
    private GameController controller;
    private List<BodyPart> renderedSnake;

    public View(int width, int height, int scale) {
        setTitle("Snake");
        setWidth(width);
        setHeight(height);
        setScale(scale);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        renderedSnake = new ArrayList<>();
    }

    private void setWidth(int width) {
        this.width = width;
        setScale(scale);
    }

    private void setHeight(int height) {
        this.height = height;
        setScale(scale);
    }

    private void setScale(int scale) {
        this.scale = scale;
        setSize(scale * width, scale * height);
    }

    public void bindController(final GameController controller) {
        this.controller = controller;
        controller.init(width, height);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: controller.turn(Direction.UP); break;
                    case KeyEvent.VK_RIGHT: controller.turn(Direction.RIGHT); break;
                    case KeyEvent.VK_DOWN: controller.turn(Direction.DOWN); break;
                    case KeyEvent.VK_LEFT: controller.turn(Direction.LEFT); break;
                    default:
                }
                if (!controller.isStarted()) {
                    controller.start(View.this);
                }
            }
        });
        setVisible(true);
    }
    
    public void draw(Field[][] court) {
        Graphics g = getContentPane().getGraphics();
        for (Field[] rows : court) {
            for (Field field : rows) {
                if (Field.Terrain.FOOD.equals(field.getTerrain())) {
                    int xOffset = field.getX() * scale;
                    int yOffset = field.getY() * scale;
                    g.setColor(Color.gray);
                    g.fillRect(xOffset, yOffset, scale, scale);
                }
            }
        }
        g.dispose();
    }
    
    public void draw(Snake snake) {
        Graphics g = getContentPane().getGraphics();
        for (BodyPart renderedPart : renderedSnake) {
            if (!contains(snake.getBody(), renderedPart)) {
                clear(renderedPart, g);
            }
        }
        renderedSnake = new ArrayList<>();
        for (BodyPart bodyPart : snake.getBody()) {
            draw(bodyPart, g);
            renderedSnake.add(bodyPart.copy());
        }
        g.dispose();
    }
    
    private boolean contains(List<BodyPart> body, BodyPart test) {
        for (BodyPart rendered : body) {
            if (rendered.getX() == test.getX() && rendered.getY() == test.getY()) {
                return true;
            }
        }
        return false;
    }

    private void draw(BodyPart part, Graphics g) {
        int xOffset = getOffsetX(part);
        int yOffset = getOffsetY(part);
        g.fillRect(xOffset, yOffset, scale, scale);
    }

    private void clear(BodyPart part, Graphics g) {
        int xOffset = getOffsetX(part);
        int yOffset = getOffsetY(part);
        g.clearRect(xOffset, yOffset, scale, scale);
    }

    private int getOffsetX(BodyPart part) {
        return part.getX() * scale;
    }

    private int getOffsetY(BodyPart part) {
        return part.getY() * scale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (controller.isInProgress()) {
            draw(controller.getCourt());
            draw(controller.getSnake());
            controller.proceed(this);
        } else {
            setTitle("Snake - GAME OVER");
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (controller != null) {
            draw(controller.getCourt());
            draw(controller.getSnake());
        }
    }

}
