package Spell;

public class AttackSpell extends Spell {
    private int attackPoint;

    // コンストラクタ
    public AttackSpell(String name, String explanation, int attackPoint) {
        super(name,explanation);
        this.attackPoint = attackPoint;
    }
    // 攻撃呪文
    public void mera() {        // メラ

    }
    public void merami() {      // メラミ

    }
    public void merazoma() {    // メラゾーマ

    }
    // アクセサ
    public int getAttackPoint() { return this.attackPoint; }
    public void setAttackPoint(int attackPoint) { this.attackPoint = attackPoint; }
}
