public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount = 0;
    PercolationUF(IUnionFind finder, int size){
        myGrid = new boolean [size][size];
        finder.initialize(size * size + 2);
        myFinder = finder;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
    }
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

    @Override
    public boolean isOpen(int row, int col) {
        if(row < 0 || row > myGrid.length - 1 || col < 0 || col > myGrid[row].length - 1){
            throw new IndexOutOfBoundsException();
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if(row < 0 || row > myGrid.length - 1 || col < 0 || col > myGrid[row].length - 1){
            throw new IndexOutOfBoundsException();
        }
        return myFinder.connected(getIndex(row, col), VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }
    private int getIndex(int row, int col){
        return row * myGrid.length + col;
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
