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
            int dRow = dequeue / size;
            int dCol = dequeue % size;
            if (dCol > 0 && myGrid[dRow][dCol - 1] == OPEN) {
                myGrid[row][col - 1] = FULL;
                q.add(toQueue(dRow, dCol - 1, size));
            }
            if (dCol < myGrid[dRow].length - 1 && myGrid[dRow][dCol + 1] == OPEN) {
                myGrid[dRow][dCol + 1] = FULL;
                q.add(toQueue(dRow, dCol + 1, size));
            }
            if (dRow > 0 && myGrid[dRow - 1][dCol] == OPEN) {
                myGrid[dRow - 1][dCol] = FULL;
                q.add(toQueue(dRow - 1, dCol, size));
            }
            if (dRow < myGrid.length - 1 && myGrid[dRow + 1][dCol] == OPEN) {
                myGrid[dRow + 1][dCol] = FULL;
                q.add(toQueue(dRow + 1, dCol, size));
            }
        }
    }
    private int toQueue(int row, int col, int size){
        return row * size + col;
    }
}
