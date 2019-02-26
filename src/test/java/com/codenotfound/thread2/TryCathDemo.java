package com.codenotfound.thread2;

public class TryCathDemo {

    public static void main(String[] args) {
        System.out.println( "   1 >>>>>>>>>>>>>>>> " + func1(1) );
        System.out.println( "   1 >>>>>>>>>>>>>>>> " + func1(0) );

        System.out.println( "   2 >>>>>>>>>>>>>>>> " + func2(1) );
        System.out.println( "   2 >>>>>>>>>>>>>>>> " + func2(0) );
    }

    static boolean func1(int param) {
        try {
            int result = 1 / param;
            return true;
        } catch (ArithmeticException ex) {
            return false;
        }
    }

    static boolean func2(int param) {
        try {
            int result = 1 / param;
            return true;
        } catch (ArithmeticException ex) {

        }
        return false;
    }
}
