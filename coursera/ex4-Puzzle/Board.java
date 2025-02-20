/******************************************************************************
 *  Compilation:  javac-algs4 Board.java
 *  Execution:    java-algs4 Board 
 *  
 *  Draws a blue bullseye and textbook graphic.
 * 
 ******************************************************************************/
/**
 * The {@code Board} class creates an immutable data type to 
 * represent a n-by-n grid with n^2-1 square blocks labeled 1 
 * through n^2-1 and a blank square.
 * <p>
 * It supports operations of calculating the Hamming and 
 * Manhattan priorities, along with methods for iterating 
 * through neighbors of the Board, determining whether the 
 * Board is a goal, and getting its twin Board.
 *
 * @author zhangyu
 * @date 2017.3.29
 */
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[] blocks1D; // 1D array of blocks
    private final int n;          // dimension of the Board

    /**
     * Constructs a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j).
     * 
     * @param blocks the initial n-by-n array of blocks
     * @throws NullPointerException if the given array is null
     */
    public Board(int[][] blocks) {
        if (blocks == null) throw new NullPointerException("Null blocks");

        n = blocks.length;
        this.blocks1D = new int[n * n];
        for (int i = 0; i < n; ++i) // copy the given 2D array to a 1D.
            for (int j = 0; j < n; ++j)
                this.blocks1D[i * n + j] = blocks[i][j];
    }

    // private constructor with a 1D array as a parameter
    private Board(int[] blocks) {
        n = (int) Math.sqrt(blocks.length);
        this.blocks1D = Arrays.copyOf(blocks, blocks.length);
    }

    /**
     * Returns dimension of the board.
     * 
     * @return dimension of the board
     */
    public int dimension() 
    {
        return n;
    }

    /**
     * Returns number of blocks in the wrong position.
     * 
     * @return number of blocks in the wrong position
     */
    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < blocks1D.length; ++i) // ignore the blank square
            if (blocks1D[i] != 0 && blocks1D[i] != i + 1) 
                hamming++;
        return hamming;
    }

    /**
     * Returns sum of Manhattan distances between blocks and goal.
     * 
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < blocks1D.length; ++i) // ignore the blank square
            if (blocks1D[i] != 0 && blocks1D[i] != i + 1) 
                manhattan += Math.abs((blocks1D[i] - 1) / n - i / n) + // distance of rows
                             Math.abs((blocks1D[i] - 1) % n - i % n);  // distance of columns
        return manhattan;
    }

    /**
     * Determine whether this board is the goal board.
     * 
     * @return true if the board is goal board.
     *         false otherwise
     */
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    /**
     * Returns a board that is obtained by exchanging any pair of 
     * initial blocks(except the blank square).
     * 
     * @return a board with exchanged blocks
     */
    public Board twin()
    {
        int[] twinBlocks;

        if (blocks1D[0] != 0 && blocks1D[1] != 0)
            twinBlocks = getSwappedBlocks(0, 1);
        else 
            twinBlocks = getSwappedBlocks(n * n - 2, n * n - 1);
        return new Board(twinBlocks);
    }

    /**
     * Determine if this board equals to y?
     * 
     * @return true if this equals to y.
     *         false otherwise
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;

        if (this.dimension() != that.dimension()) return false;
        return Arrays.equals(this.blocks1D, that.blocks1D);
    }

    /**
     * Returns an Iterable data type containing all neighboring boards.
     * 
     * @return an Iterable data type containing all neighboring boards
     */
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<Board>();
        int[] xDiff = {-1, 1, 0, 0};
        int[] yDiff = {0, 0, -1, 1};
        int[] swappedBlocks;
        int idxOfBlank; // position of the blank square
        int idxOfNB;    // position of a neighbor 

        // find position of the blank square
        for (idxOfBlank = 0; idxOfBlank < blocks1D.length; ++idxOfBlank)
            if (blocks1D[idxOfBlank] == 0) break;
        for (int i = 0; i < 4; ++i) {
            int rowOfNB = idxOfBlank / n + xDiff[i];
            int colOfNB = idxOfBlank % n + yDiff[i];

            if (rowOfNB >= 0 && rowOfNB < n && colOfNB >= 0 && colOfNB < n) {
                idxOfNB = rowOfNB * n + colOfNB;
                swappedBlocks = getSwappedBlocks(idxOfBlank, idxOfNB);
                neighbors.push(new Board(swappedBlocks));
            }
        }
        return neighbors;
    }

    // swaps elements of blocks1D and returns a new array 
    private int[] getSwappedBlocks(int i, int j) {
        // copy the blocks
        int[] blocks = Arrays.copyOf(blocks1D, blocks1D.length);
        int swap = blocks[i];

        blocks[i] = blocks[j];
        blocks[j] = swap;
        return blocks;
    }

    /**
     * string representation of this board (in the output format specified below)
     * 
     * @return a String representing this board
     */
    public String toString() {
        StringBuilder board = new StringBuilder();

        board.append(n + "\n");
        for (int i = 0; i < blocks1D.length; i++) {
            board.append(String.format("%2d ", blocks1D[i]));
            if ((i + 1) % n == 0) board.append("\n");
        }
        return board.toString();
    }

    /**
     * Unit tests the {@code Board} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // In in = new In(args[0]);
        
        In in = new In("8puzzle/puzzle2x2-01.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        StdOut.println(initial.dimension());
        StdOut.println(initial.toString());
        StdOut.println(initial.hamming());
        StdOut.println(initial.manhattan());
        StdOut.println(initial.twin().toString());
        for (Board nb : initial.neighbors())
            for (Board nbb : nb.neighbors())
            StdOut.println(nbb.toString());
    }
}