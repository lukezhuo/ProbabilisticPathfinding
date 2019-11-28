/**
 * Simulate percolation thresholds for a grid-base system using
 * union find, aka disjoint set algorithm for determining if the top of
 * a grid is connected to the bottom of a grid.
 *
 * @author Luke Zhuo
 **/
public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount = 0;

    /**
     * Initialize an IUnionFind object, initialize integers VTOP and VBOTTOM,
     * which when formed a union to, represent the presence of a cell in the top
     * and bottom rows, and initialize a grid so that all cells are blocked
     * @param finder
     *             is the IUnionFind object to be initialized
     * @param size
     *             is the size of myGrid
     */
    PercolationUF(IUnionFind finder, int size){
        myGrid = new boolean [size][size];
        finder.initialize(size * size + 2);
        myFinder = finder;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
    }

    /**
     * Changes the cell at the row and col specified by the parameters to open if it
     * is not already open. Calls the union method to merge the cell with any adjacent
     * open cells and VTOP or VBOTTOM if it is in the top or bottom row.
     * @param row
     *            is the row coordinate of the cell being checked/marked
     * @param col
     *            is the col coordinate of the cell being checked/marked
     */
    @Override
    public void open(int row, int col) {
        if(row < 0 || row > myGrid.length - 1 || col < 0 || col > myGrid[row].length - 1){
            throw new IndexOutOfBoundsException();
        }
        if(myGrid[row][col] == false){
            myGrid[row][col] = true;
            myOpenCount++;
        }
        if(row == 0){
            myFinder.union(getIndex(row, col), VTOP);
        }
        if(row == myGrid.length-1){
            myFinder.union(getIndex(row, col), VBOTTOM);
        }
        if(row > 0 && myGrid[row - 1][col] == true){
            myFinder.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if(row < myGrid.length - 1 && myGrid[row + 1][col] == true){
            myFinder.union(getIndex(row, col), getIndex(row + 1, col));
        }
        if(col > 0 && myGrid[row][col - 1] == true){
            myFinder.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if(col < myGrid[row].length - 1 && myGrid[row][col + 1] == true){
            myFinder.union(getIndex(row, col), getIndex(row, col + 1));
        }
    }

    /**
     * Returns a boolean representing whether or not the cell at the row and col specified
     * by the parameters is open
     * @param row
     *            is the row coordinate of the cell being checked/marked
     * @param col
     *            is the col coordinate of the cell being checked/marked
     * @return the boolean value at the row and col of myGrid specified by the parameters
     */
    @Override
    public boolean isOpen(int row, int col) {
        if(row < 0 || row > myGrid.length - 1 || col < 0 || col > myGrid[row].length - 1){
            throw new IndexOutOfBoundsException();
        }
        return myGrid[row][col];
    }

    /**
     * Returns a boolean that represents if the cell at the row and col of myGrid
     * specified by the parameters is full
     * @param row
     *            is the row coordinate of the cell being checked/marked
     * @param col
     *            is the col coordinate of the cell being checked/marked
     * @return a boolean value representing whether or not the cell at the row and col
     * of myGrid specified by the parameters is full
     */
    @Override
    public boolean isFull(int row, int col) {
        if(row < 0 || row > myGrid.length - 1 || col < 0 || col > myGrid[row].length - 1){
            throw new IndexOutOfBoundsException();
        }
        return myFinder.connected(getIndex(row, col), VTOP);
    }

    /**
     * Returns a boolean that represents if percolation occurs
     * @return a boolean value representing whether or not the system percolates
     */
    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    private int getIndex(int row, int col){
        return row * myGrid.length + col;
    }

    /**
     * Returns the number of cells that are open
     * @return the count of the open cells
     */
    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
