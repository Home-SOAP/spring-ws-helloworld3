package com.codenotfound.thread2;

public class TryCathDemo {

    public static void main(String[] args) {
        System.out.println( "   >>>>>>>>>>>>>>>> " + func(1) );
        System.out.println( "   >>>>>>>>>>>>>>>> " + func(0) );
    }

    static boolean func(int param) {
        try {
            int result = 1 / param;
            return true;
        } catch (ArithmeticException ex) {
            return false;
        }
    }

}
