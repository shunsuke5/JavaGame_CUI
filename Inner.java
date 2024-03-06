class Inner {
    static int num;

    static {
        num = 5;
    }

    // メンバクラス
    class Inclass {
        void methodA() {
            // 外部クラス「Inner」の、インスタンス変数
            num = 10;
            System.out.println(num);
        }
    }
}