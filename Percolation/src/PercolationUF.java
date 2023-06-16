import java.util.*;
public class PercolationUF implements IPercolate{

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        finder.initialize(size * size + 2);
        myFinder = finder;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
    }
    @Override
    public void open(int row, int col) {
        if (!inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }   if (myGrid[row][col]){
            return;
        }
        myOpenCount += 1;
        myGrid[row][col] = true;
        //cv = cells value
        int cV = getCv(row, col);
            if(inBounds(row - 1, col) && isOpen(row - 1, col)){
                myFinder.union(cV, getCv(row - 1, col));
        }   if(inBounds(row + 1, col) && isOpen(row + 1, col)){
                myFinder.union(cV, getCv(row + 1, col));
        }   if(inBounds(row, col - 1) && isOpen(row, col - 1)){
                myFinder.union(cV, getCv(row, col - 1));
        }   if(inBounds(row, col + 1) && isOpen(row, col + 1)){
                myFinder.union(cV, getCv(row, col + 1));
        }   if(row == 0){
                myFinder.union(cV, VTOP);
        }   if(row == myGrid.length - 1){
                myFinder.union(cV, VBOTTOM);
            }
    }
    private int getCv(int row, int col)
    {
        return row * myGrid.length + col;
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }
    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }

        return myFinder.connected(getCv(row, col), VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    private boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }
}


