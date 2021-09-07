package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
    // sout + enter -> System.out.println();
    // souf + enter -> System.out.printf("");
    // psvm + enter -> public static void main(String[] args) {}

    // Static fields do not depend on class instance
    static int a = 4;

    // Non-static fields depend on class instance
    int b = 3;

    // Static methods can't access non-static fields
    public static void printA(){
        System.out.println(a);
    }


    static void PrimitiveTypesDemo(){
        int a = 100;

        //long b = 1000000000000; // not OK
        long c = 1000000000000L; // OK

        long underscores = 1_000_000L;

        int oct = 010;
        int hex = 0x10;
    }

    static void TypesCast(){
        int a = 100;
        double b = a;

        int c = (int)b; // types cast
    }

    static void Literals(){
        double inf = Double.POSITIVE_INFINITY;
        double minus_inf = Double.NEGATIVE_INFINITY;
        double nan = Double.NaN;

        System.out.println(2.0 - 1.1);
    }

    static void Branches(){
        int b = 100;

        switch (b){
            case 100:
                // do smth
                break;
            case 1338:
                // do smth
                break;
            default:
                // do smth
        }
    }

    static void Cycles(){
        int k = 4;

        for (int i = 0; i < 3; i++){
            System.out.println(i);
        }

        while(k > 2){
            k++;
        }

        do{
            k++;
        } while(k < 10);

        // break -- leave cycle
        // continue -- skip current stage in cycle

    }

    static void Char(){
        // 16-bit UTF-16
        char ch = '\u03C0'; // pi number code
        System.out.println(ch);
    }

    static void inputScanner(){
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        scn.close();
    }

    static void inputBufferedReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        String b = br.readLine();
        br.close();
    }
}