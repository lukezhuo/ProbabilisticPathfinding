/**
 * Simulate percolation thresholds for a grid-base system using
 * depth-first-search, aka 'flood-fill' techniques for determining if the top of
 * a grid is connected to the bottom of a grid.
 *
 * @author Luke Zhuo
 **/
public class PercolationDFSFast extends PercolationDFS {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n
     *           is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    /**
     * Calls dfs on a cell if it should be marked as full.
     *
     * @param row
     *            is the row coordinate of the cell being checked/marked
     * @param col
     *            is the col coordinate of the cell being checked/marked
     */
    @Override
    protected void updateOnOpen(int row, int col) {
        if(row == 0){
            dfs(row, col);
        }
        else if (col > 0 && myGrid[row][col-1] == FULL){
            dfs(row, col);
        }
        else if(col < myGrid[row].length - 1 && myGrid[row][col+1] == FULL){
            dfs(row, col);
        }
        else if (row > 0 && myGrid[row-1][col] == FULL){
            dfs(row, col);
        }
        else if(row < myGrid.length - 1 && myGrid[row+1][col] == FULL){
            dfs(row, col);
        }
    }
    
}
