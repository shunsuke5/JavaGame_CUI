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
        Event.chooseMapAction();
        // 全てのマップをクリアしたらラスボス戦
        Event.lastBattle();
        // ラスボスを倒せばゲームクリア
        Event.congratulations();
        
        */

        int a = 1;
        int b = 1;
        int i = 0;
        while(i < 3) {
            System.out.println("while--start");
            if (b == 1) {
                System.out.println("if--start");
                switch(a) {
                    case 1:
                        System.out.println("switch");
                        i++;
                        break;
                }
                System.out.println("if--end");
            }
            System.out.println("while--end");
        }
        System.out.println("全てのループが終了");
    }
}