public class Event {
    private String story;

    public static void startEvent() {
        System.out.println("あなたは勇者です。");
        System.out.println("魔物に奪われたこの世界にある3つの神聖な地を取り返す宿命を背負い旅に出ました。");
    }
    public static void chooseMap(Brave b) {
        System.out.println("どのマップにいきますか？");
        System.out.println("山:1 海:2 森:3");
        System.out.print("つぎにいくのは…->\s");
        // ここで現在いるマップは表示しないようにしたい、もしくは
        // 現在いるマップを選択したらもう一度マップアクションをやりなおさせたい
        int number = new java.util.Scanner(System.in).nextInt();
        System.out.println(b.getName() + "は" + "〇〇(変数)へむかった！");
    }
    public static void chooseMapAction() {
        System.out.println("どのこうどうにする？");
        System.out.print("""
                てきをさがす：1
                おたからをさがす：2
                やすむ：3
                べつのマップへいく：4

                ->\s
                """);
    }
    public static void killEnemy(Enemy e) {
        System.out.println(e.getName() + "をたおした！");
        System.out.println(e.getPoint() + "ポイントのけいけんちをかくとく！");
    }
    public static void forestEvent() {

    }
    public static void seaEvent() {
        
    }
    public static void mountainEvent() {
        
    }
}