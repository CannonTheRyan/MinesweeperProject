public class Index
{
    private int row;
    private int col;
    private boolean flag;

    public Index(String input)
    {
        String[] arr = input.split(" ");
        if (input.substring(0, 1).equalsIgnoreCase("F"))
        {
            row = Integer.parseInt(arr[1]) - 1;
            col = Integer.parseInt(arr[2]) - 1;
            flag = true;
        }
        else
        {
            row = Integer.parseInt(arr[0]) - 1;
            col = Integer.parseInt(arr[1]) - 1;
            flag = false;
        }
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public boolean isFlag()
    {
        return flag;
    }
}
