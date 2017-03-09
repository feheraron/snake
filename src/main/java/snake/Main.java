package snake;

import java.util.HashMap;
import java.util.Map;

/**
 * @author afeher
 */
public class Main {
    
    public static void main(String[] args) {
        Map<String, String> settings = new HashMap<>();
        settings.put("scale", "10");
        settings.put("height", "25");
        settings.put("width", "25");
        Game game = new Game(settings);
        game.run();
    }

}
