
import java.util.ArrayList;

public class Board {
    private String[][] board;
    private String[][] displayedBoard;
    private int size;
    private ArrayList<String> guessedIndexes;
    private ArrayList<String> flaggedIndexes;
    private ArrayList<String> mineIndexes;

    public Board(String difficulty)
    {
        if (difficulty.equalsIgnoreCase("Beginner"))
        {
            board = new String[8][8];
            displayedBoard = new String[8][8];
        }
        else if (difficulty.equalsIgnoreCase("Intermediate"))
        {
            board = new String[12][12];
            displayedBoard = new String[12][12];
        }
        else if (difficulty.equalsIgnoreCase("Expert"))
        {
            board = new String[16][16];
            displayedBoard = new String[16][16];
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
        guessedIndexes = new ArrayList<String>();
        flaggedIndexes = new ArrayList<String>();
        mineIndexes = new ArrayList<String>();
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

    public ArrayList<String> getFlaggedIndexes()
    {
        return flaggedIndexes;
    }

    public ArrayList<String> getMineIndexes()
    {
        return mineIndexes;
    }

}
