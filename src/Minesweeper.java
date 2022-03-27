import java.util.Scanner;

/**
 * This class represents a minesweeper game
 *
 * @author Ryan Wong
 */
public class Minesweeper {

    /** A Scanner object */
    private Scanner scan;
    /** A Board object */
    private Board board;

    /** Constructs a Minesweeper object and initializes the scan object */
    public Minesweeper()
    {
        scan = new Scanner(System.in);
    }

    /** Starts the game */
    public void playGame()
    {
        U.println("Welcome to Minesweeper!");
        Board exampleBoard = new Board("Tutorial");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        U.println("Tutorial:");
        while (true)
        {
            System.out.println();
            U.println("To choose a spot to uncover, input the row it is in, followed by a space, followed by the column it is in.");
            U.println("To choose a spot to flag OR unflag, type F, followed by a space, followed by the row it is in, followed by a space, followed by the column it is in.");
            U.println("For example: if the board is ");
            System.out.println();
            exampleBoard.displayBoard();
            System.out.println();
            U.println("To choose the most bottom left spot, you would input \"3 1\" without the quotation marks.");
            U.println("If you would like to flag/unflag the bottom left spot, you would input \"F 3 1\" without the quotation marks.");
            U.sleep(2000);
            System.out.println("Legend: ");
            System.out.println("?: Squares you have not uncovered");
            System.out.println("0, 1, 2 etc...: How many mines are in the adjacent 8 squares");
            System.out.println("F: Squares you have flagged");
            System.out.println("M: Mine that you have uncovered (meaning you lost)");
            U.sleep(2000);
            String choice = "";
            while (true)
            {
                U.print("Do you want this tutorial to be repeated (Y/N)? ");
                choice = scan.nextLine();
                if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes"))
                {
                    break;
                }
                U.println("That is not a valid option!\n");
            }
            if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No"))
            {
                break;
            }
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        while (true)
        {
            System.out.println("Beginner: 8x8 board, 10 mines");
            System.out.println("Intermediate: 12x12 board, 22 mines");
            System.out.println("Expert: 16x16 board, 40 mines");
            U.print("Choose what difficulty you would like: ");

            String difficulty = "";
            while (true)
            {
                String choice = scan.nextLine();
                if (choice.equalsIgnoreCase("Beginner") || choice.equalsIgnoreCase("Intermediate") || choice.equalsIgnoreCase("Expert"))
                {
                    difficulty = choice;
                    break;
                }
                else
                {
                    U.println("That is not a valid difficulty!\n");
                    System.out.println("Beginner: 8x8 board, 10 mines");
                    System.out.println("Intermediate: 12x12 board, 22 mines");
                    System.out.println("Expert: 16x16 board, 40 mines");
                    U.print("What difficulty would you like? ");
                }
            }

            board = new Board(difficulty);
            System.out.println();

            boolean gameOver = false;
            String tempIndex;

            while (true)
            {
                board.displayBoard();
                System.out.println();
                U.print("Pick a spot to uncover: ");
                tempIndex = scan.nextLine();
                if (isIndexValid(tempIndex))
                {
                    break;
                }
                U.println("That is not a valid index!\n");
            }
            Index firstIndex = new Index(tempIndex);
            board.addGuessedIndexes(firstIndex);
            board.setBoard(firstIndex);
            board.setDisplayedBoard(firstIndex);

            while (!gameOver)
            {
                String input = "";
                while(true)
                {
                    System.out.println();
                    board.displayBoard();
                    System.out.println();
                    U.print("Pick a spot to uncover or flag: ");
                    input = scan.nextLine();
                    if (isIndexValid(input))
                    {
                        break;
                    }
                    U.println("That is not a valid index!");
                }
                Index index = new Index(input);
                if (index.isFlag())
                {
                    boolean alreadyAFlag = false;
                    for (int i = 0; i < board.getFlaggedIndexes().size(); i++)
                    {
                        if (board.getFlaggedIndexes().get(i).getRow() == index.getRow() && board.getFlaggedIndexes().get(i).getCol() == index.getCol())
                        {
                            alreadyAFlag = true;
                            board.removeFlaggedIndex(i);
                            board.setDisplayedBoard(index.getRow(), index.getCol());
                            break;
                        }
                    }
                    if (!alreadyAFlag)
                    {
                        board.addFlaggedIndexes(index);
                        board.setDisplayedBoard(index);
                    }
                }
                else
                {
                    board.addGuessedIndexes(index);
                    board.setDisplayedBoard(index);
                }
                //printing out mine map for testing purposes
//                for (String[] row : board.getBoard())
//                {
//                    for (String str : row)
//                    {
//                        System.out.print(str + " ");
//                    }
//                    System.out.println();
//                }
//                for (Index ind : board.getMineIndexes())
//                {
//                    System.out.print("(" + ind.getRow() + ", " + ind.getCol() + ") | ");
//                }
//                System.out.println();
//                for (Index ind : board.getGuessedIndexes())
//                {
//                    System.out.print("(" + ind.getRow() + ", " + ind.getCol() + ") | ");
//                }
                System.out.println();
                if (checkLoss(index) && !index.isFlag())
                {
                    System.out.println();
                    board.displayBoard();
                    System.out.println();
                    U.println("You hit a mine!");
                    U.println("You lose!");
                    gameOver = true;
                }
                if (checkWin())
                {
                    System.out.println();
                    board.displayBoard();
                    System.out.println();
                    U.println("You have flagged all the mines!");
                    U.println("You win!");
                    gameOver = true;
                }
            }
            String option = "";
            while (true)
            {
                U.print("Would you like to play again (Y/N)? ");
                option = scan.nextLine();
                if (option.equalsIgnoreCase("Y") || option.equalsIgnoreCase("Yes"))
                {
                    System.out.println();
                    break;
                }
                else if (option.equalsIgnoreCase("N") || option.equalsIgnoreCase("No"))
                {
                    U.println("Alright, thanks for playing!");
                    System.exit(0);
                }
                else
                {
                    U.println("That is not a valid option!\n");
                }
            }
        }
    }

    /**
     * Checks if the player has won
     *
     * @return True if player has won, false if player has not won yet
     */
    private boolean checkWin()
    {
        if (board.getFlaggedIndexes().size() != board.getMineIndexes().size())
        {
            return false;
        }
        else
        {
            boolean tf = false;
            for (int i = 0; i < board.getMineIndexes().size(); i++)
            {
                for (int j = 0; j < board.getFlaggedIndexes().size(); j++)
                {
                    if (board.getMineIndexes().get(i).getRow() == board.getFlaggedIndexes().get(j).getRow() && board.getMineIndexes().get(i).getCol() == board.getFlaggedIndexes().get(j).getCol())
                    {
                        tf = true;
                    }
                }
                if (!tf)
                {
                    return false;
                }
                tf = false;
            }
        }
        return true;
    }

    /**
     * Checks if an index is a mine
     *
     * @param index The index being checked
     *
     * @return True if player has lost, false if player has not lost yet
     */
    private boolean checkLoss(Index index)
    {
        for (Index mineIndex : board.getMineIndexes())
        {
            if (mineIndex.getRow() == index.getRow() && mineIndex.getCol() == index.getCol())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if player input is valid
     *
     * @param index The input being checked
     *
     * @return True if player input is valid, false if player input is invalid or already guessed
     */
    private boolean isIndexValid(String index)
    {
        String[] arr = index.split(" ");
        int row;
        int col;
        if (index.substring(0, 1).equalsIgnoreCase("F"))
        {
            try
            {
                row = Integer.parseInt(arr[1]);
                col = Integer.parseInt(arr[2]);
            }
            catch (Exception e)
            {
                row = 100;
                col = 100;
            }
        }
        else
        {
            try
            {
                row = Integer.parseInt(arr[0]);
                col = Integer.parseInt(arr[1]);
            }
            catch (Exception e)
            {
                row = 100;
                col = 100;
            }
        }
        for (Index ind : board.getGuessedIndexes())
        {
            if (ind.getRow() == row-1 || row == 100)
            {
                if (ind.getCol() == col-1 || col == 100)
                {
                    return false;
                }
            }
        }
        return row <= board.getSize() && col <= board.getSize() && row >= 1 && col >= 1;
    }
}
