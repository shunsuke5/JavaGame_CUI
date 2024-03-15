package spell.attackspell;

public class AttackSpell extends Spell {
    private int attackPoint;

    // 攻撃呪文
    public static int myora(int level) {        // ミョラ
        if (checkLevel(level, 5)) {
            int attackPoint = createPoint(6, 6);
            return attackPoint;
        } else {
            return 0;
        }
    }
    public static int myorami(int level) {      // ミョラミ
        if (checkLevel(level, 14)) {
            int attackPoint = createPoint(16, 11);
            return attackPoint;
        } else {
            return 0;
        }
    }
    public static int myorazoma(int level) {    // ミョラゾーマ
        if (checkLevel(level, 17)) {
            int attackPoint = createPoint(28, 16);
            return attackPoint;
        } else {
            return 0;
        }
    }
    // メソッド
    public static int createPoint(int minimum, int range) {     // 指定した範囲からランダムにポイントを生成
        int point = new java.util.Random().nextInt(range) + minimum;
        return point;
    }
    public static boolean checkLevel(int level, int border) {
        if (level > border) {
            return true;
        } else {
            return false;
        }
    }
    // アクセサ
    public int getAttackPoint() { return this.attackPoint; }
    public void setAttackPoint(int attackPoint) { this.attackPoint = attackPoint; }
}
