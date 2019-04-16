/**
 * Represent the smallest unit in the territory of a Kingdom
 *
 * @author Shiji Jiang
 * @see CitizenCell
 */
public class Cell {

    public enum Direction {E, W, N, S, NW, NE, SW, SE}

    // Kingdom it belongs to
    private Kingdom kingdom;

    // Location
    private int x;
    private int y;

    /**
     * Constructor for vacant Cell
     *
     * @param kingdom Kingdom it belongs to
     */
    public Cell(Kingdom kingdom, int x, int y) {
        this.kingdom = kingdom;
        this.x = x;
        this.y = y;
    }

    /**
     * Return if a neighbour exists in the direction
     *
     * @param direction the direction
     *
     * @return the neighbour cell in the direction exist
     */
    public boolean hasNeighbour(Direction direction) {
        switch (direction) {
            case W:
                return y > 0;
            case E:
                return y < kingdom.getDimension() - 1;
            case N:
                return x > 0;
            case S:
                return x < kingdom.getDimension() - 1;
            case NE:
                return getNeighbour(Direction.N) != null
                        && getNeighbour(Direction.N).hasNeighbour(Direction.E);
            case SE:
                return getNeighbour(Direction.S) != null
                        && getNeighbour(Direction.S).hasNeighbour(Direction.E);
            case NW:
                return getNeighbour(Direction.N) != null
                        && getNeighbour(Direction.N).hasNeighbour(Direction.W);
            case SW:
                return getNeighbour(Direction.S) != null
                        && getNeighbour(Direction.S).hasNeighbour(Direction.W);
        }
        return false;
    }

    /**
     * Get Neighbour by direction
     *
     * @param direction the direction
     *
     * @return the neighbour cell in the direction requested, or null if not exist
     */
    public Cell getNeighbour(Direction direction) {
        switch (direction) {
            case W:
                if (this.hasNeighbour(direction)) {
                    return kingdom.getCell(x, y - 1);
                }
                break;
            case E:
                if (this.hasNeighbour(direction)) {
                    return kingdom.getCell(x, y + 1);
                }
                break;
            case N:
                if (this.hasNeighbour(direction)) {
                    return kingdom.getCell(x - 1, y);
                }
                break;
            case S:
                if (this.hasNeighbour(direction)) {
                    return kingdom.getCell(x + 1, y);
                }
                break;
            case NE:
                if (getNeighbour(Direction.N) != null) {
                    return getNeighbour(Direction.N).getNeighbour(Direction.E);
                }
                break;
            case SE:
                if (getNeighbour(Direction.S) != null) {
                    return getNeighbour(Direction.S).getNeighbour(Direction.E);
                }
                break;
            case NW:
                if (getNeighbour(Direction.N) != null) {
                    return getNeighbour(Direction.N).getNeighbour(Direction.W);
                }
                break;
            case SW:
                if (getNeighbour(Direction.S) != null) {
                    return getNeighbour(Direction.S).getNeighbour(Direction.W);
                }
                break;
        }
        // No such neighbour
        return null;
    }


    /**
     * Return the kingdom it belongs to
     *
     * @return the kingdom it belongs to
     */
    public Kingdom getKingdom() {
        return kingdom;
    }

    /**
     * Returns the X coordinate (Row)
     *
     * @return the X coordinate (Row)
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y coordinate (Column)
     *
     * @return the Y coordinate (Column)
     */
    public int getY() {
        return y;
    }

    /**
     * Determine it it's equal to another object
     *
     * @param o Another Object
     *
     * @return if equals
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell temp = (Cell) o;
        return temp.kingdom == kingdom && temp.x == x && temp.y == y;
    }

    @Override
    public String toString() {
        return " ";
    }

}
