import enemy.Enemy;

public class Event {

    public static void startEvent() {
        System.out.println("あなたは勇者です。");
        System.out.println("魔物に奪われたこの世界にある3つの神聖な地を取り返す宿命を背負い旅に出ました。");
    }

    public static void chooseMap() {

    }

    public static void chooseMapAction() {

    }

    public static void battle(Brave b, Enemy e) {
        System.out.println(e.getName() + "があらわれた！");

        do {
            if (b.getAgility() > e.getAgility()) {
                // b.turn;
                // e.turn;
            } else {
                // e.turn;
                // b.turn;
            }
        } while (b.getHp() <= 0 || e.getHp() <= 0);
        // 「戦い」が結果として返すものは「勝敗」
        // つまり戻り値はbooleanにすればよい？
        // 勇者が勝ったらtrue,敵が勝ったらfalseにする
    }
}