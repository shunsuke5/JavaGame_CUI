public class Main {
    public static void main(String[] args) {
        /*
        
        // 主人公を生み出す
        Brave b = new Brave();
        // 冒険の始まり
        Event.startEvent();
        // マップ選択
        Event.chooseMap();
        // マップ内アクション
        b.chooseMapAction();
        
        */

        boolean buriburi = unko();
        if (buriburi) {
            System.out.println("ちがうだろーーー！！！");
        } else {
            System.out.println("なるほどぶりぶりぶりぶりぶり");
        }
    }
    public static boolean unko() {
        System.out.println("unkoがあらわれた！");
        System.out.print("どうする？ ->\s");
        int onara = new java.util.Scanner(System.in).nextInt();
        if (onara == 1) {
            return false;
        } else {
            return true;
        }
    }
}