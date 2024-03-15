package spell.healspell;
import brave.*;

public class HealSpell extends Spell {
    // 回復呪文一覧
    public static int pyoimi() {    // ピョイミ
        int healPoint = createPoint(7,8);
        return healPoint;
    }
    public static int bepyoimi(int level) {  // ベピョイミ
        if (checkLevel(level, 9)) {
            int healPoint = createPoint(20, 16);
            return healPoint;
        } else {
            return 0;
        }
    }
    // メソッド
    private static int createPoint(int minimum, int range) {     // 指定した範囲からランダムにポイントを生成
        int point = new java.util.Random().nextInt(range) + minimum;
        return point;
    }
    private static boolean checkLevel(int level, int border) {   // 呪文を使えるレベルならtrueを返す
        if (level > border) {
            return true;
        } else {
            return false;
        }
    }
}