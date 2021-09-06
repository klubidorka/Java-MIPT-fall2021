package main;

public class Tasks {
    public static void main(String[] args) {
        problem1();
        //problem2();
    }

    static void problem1(){
        int a = 2;
        a = ++a + ++a + ++a;
        System.out.println(a);
    }

    static void problem2(){
        Integer num1 = new Integer(120);
        Integer num2 = new Integer(120);
        Integer num3 = 110;
        Integer num4 = 110;
        Integer num5 = 210;
        Integer num6 = 210;
        System.out.println(num1 == num2);
        System.out.println(num3 == num4);
        System.out.println(num5 == num6);
    }
}

