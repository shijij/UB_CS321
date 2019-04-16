import java.util.*;


/**
 * Representing a Kingdom.
 *
 * @author Shiji Jiang
 * @see Cell
 * @see CitizenCell
 */
public class Kingdom implements Iterable<Cell> {

    // Max number of rounds for simulation
    public static final int MAX_ROUND = 100000;

    // Dimension of the kingdom
    private int dimension;
    // Level of satisfaction to be happy
    private int satisfaction_lv;
    // Represent the territory of the kingdom
    private Cell[][] territory;
    // Keep track of vacant Cells
    private ArrayList<Cell> vacant = new ArrayList<>();


    /**
     * Construct an empty territory by dimension
     *
     * @param dimension       dimension (N) of the territory (N x N)
     * @param satisfaction_lv level of satisfaction to be happy
     *
     * @throws IllegalArgumentException if any of the inputs is not valid
     */
    public Kingdom(int dimension, int satisfaction_lv) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimension must be positive!");
        }
        this.dimension = dimension;
        if (satisfaction_lv > 100 || satisfaction_lv < 0) {
            throw new IllegalArgumentException("Satisfaction must between 0 and 100");
        }
        this.satisfaction_lv = satisfaction_lv;
        this.territory = new Cell[dimension][dimension];
    }


    /**
     * Spawns citizens randomly on the territory
     *
     * @param percentageX Percentage of X type citizen
     * @param percentageO Percentage of O type citizen
     *
     * @throws IllegalArgumentException if any of the inputs is not valid
     */
    public void seed(int percentageX, int percentageO) {

        if (percentageO > 100 || percentageO < 0
                || percentageX > 100 || percentageX < 0) {
            throw new IllegalArgumentException("Invalid Percentage!");
        }

        // Get the number first, so the number always match the percentage
        int numX = (int) Math.floor(size() * (percentageX / 100.0));
        int numO = (int) Math.floor(size() * (percentageO / 100.0));

        List<CitizenCell.CitizenType> l = new ArrayList<>(size());

        // Fill the List with citizens and vacant cells
        for (int i = 0; i < size(); i++) {
            if (numX > 0) {
                l.add(CitizenCell.CitizenType.X);
                numX--;
            } else if (numO > 0) {
                l.add(CitizenCell.CitizenType.O);
                numO--;
            } else {
                l.add(null);
            }
        }

        // Shuffle the List
        Collections.shuffle(l);

        // Save the shuffled List to territory
        int index = 0;
        for (CitizenCell.CitizenType type : l) {
            int x = index / dimension;
            int y = index % dimension;
            Cell cell;
            if (type == null) {
                cell = new Cell(this, x, y);
                vacant.add(cell);
            } else {
                cell = new CitizenCell(this, x, y, type);
            }

            territory[x][y] = cell;
            index++;
        }
    }

    /**
     * Simulate the process of moving citizens
     */
    public void simulation() {
        // Loop till MAX_ROUND reached
        for (int i = 0; i < MAX_ROUND; i++) {
            System.out.println("Round " + (i + 1));

            // Foreach uphappy citizen
            for (CitizenCell citizen : getUnhappyCitizens()) {
                // Skip if the citizen is now happy due to Other's prev move
                if (citizen.isSatisfied()) {
                    continue;
                }
                // Mark if current citizen finally got a satisfied location
                boolean moved = false;

                // Sort the vacant locations by manhattan distance from cur citizen
                Collections.sort(vacant, new SortByDistance(citizen));

                // Check each vacant location, will the citizen happy?
                for (Cell v : vacant) {
                    // Move
                    CitizenCell citizenMoved = moveCitizen(citizen, v, false);

                    // Then Check satisfaction
                    if (citizenMoved.isSatisfied()) {
                        // Update vacant locations and move on to next unhappy citizen
                        updateVacantList(v, citizen.getX(), citizen.getY());
                        moved = true;
                        break;
                    } else {
                        // Revert move if unhappy
                        moveCitizen(citizenMoved, citizen, true);
                    }
                }

                // Can't find satisfied location? move randomly
                if (!moved) {
                    Random r = new Random();
                    int randomIndex = r.nextInt(vacant.size());
                    Cell target = vacant.get(randomIndex);
                    moveCitizen(citizen, target, false);
                    updateVacantList(target, citizen.getX(), citizen.getY());
                }
                System.out.println(this);
            }


            if (getUnhappyCitizens().size() == 0) {
                // Exit when citizens are all happy
                System.out.println("All Satisfied"
                        + (i > 0 ? ", Early Exit @ Round " + (i + 1) : ""));
                return;
            } else if (i == MAX_ROUND - 1) {
                System.out.println("Max round reached, exit..");
            }
        }

    }

    /**
     * Returns an ArrayList of unhappy citizens
     *
     * @return an ArrayList of unhappy citizens
     */
    private ArrayList<CitizenCell> getUnhappyCitizens() {

        Iterator<CitizenCell> citizens = this.itrCitizen();
        ArrayList<CitizenCell> result = new ArrayList<>();

        // Go through citizens
        while (citizens.hasNext()) {
            CitizenCell citizen = citizens.next();
            if (!citizen.isSatisfied()) {
                // Add to result if unhappy
                result.add(citizen);

            }
        }
        return result;
    }


    private void updateVacantList(Cell toRemove, int newX, int newY) {
        vacant.remove(toRemove);
        vacant.add(new Cell(this, newX, newY));
    }

    /**
     * Move a citizen from current location to a vacant location
     *
     * @param from     current citizen
     * @param to       target location
     * @param moveBack identifier for special output while reverting a move
     *
     * @return A new CitizenCell in the target location
     */
    private CitizenCell moveCitizen(CitizenCell from, Cell to, boolean moveBack) {

        int fromX = from.getX();
        int fromY = from.getY();
        int toX = to.getX();
        int toY = to.getY();

        CitizenCell newCitizen = new CitizenCell(this, toX, toY, from.getType());
        territory[toX][toY] = newCitizen;
        territory[fromX][fromY] = new Cell(this, fromX, fromY);

        System.out.println((moveBack ? "\t- Unhappy, MV Back" : "MV Citizen"
                + " from <" + fromX + ", " + fromY + ">")
                + " \tto <" + toX + ", " + toY + ">");
        return newCitizen;
    }

    public Cell getCell(int x, int y) {
        return this.territory[x][y];
    }

    /**
     * Get the total numbers of cells on the territory
     *
     * @return size of the territory
     */
    public int size() {
        return dimension * dimension;
    }

    /**
     * Returns the dimension of the kingdom territory
     *
     * @return the dimension of the kingdom territory
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Returns the satisfaction level of the kingdom citizens
     *
     * @return the satisfaction level of the kingdom citizens
     */
    public int getSatisfaction_lv() {
        return satisfaction_lv;
    }

    /**
     * Iterator for every cells, either vacant or CitizenCell Default for-each iterator
     *
     * @return Cell iterator
     */
    @Override
    public Iterator<Cell> iterator() {
        Iterator<Cell> it = new Iterator<Cell>() {

            private int index = 0;

            /**
             * Has next cell in territory
             * @return if there exist next cell in territory
             */
            @Override
            public boolean hasNext() {
                return index < dimension * dimension;
            }

            /**
             * Returns the next Cell in the iteration.
             *
             * @return the next Cell in the iteration
             *
             * @throws NoSuchElementException if the iteration has no more Cell
             */
            @Override
            public Cell next() {
                try {
                    Cell cur = getCell(index / dimension, index % dimension);
                    index++;
                    return cur;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }

            /**
             * Method Not Supported
             *
             * @throws UnsupportedOperationException if the {@code remove}
             *      operation is not supported by this iterator
             */
            @Override
            public void remove(){
                throw new UnsupportedOperationException("remove");
            }

        };
        return it;
    }


    /**
     * Iterator for each CitizenCell using the default iterator
     *
     * @return CitienCell iterator
     */
    public Iterator<CitizenCell> itrCitizen() {
        Iterator<CitizenCell> it = new Iterator<CitizenCell>() {

            private Iterator<Cell> itrCell = iterator();
            private CitizenCell nextCitizen = null;     // Stores the next Citizen

            /**
             * Returns true if the kingdom has more citizens.
             * @return if the kingdom has more citizens.
             */
            @Override
            public boolean hasNext() {
                // If found from prev call
                if (nextCitizen != null) {
                    return true;
                }
                while (itrCell.hasNext()) {
                    Cell temp = itrCell.next();
                    if (temp instanceof CitizenCell) {
                        // Save to next citizen storage
                        nextCitizen = (CitizenCell) temp;
                        return true;
                    }
                }
                return false;
            }

            /**
             * Returns the next citizen in the iteration.
             * @return the next citizen in the iteration.
             *
             * @throws NoSuchElementException if the iteration has no more citizen
             */
            @Override
            public CitizenCell next() {
                if (nextCitizen != null || this.hasNext()) {
                    CitizenCell result = nextCitizen;
                    // Reset next citizen storage
                    nextCitizen = null;
                    return result;
                }
                throw new NoSuchElementException();
            }

            /**
             * Method Not Supported
             *
             * @throws UnsupportedOperationException if the {@code remove}
             *      operation is not supported by this iterator
             */
            @Override
            public void remove(){
                throw new UnsupportedOperationException("remove");
            }


        };
        return it;
    }

    /**
     * Returns a string representing the kingdom
     *
     * @return a string representing the kingdom
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");

        for (Cell[] cells : territory) {
            sb.append("|");

            for (Cell cell : cells) {

                if (cell instanceof CitizenCell) {
                    CitizenCell citizen = (CitizenCell) cell;
                    sb.append(citizen);
                } else {
                    sb.append(cell);
                }

                sb.append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
