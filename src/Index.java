/**
 * This class represents an Index on the board
 *
 * @author Ryan Wong
 */
public class Index
{

    /** The row of the index */
    private int row;
    /** The column of the index */
    private int col;
    /** If the index is a flag */
    private boolean flag;

    /**
     * Constructs an Index object and initializes row, col, and flag
     *
     * @param input The player input being parsed into an Index object
     */
    public Index(String input)
    {
        String[] arr = input.split(" ");
        if (input.substring(0, 1).equalsIgnoreCase("F"))
        {
            try
            {
                row = Integer.parseInt(arr[1]) - 1;
                col = Integer.parseInt(arr[2]) - 1;
            }
            catch (Exception e)
            {

            }
            flag = true;
        }
        else
        {
            try
            {
                row = Integer.parseInt(arr[0]) - 1;
                col = Integer.parseInt(arr[1]) - 1;
            }
            catch (Exception e)
            {

            }
            flag = false;
        }
    }

    /**
     * Getter method for the row
     *
     * @return row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Getter method for the column
     *
     * @return col
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Getter method to check if index is a flag
     *
     * @return True if index is a flag, false if index is not a flag
     */
    public boolean isFlag()
    {
        return flag;
    }
}
