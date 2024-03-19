package spell.attackspell;
import battlechar.brave.Brave;
import battlechar.enemy.Enemy;
import spell.Spell;

public class AttackSpell extends Spell {
    // コンストラクタ
    public AttackSpell(String name) {
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
        controlEnemyHp(e, attackPoint);
        System.out.println(e.getName() + "に" + attackPoint + "のダメージ！");
    }
    public void controlEnemyHp(Enemy e, int attackPoint) {     // 敵HPが0を下回らないように調整
        if (attackPoint > e.getHp()) {
            e.setHp(0);
        } else {
            e.setHp(e.getHp() - attackPoint);
        }
    }
}
