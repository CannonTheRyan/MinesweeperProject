// U stands for Utility
public class U {

    public static void print(String str) {
        for (int i = 0; i < str.length(); i++) {
            try {
                System.out.print(str.substring(i, i+1));
                Thread.sleep(40);
            }
            catch (InterruptedException e) {
                System.out.println("No interruptions thank you");
            }
        }
    }

    public static void println(String str) {
        print(str);
        System.out.println();
    }

    public static void sleep(int ms) {
        try{
            Thread.sleep(ms);
        }
        catch (InterruptedException e){

        }
    }

    public static int random(int min, int max)
    {
        return (int) (Math.random() * (max-min+1)) + min;
    }
}
