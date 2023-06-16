import java.util.*;
public class PercolationBFS extends PercolationDFSFast{
    public PercolationBFS(int n) {
        super(n);
    }
    @Override
    protected void dfs(int row, int col) {
        int[] rowDelta = {-1, 1, 0 ,0};
        int[] colDelta = {0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<>();
        myGrid[row][col] = FULL;
        queue.add(new int[]{row, col});
        while(queue.size() != 0)
        {
            int[] rem = queue.remove();
            for(int i = 0; i < rowDelta.length; i++)
            {
                row = rem[0] + rowDelta[i];
                col = rem[1] + colDelta[i];
                if(inBounds(row, col) && isOpen(row, col) && !isFull(row, col))
                {
                    myGrid[row][col] = FULL;
                    queue.add(new int[]{row, col});
                }
            }
        }
    }
}

