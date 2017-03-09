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

    public Field copy() {
        Field copy = new Field(x, y);
        copy.setTerrain(terrain);
        return copy;
    }

    public enum Terrain {
        PLAIN, FOOD, SNAKE, OFF_GRID;
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Field other = (Field) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
}
