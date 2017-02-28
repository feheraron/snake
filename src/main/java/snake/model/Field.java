package snake.model;

/**
 * @author afeher
 */
public class Field {
    
    private Terrain terrain = Terrain.PLAIN;
    
    private final int x;
    private final int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public enum Terrain {
        PLAIN, FOOD, OBSTACLE;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
