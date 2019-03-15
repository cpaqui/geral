package snippets.challenger;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BitMask {

    private static final int CREATE = 1;//01
    private static final int RETRIEVE = 2;//10
    private static final int UPDATE = 4;//100
    private static final int DELETE = 8;

    public static void main(String[] args) {
        bigIntegerBitWise();
    }

    public static void main2 (String[] args) {

//        int count = 0;
//        for (int i = 1; i < 2048; i=i*2) {
//            System.out.println(String.format("%d - %s", i, Integer.toBinaryString(i)));
//            count += i;
//        }
//
        int count = 170;
        System.out.println(String.format("%d - %s", count, Integer.toBinaryString(count)));
        System.out.println((170 & 2) == 2);
    }


    private static void reduce () {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6);

        int reduce = intStream.filter(e -> e % 2 == 0).reduce(10, (a, b) -> {return  a + b;});

        Function<Integer[], Integer> f = (arr) -> {
            return Arrays.asList(arr).stream().filter(e -> e % 2 == 0).reduce(0, (a, b) -> a + b);
        };

//        IntStream intStream2 = IntStream.of(1, 2, 3, 4, 5, 6);
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        List<Integer> ints = Arrays.asList(arr);
        Integer apply = f.apply(arr);

        System.out.println(String.format("reduce= %d, apply= %d", reduce, apply));


        Integer[] sourceArray = { 0, 1, 2, 3, 4, 5 };
        List<Integer> targetList = Arrays.asList(sourceArray);

    }


    private static void bigIntegerBitWise () {
//        BigInteger bitMask = BigDecimal.valueOf(Math.pow(2, 63)).toBigInteger();
//        BigInteger bitMask = BigInteger.valueOf(16);
        BigInteger bitMask = new BigInteger("1000000000000000000000000000000000000000000000000000000000000001", 2);

        System.out.println(String.format("%s, %s, %s, %s", bitMask.toString(), bitMask.toString(2), bitMask.bitLength(), bitMask.testBit(63)));
    }

    private static void bigIntegerBitWise2 () {
        BigInteger bitMask = BigDecimal.valueOf(Math.pow(2, 31)-1).toBigInteger();
        BigInteger bigInteger = BigInteger.valueOf(Integer.MAX_VALUE);

        System.out.println(String.format("%s, %s", bitMask.toString(), bigInteger.toString()));
    }
}
