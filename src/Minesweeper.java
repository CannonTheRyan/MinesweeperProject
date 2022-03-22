import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Minesweeper {

    private Scanner scan;
    private Board board;

    public Minesweeper()
    {
        scan = new Scanner(System.in);
    }

    public void playGame()
    {
//        U.println("Welcome to Minesweeper!");
//        Board exampleBoard = new Board("Tutorial");
//        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
//        U.println("Tutorial");
//        while (true)
//        {
//            System.out.println();
//            U.println("To choose a spot to uncover, input the row it is in, followed by a space, followed by the column it is in.");
//            U.println("To choose a spot to flag OR unflag, type F, followed by a space, followed by the row it is in, followed by a space, followed by the column it is in.");
//            U.println("For example: if the board is ");
//            System.out.println();
//            exampleBoard.displayBoard();
//            System.out.println();
//            U.println("To choose the most bottom left spot, you would input \"3 1\" without the quotation marks.");
//            U.println("If you would like to flag/unflag the bottom left spot, you would input \"F 3 1\" without the quotation marks.");
//            U.sleep(2000);
//            System.out.println("Legend: ");
//            System.out.println("?: Squares you have not uncovered");
//            System.out.println("0, 1, 2 etc...: How many mines are in the adjacent 8 squares");
//            System.out.println("F: Squares you have flagged");
//            System.out.println("M: Mine that you have uncovered (meaning you lost)");
//            U.sleep(2000);
//            U.print("Do you want this tutorial to be repeated (Y/N)? ");
//            String choice = scan.nextLine();
//            if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No"))
//            {
//                break;
//            }
//        }
//
//        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Beginner: 8x8 board, 10 mines");
        System.out.println("Intermediate: 12x12 board, 22 mines");
        System.out.println("Expert: 16x16 board, 40 mines");
        U.print("Now, choose what difficulty you would like: ");

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
                U.println("That is not a valid difficulty!");
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
            U.println("Pick a spot to uncover: ");
            tempIndex = scan.nextLine();
            if (isIndexValid(tempIndex))
            {
                break;
            }
            U.println("That is not a valid index!");
        }
        Index firstIndex = new Index(tempIndex);
        board.addGuessedIndexes(firstIndex);
        board.setBoard(firstIndex);

        while (!gameOver)
        {

//                board.displayBoard();
//                System.out.println();
//                U.print("Pick a spot to uncover or to flag: ");
//                String index = scan.nextLine();
//                if (firstTry && isIndexValid(index))
//                {
//                    firstTry = false;
//                    board.setBoard(index);
//                }
//                else if (!isIndexValid(index))
//                {
//                    System.out.println("That is not a valid index! Try again!");
//                }
//                else
//                {
//                    processOption(index);
//                }



        }
    }

    private boolean checkWin()
    {
        return (board.getMineIndexes().containsAll(board.getFlaggedIndexes()) && board.getFlaggedIndexes().containsAll(board.getMineIndexes()));
    }

    private boolean isIndexValid(String index)
    {
        String[] arr = index.split(" ");
        int row;
        int col;
        if (index.substring(0, 1).equalsIgnoreCase("F"))
        {
            row = Integer.parseInt(arr[1]);
            col = Integer.parseInt(arr[2]);
        }
        else
        {
            row = Integer.parseInt(arr[0]);
            col = Integer.parseInt(arr[1]);
        }
        return row <= board.getSize() && col <= board.getSize() && row >= 1 && col >= 1;
    }

    public Board getBoard()
    {
        return board;
    }
}
