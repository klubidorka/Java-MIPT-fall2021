package StringSamples;

import StringSamples.DefinitelyNotString;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        StringDemo();
//        Concatenation();
//        CompareStrings();
//        Transformations();
//        SpeedTest();
    }

    static void StringDemo(){
        // "+" is overloaded for Strings

        String phrase = "This" + " " + "is" + " " + "Java";
        System.out.println(phrase);
    }

    static void Concatenation(){
        // When you concatenate String with an instance of another class compiler casts it to String

        Boolean bool = Boolean.TRUE;
        String result = "bool is " + bool;
        System.out.println(result);

        // Even for user-generated classes with toString() method
        DefinitelyNotString notString = new DefinitelyNotString();
        System.out.println("What is this? " + notString);
    }

    static void CompareStrings(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter first string");
        String first = in.nextLine();
        System.out.println("Enter second string");
        String second = in.nextLine();

        System.out.println(first == second); // false

        System.out.println(first.equals(second)); // true if entered strings are the same

        String a = "abc";
        String b = "abc";
        System.out.println(a == b); // true [string pool]
    }

    static void Transformations(){

        // String to int
        int integer = Integer.parseInt("123");

        // int to string
        String string = Integer.toString(integer);
    }

    // StringBuilder vs StringBuffer
    static void SpeedTest(){
        long bufferResult = 0;
        long builderResult = 0;
        try {
            bufferResult = test(new StringBuffer());
            builderResult = test(new StringBuilder());
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("StringBuffer: " + bufferResult + "ms.");
        System.out.println("StringBuilder: " + builderResult + "ms.");
        System.out.printf("StringBuilder is %f times faster.\n", (bufferResult + 0.0) / builderResult);
    }

    static long test(Appendable obj) throws java.io.IOException {
        long start = System.currentTimeMillis();

        for (int i = 0; i++ < 1e9; ) {
            obj.append("");
        }
        long stop = System.currentTimeMillis();
        return stop - start;
    }
}