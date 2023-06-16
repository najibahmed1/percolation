public class PercolationDFSFast extends PercolationDFS{
    public PercolationDFSFast(int n) {
        super(n);
    }
    @Override
    protected void updateOnOpen(int row, int col) {
        boolean topRow = false;
        if(row == 0)
        {
            topRow = true;
        }
        boolean lft = row != 0 && myGrid[row - 1][col] == FULL;
        boolean rt = row != myGrid[row].length - 1 && myGrid[row + 1][col] == FULL;
        boolean up = col != 0 && myGrid[row][col - 1] == FULL;
        boolean dwn = col != myGrid[row].length - 1 && myGrid[row][col + 1] == FULL;
        if(topRow || lft || rt || up || dwn)
        {
            dfs(row, col);
        }
    }
}

