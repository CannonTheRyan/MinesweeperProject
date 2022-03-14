import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    private String[][] board;
    private String[][] displayedBoard;
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
        else if (difficulty.equals("Expert"))
        {
            board = new String[16][16];
            displayedBoard = new String[16][16];
        }
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

}
