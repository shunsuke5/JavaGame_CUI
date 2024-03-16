package spell.attackspell;
import java.io.IOException;

import brave.Brave;
import enemy.Enemy;
import spell.Spell;

public class AttackSpell extends Spell {
    // コンストラクタ
    public AttackSpell(String name) throws IOException {
        super(name);
    }

    // メソッド
    public void resite(Brave b, Enemy e) {              // 指定した範囲からランダムにポイントを生成
        if (b.getMp() < getConsumptionMp()) {
            System.out.println("MPがたりない！");
            return;
        }
        System.out.println(b.getName() + "は" + getName() + "をとなえた！");
        int attackPoint = new java.util.Random().nextInt(getPointRange()) + getMinPoint();
        controlDamage(e, attackPoint);
        System.out.println(e.getName() + "に" + attackPoint + "のダメージ！");
    }
    public void controlDamage(Enemy e, int attackPoint) {     // 回復量が最大HPを超えないように調整
        if (attackPoint > e.getHp()) {
            e.setHp(0);
        } else {
            e.setHp(e.getHp() - attackPoint);
        }
    }
}
