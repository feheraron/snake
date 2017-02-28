package snake;

import java.lang.reflect.InvocationTargetException;
import snake.view.View;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import snake.controller.GameController;

/**
 * @author afeher
 */
public class Game {
    
    private final Map<String, String> settings;

    public Game(Map<String, String> settings) {
        this.settings = settings;
    }
   
    public void run() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    int width = getSetting("width");
                    int height = getSetting("height");
                    int scale = getSetting("scale");
                    View view = new View(width, height, scale);
                    view.bindController(new GameController());
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Integer getSetting(String key) throws NumberFormatException {
        return Integer.valueOf(settings.get(key));
    }
    
}
