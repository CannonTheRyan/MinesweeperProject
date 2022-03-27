import java.util.ArrayList;

/**
 * This class represents the minesweeper board
 *
 * @author Ryan Wong
 */
public class Board {

    /** A 2D array of the answer board */
    private String[][] board;
    /** A 2d array of the board the player sees */
    private String[][] displayedBoard;
    /** The length/width of the board */
    private int size;
    /** The amount of mines on the board */
    private int mines;
    /** A list of the indexes of player guesses */
    private ArrayList<Index> guessedIndexes;
    /** A list of the indexes of player flags */
    private ArrayList<Index> flaggedIndexes;
    /** A list of the indexes of mines on the board */
    private ArrayList<Index> mineIndexes;

    /**
     * Constructs a Board object and initializes the board, displayedBoard, mines, size, guessedIndexes, flaggedIndexes, and mineIndexes based on the difficulty
     *
     * @param difficulty The difficulty of the game (Beginner, Intermediate, Expert)
     */
    public Board(String difficulty)
    {
        if (difficulty.equalsIgnoreCase("Beginner"))
        {
            board = new String[8][8];
            displayedBoard = new String[8][8];
            mines = 10;
        }
        else if (difficulty.equalsIgnoreCase("Intermediate"))
        {
            board = new String[12][12];
            displayedBoard = new String[12][12];
            mines = 22;
        }
        else if (difficulty.equalsIgnoreCase("Expert"))
        {
            board = new String[16][16];
            displayedBoard = new String[16][16];
            mines = 40;
        }
        else if (difficulty.equalsIgnoreCase("Tutorial"))
        {
            board = new String[3][3];
            displayedBoard = new String[3][3];
        }
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < displayedBoard[0].length; j++)
            {
                displayedBoard[i][j] = "?";
                board[i][j] = " ";
            }
        }
        size = board.length;
        guessedIndexes = new ArrayList<Index>();
        flaggedIndexes = new ArrayList<Index>();
        mineIndexes = new ArrayList<Index>();
    }

    /** Displays the displayedBoard with indexes on the side */
    public void displayBoard()
    {
        System.out.print("X |");
        for (int i = 1; i <= board.length; i++)
        {
            if (i <= 9)
            {
                System.out.print("0");
            }
            System.out.print(i + "|");
        }
        System.out.println();
        for (int i = 1; i <= 3 * (board.length +1 ); i++)
        {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 1; i < board.length + 1; i++)
        {
            for (int j = -1; j < board.length; j++)
            {
                if (j == -1)
                {
                    if (i <= 9)
                    {
                        System.out.print("0");
                    }
                    System.out.print(i + "|");
                }
                else
                {
                    System.out.print(" " + displayedBoard[i-1][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Sets the mines and the numbers corresponding to the amount of mines nearby on the board after the player has made their first turn
     *
     * @param index The first index the player has inputted to not place a mine there
     */
    public void setBoard(Index index)
    {
        int usedRow = index.getRow();
        int usedCol = index.getCol();
        int placedMines = 0;
        while (placedMines < mines)
        {
            int row = U.random(1, size);
            int col = U.random(1, size);
            Index tempIndex = new Index("" + row + " " + col);
            boolean alreadyAMine = false;
            for (Index ind : mineIndexes)
            {
                if ((ind.getRow() == tempIndex.getRow() && ind.getCol() == tempIndex.getCol()) || (usedRow == tempIndex.getRow() && usedCol == tempIndex.getCol()))
                {
                    alreadyAMine = true;
                }
            }
            if (!alreadyAMine)
            {
                mineIndexes.add(tempIndex);
                board[row-1][col-1] = "M";
                placedMines++;
            }
        }
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                int amountOfMines = 0;
                if (!board[row][col].equals("M"))
                {
                    if (isIndexValid(row-1, col-1)) {
                        if (board[row - 1][col - 1].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    if (isIndexValid(row-1, col)) {
                        if (board[row - 1][col].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    if (isIndexValid(row-1, col+1)) {
                        if (board[row - 1][col + 1].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    if (isIndexValid(row, col-1)) {
                        if (board[row][col - 1].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    if (isIndexValid(row, col+1)) {
                        if (board[row][col + 1].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    if (isIndexValid(row+1, col-1)) {
                        if (board[row + 1][col - 1].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    if (isIndexValid(row+1, col)) {
                        if (board[row + 1][col].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    if (isIndexValid(row+1, col+1)) {
                        if (board[row + 1][col + 1].equals("M")) {
                            amountOfMines++;
                        }
                    }
                    board[row][col] = "" + amountOfMines;
                }
            }
        }
    }

    /**
     * Reveals an index on displayedBoard to a number or sets it to a flag
     *
     * @param index The index on the board to set
     */
    public void setDisplayedBoard(Index index)
    {
        if (index.isFlag())
        {
            displayedBoard[index.getRow()][index.getCol()] = "F";
        }
        else
        {
            displayedBoard[index.getRow()][index.getCol()] = board[index.getRow()][index.getCol()];
        }
    }

    /**
     *  Used only when unflagging an index
     *
     * @param row The row of the index
     * @param col The column of the index
     */
    public void setDisplayedBoard(int row, int col)
    {
        displayedBoard[row][col] = "?";
    }

    /**
     * Checks if the row and column in an index inputted go out of bounds of the board
     *
     * @param row The row of the index
     * @param col The column of the index
     *
     * @return True if in bounds, false if out of bounds
     */
    private boolean isIndexValid(int row, int col)
    {
        return row >= 0 && row <= size-1 && col >= 0 && col <= size-1;
    }

    /**
     * Adds an index to the flaggedIndexes list
     *
     * @param index The index being added
     */
    public void addFlaggedIndexes(Index index)
    {
        flaggedIndexes.add(index);
    }

    /**
     * Adds an index to the guessedIndexes list
     *
     * @param index The index being added
     */
    public void addGuessedIndexes(Index index)
    {
        guessedIndexes.add(index);
    }

    /**
     * Removes an index in the flaggedIndexes list
     *
     * @param index The index being removed
     */
    public void removeFlaggedIndex(int index)
    {
        flaggedIndexes.remove(index);
    }

    /**
     * Getter method for the size
     *
     * @return size
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Getter method for the flaggedIndexes list
     *
     * @return flaggedIndexes
     */
    public ArrayList<Index> getFlaggedIndexes()
    {
        return flaggedIndexes;
    }

    /**
     * Getter method for the mineIndexes list
     *
     * @return mineIndexes
     */
    public ArrayList<Index> getMineIndexes()
    {
        return mineIndexes;
    }

    /**
     * Getter method for the guessedIndexes list
     *
     * @return guessedIndexes
     */
    public ArrayList<Index> getGuessedIndexes()
    {
        return guessedIndexes;
    }
}
