/**
 * This class is an utility class
 *
 * @author Ryan Wong
 */
public class U {

    /**
     * Prints the string with a small interval of 20ms between letters
     *
     * @param str The string being printed
     */
    public static void print(String str) {
        for (int i = 0; i < str.length(); i++) {
            try {
                System.out.print(str.substring(i, i+1));
                Thread.sleep(20);
            }
            catch (InterruptedException e) {
                System.out.println("No interruptions thank you");
            }
        }
    }

    /**
     * Prints the string with a small interval of 20ms between letters and moves to the next line
     *
     * @param str The string being printed
     */
    public static void println(String str) {
        print(str);
        System.out.println();
    }

    /**
     * Sleeps for a certain amount of time
     *
     * @param ms The amount of milliseconds to sleep
     */
    public static void sleep(int ms) {
        try{
            Thread.sleep(ms);
        }
        catch (InterruptedException e){

        }
    }

    /**
     * Creates a random number between min and max inclusive
     *
     * @param min The minimum number wanted
     * @param max The maximum number wanted
     *
     * @return A random number between min and max
     */
    public static int random(int min, int max)
    {
        return (int) (Math.random() * (max-min+1)) + min;
    }
}
