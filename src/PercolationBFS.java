import java.util.*;
public class PercolationBFS extends PercolationDFSFast{
    public PercolationBFS(int n) {
        super(n);
    }
    @Override
    protected void dfs(int row, int col) {
        int size = myGrid.length;
        int[] rowChange = {-1, 1, 0, 0};
        int[] colChange = {0, 0, -1, 1};
        if (myGrid[row][col] != OPEN) return;
        Queue<Integer> q = new LinkedList<>();
        myGrid[row][col] = FULL;
        q.add(toQueue(row, col));
        while (q.size() != 0) {
            int dq = q.remove();
            for (int i = 0; i < rowChange.length; i++) {
                row = dq / size + rowChange[i];
                col = dq % size + colChange[i];
                if(inRange(row, col) && myGrid[row][col] == OPEN){
                    q.add(toQueue(row, col));
                    myGrid[row][col] = FULL;
                }
            }

        }
    }
    private boolean inRange(int row, int col) {
        return 0 <= row && row < myGrid.length && 0 <= col
                && col < myGrid[0].length;
    }

    private int toQueue(int row, int col ){
        return row * myGrid.length + col;
    }

    /*protected void dfs(int row, int col) {
        int size = myGrid.length;
        Queue<Integer> q = new LinkedList<>();
       // myGrid[row][col] = FULL;
        q.add(toQueue(row, col, size));
        while(q.size()!=0) {
            int dequeue = q.remove();
            row = dequeue / size;
            col = dequeue % size;
            myGrid[row][col] = FULL;
            if (col > 0 && myGrid[row][col-1] != FULL && myGrid[row][col - 1] == OPEN) {
                col = col - 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
            if (col < myGrid[row].length - 1 && myGrid[row][col + 1] != FULL && myGrid[row][col + 1] == OPEN) {
                col = col + 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
            if (row > 0 && myGrid[row - 1][col] != FULL && myGrid[row - 1][col] == OPEN) {
                row = row - 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
            if (row < myGrid.length - 1 && myGrid[row + 1][col] != OPEN && myGrid[row + 1][col] == OPEN) {
                row = row + 1;
                myGrid[row][col] = FULL;
                q.add(toQueue(row, col, size));
            }
        }
    }
    */
}
