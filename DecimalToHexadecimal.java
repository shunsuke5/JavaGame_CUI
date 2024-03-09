public class DecimalToHexadecimal {
    // 10進数を16進数に変換するプログラム
    public static void decimalToHexadecimal(int decimal) {  // 10進数を16進数に変換し表示する
        int a = (int) ( decimal / 16 );     // 1桁目の計算
        int b = decimal % 16;               // 2桁目の計算
        System.out.println("0x" + conversion(a,b));
    }
    public static String conversion(int a, int b) {         // 0x??の?を文字列に変換して返す
        String stra;
        String strb;

        if (jadgement(a)) {
            stra = numToAlpha(a);
        } else {
            stra = Integer.toString(a);
        }

        if (jadgement(b)) {
            strb = numToAlpha(b);
        } else {
            strb = Integer.toString(b);
        }

        return stra + strb;
    }
    public static boolean jadgement(int num) {              // 引数が英字になるか判断する
        return num > 9;
    }
    public static String numToAlpha(int a) {                // 引数に対応する英字を返す
        switch(a) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            default:
                return "F";
        }
    }
}
