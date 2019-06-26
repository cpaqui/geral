package snippets;
import  java.lang.String;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.print("\b");
            System.out.print("|");
            Thread.sleep(200);
            System.out.print("\b");
            System.out.print("/");
            Thread.sleep(200);
            System.out.print("\b");
            System.out.print("|");
            Thread.sleep(200);
            System.out.print("\b");
            System.out.print("\\");
            Thread.sleep(200);
        }
    }

   /* public static void main2(String[] args) {
        String a = "A ";
        a = a.concat("B ");
        String b = "C ";
        a = a.concat(b);
        a.replace('C', 'D');
        a = a.concat("C ");
        System.out.println(a);

        boolean boo = new Boolean(null);

        System.out.println(boo);

        float f = 0;
        char c;

        System.out.println("" + f);
        System.out.println("" + c);


    }*/
}
