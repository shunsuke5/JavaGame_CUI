package Spell;
import Brave;

public class HealSpell extends Spell {
    private int healPoint;

    // コンストラクタ
    public HealSpell(String name, String explanation, int healPoint) {
        super(name,explanation);
        this.healPoint = healPoint;
    }
    // 回復呪文一覧
    public void hoimi() {    // ホイミ

    }
    public void behoimi() {  // ベホイミ
        // レベルチェック
        if (b.getLevel() < 9) {
            return;
        }
        b.setHp(b.getHp() + 50);    // 一定の範囲でランダムな回復量にしたい
    }
    // アクセサ
    public int getHealPoint() { return this.healPoint; }
    public void setHealPoint(int healPoint) { this.healPoint = healPoint; }
}