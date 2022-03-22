
import java.util.ArrayList;

public class Board {
    private String[][] board;
    private String[][] displayedBoard;
    private int size;
    private int mines;
    private ArrayList<Index> guessedIndexes;
    private ArrayList<Index> flaggedIndexes;
    private ArrayList<Index> mineIndexes;

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
            }
        }
        size = board.length;
        guessedIndexes = new ArrayList<Index>();
        flaggedIndexes = new ArrayList<Index>();
        mineIndexes = new ArrayList<Index>();
    }

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

    public void setBoard(Index index)
    {
        int usedRow = index.getRow();
        int usedCol = index.getCol();
        int placedMines = 0;
        while (placedMines < mines)
        {
            int row = U.random(0, size-1);
            int col = U.random(0, size-1);
            Index tempIndex = new Index("" + row + " " + col);
            boolean alreadyAMine = false;
            for (Index ind : mineIndexes)
            {
                if ((ind.getRow() == row && ind.getCol() == col) || (ind.getRow() == usedRow && ind.getCol() == usedCol))
                {
                    alreadyAMine = true;
                }
            }
            if (!alreadyAMine)
            {
                mineIndexes.add(tempIndex);
                board[row][col] = "M";
                placedMines++;
            }
        }
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                int amountOfMines = 0;
                if (board[row][col].equals(null))
                {
                    if (board[row-1][col-1].equals("M") && row-1 > 0 && row-1 < size && col-1 > 0 && col-1 < size) //idk if logic is right
                    {amountOfMines++;}
                    if (board[row-1][col].equals("M"))
                    {amountOfMines++;}
                    if (board[row-1][col+1].equals("M"))
                    {amountOfMines++;}
                    if (board[row][col-1].equals("M"))
                    {amountOfMines++;}
                    if (board[row][col+1].equals("M"))
                    {amountOfMines++;}
                    if (board[row+1][col-1].equals("M"))
                    {amountOfMines++;}
                    if (board[row+1][col].equals("M"))
                    {amountOfMines++;}
                    if (board[row+1][col+1].equals("M"))
                    {amountOfMines++;}
                    board[row][col] = "" + amountOfMines;
                }

            }
        }
    }

    public void setDisplayedBoard(int row, int col, String str)
    {
        displayedBoard[row][col] = str;
    }

    public int getSize()
    {
        return size;
    }

    public String[][] getBoard()
    {
        return board;
    }

    public ArrayList<Index> getFlaggedIndexes()
    {
        return flaggedIndexes;
    }

    public ArrayList<Index> getMineIndexes()
    {
        return mineIndexes;
    }

    public ArrayList<Index> getGuessedIndexes()
    {
        return guessedIndexes;
    }

    public void addFlaggedIndexes(Index index)
    {
        flaggedIndexes.add(index);
    }

    public void addGuessedIndexes(Index index)
    {
        guessedIndexes.add(index);
    }


}
