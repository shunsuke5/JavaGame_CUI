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

        boolean a = false;
        int num10 = 10;
        int num20 = 20;
        int action = 1;
        while(!a) {
            System.out.println("while--start");
            if (num10 < num20) {
                System.out.println("if--start");
                switch(action) {
                    case 1:
                        System.out.println("switch");
                        a = true;
                        continue;
                }
                a = true;
                System.out.println("if--end");
                continue;
            }
            System.out.println("while--end");
        }
    }
}