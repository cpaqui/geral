package snippets.challenger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.stream.IntStream;

public class ProjectEuler1 {

    public static void main(String[] args) {
        int sum = 0;

        System.out.println(new Timestamp(new Date().getTime()));
        sum = IntStream.range(3, 1000).filter(i -> ((i%3)==0) || ((i%5)==0)).reduce(0, (a,b) -> a+b);
        System.out.println(sum);
        System.out.println(new Timestamp(new Date().getTime()));

        sum = 0;
        System.out.println(new Timestamp(new Date().getTime()));
        for (int i = 3; i < 1000000000; i++) {
            if ((i%3)==0 || ((i%5)==0)){
                sum += i;
            }
        }
        System.out.println(sum);
        System.out.println(new Timestamp(new Date().getTime()));

    }
}
