import java.util.Scanner;

public class Minesweeper {

    private Scanner scan;

    public Minesweeper()
    {
        scan = new Scanner(System.in);
    }
    public void playGame()
    {
        U.println("Welcome to Minesweeper!");
        System.out.println("-----------------------");
        System.out.println("Beginner: 8x8 board, 10 mines");
        System.out.println("Intermediate: 12x12 board, 22 mines");
        System.out.println("Expert: 16x16 board, 40 mines");
        U.print("What difficulty would you like? ");
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

        Board board = new Board(difficulty);
    }
}
