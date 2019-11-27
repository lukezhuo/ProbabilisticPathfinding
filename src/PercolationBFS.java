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
        q.add(row * size + col);
        int dequeue = q.remove();
        int dRow = dequeue / size;
        int dCol = dequeue % size;
        if(dCol > 0 && myGrid[row][col-1] != FULL && myGrid[row][col-1] == OPEN) {
            //dfs(row, col -1);
            myGrid[row][col-1] = FULL;
            q.add(row * size + col - 1);
        }
        if(dCol < myGrid[row].length - 1 && myGrid[row][col+1] != FULL && myGrid[row][col+1] == OPEN){
            //dfs(row, col +1);
            myGrid[row][col+1] = FULL;
            q.add(row * size + col + 1);
        }
        if(dRow > 0 && myGrid[row-1][col] != FULL && myGrid[row-1][col] == OPEN){
            //dfs(row-1, col);
            myGrid[row-1][col] = FULL;
            q.add((row-1) * size + col);
        }
        if(dRow < myGrid.length - 1 && myGrid[row+1][col] != FULL && myGrid[row+1][col] == OPEN){
            //dfs(row+1, col);
            myGrid[row+1][col] = FULL;
            q.add((row+1) * size + col);
        }
    }
}
