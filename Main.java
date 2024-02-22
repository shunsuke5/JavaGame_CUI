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

        String str = """
                　　　てきをさがす：1
                　おたからをさがす：2
                　　　　　　やすむ：3
                ほかのマップへいく：4
                """;
        System.out.println(str);
        str += "　　ボスとたたかう：5";
        System.out.print(str + "\n\n\s->\s");
    }
}