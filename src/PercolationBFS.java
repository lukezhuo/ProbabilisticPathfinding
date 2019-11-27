import java.util.*;
public class PercolationBFS extends PercolationDFSFast{
    public PercolationBFS(int n) {
        super(n);
    }
    @Override
    protected void dfs(int row, int col) {
        int size = myGrid.length;
        Queue<Integer> q = new LinkedList<>();
        myGrid[row][col] = FULL;
        q.add(toQueue(row, col, size));
        while(q.size()!=0) {
            int dequeue = q.remove();
            row = dequeue / size;
            col = dequeue % size;

            if (col > 0 && myGrid[row][col - 1] == OPEN) {
                col = col - 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
            if (col < myGrid[row].length - 1 && myGrid[row][col + 1] == OPEN) {
                col = col + 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
            if (row > 0 && myGrid[row - 1][col] == OPEN) {
                row = row - 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
            if (row < myGrid.length - 1 && myGrid[row + 1][col] == OPEN) {
                row = row + 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
        }
    }
    private int toQueue(int row, int col, int size){
        return row * size + col;
    }
}
