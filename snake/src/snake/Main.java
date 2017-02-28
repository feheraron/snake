package snake;

import java.util.HashMap;
import java.util.Map;

/**
 * @author afeher
 */
public class Main {
    
    public static void main(String[] args) {
        Map<String, String> settings = new HashMap<>();
        settings.put("scale", "12");
        settings.put("height", "30");
        settings.put("width", "30");
        Game game = new Game(settings);
        game.run();
    }

}
