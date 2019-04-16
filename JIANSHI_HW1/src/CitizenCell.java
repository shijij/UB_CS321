/**
 * A CitizenCell is a Cell with a Citizen in the territory of a Kingdom
 *
 * @author Shiji Jiang
 * @see Cell
 */
public class CitizenCell extends Cell {

    // Define two types of citizen
    public enum CitizenType {
        X, O
    }

    // Type of citizen
    private CitizenType type;

    /**
     * Constructor for CitizenCell
     *
     * @param kingdom Kingdom it belongs to
     * @param x       X coordinate
     * @param y       Y coordinate
     * @param type    Type of Citizen
     */
    public CitizenCell(Kingdom kingdom, int x, int y, CitizenType type) {
        super(kingdom, x, y);
        this.type = type;
    }


    /**
     * Returns the satisfaction level out of 100
     *
     * @return the satisfaction level out of 100
     */
    public int getSatisfaction() {
        int sameType = 0;
        int outof = 0;

        // Foreach Direction
        for (Direction d : Direction.values()) {
            if (getNeighbour(d) != null && getNeighbour(d) instanceof CitizenCell) {
                // Neighbour is Citizen
                outof++;
                CitizenCell c = (CitizenCell) getNeighbour(d);

                if (c.getType() == this.type) {
                    // Neighbour is citizen of same type
                    sameType++;
                }
            }
        }

        return (int) (sameType * 100.0 / outof);
    }

    /**
     * Returns if the citizen is happy / satisfied
     *
     * @return if the citizen is happy / satisfied
     */
    public boolean isSatisfied() {
        return getSatisfaction() >= getKingdom().getSatisfaction_lv();
    }

    /**
     * Get Cell/Citizen type
     *
     * @return type of citizen
     */
    public CitizenType getType() {
        return type;
    }

    /**
     * Set Cell/Citizen type
     *
     * @param type CitizenType
     */
    public void setType(CitizenType type) {
        this.type = type;
    }

    /**
     * Is the type X
     *
     * @return if citizen is X
     */
    public boolean isX() {
        return type == CitizenType.X;
    }

    /**
     * Is the type O
     *
     * @return if citizen is O
     */
    public boolean isO() {
        return type == CitizenType.O;
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
        if (!(o instanceof CitizenCell)) {
            return false;
        }
        CitizenCell temp = (CitizenCell) o;
        return temp.type == this.type;
    }

    @Override
    public String toString() {
        // Render by satisfaction
        StringBuilder sb = new StringBuilder(isSatisfied() ? "\u001B[32m" : "\u001B[31m");
        sb.append(getType().toString());
        // Reset color
        sb.append("\u001B[0m");
        return sb.toString();
    }
}
