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
                if(row >=0 && row < myGrid.length && col >= 0 && col < myGrid[row].length){
                if(myGrid[row][col] == OPEN){
                    q.add(toQueue(row, col));
                    myGrid[row][col] = FULL;
                }
                }
            }

        }
    }
    
    private int toQueue(int row, int col ){
        return row * myGrid.length + col;
    }

}
