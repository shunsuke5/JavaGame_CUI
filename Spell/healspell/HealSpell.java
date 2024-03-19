package spell.healspell;
import battlechar.brave.*;
import spell.Spell;
import battlechar.enemy.Enemy;

public class HealSpell extends Spell {

    // コンストラクタ
    public HealSpell(String name) {
        super(name);
    }
    // メソッド
    public void resite(Brave b, Enemy e) {                       // 指定した範囲からランダムにポイントを生成
        if (b.getMp() < getConsumptionMp()) {
            System.out.println("MPがたりない！");
            return;
        }
        System.out.println(b.getName() + "は" + getName() + "をとなえた！");
        int healPoint = new java.util.Random().nextInt(getPointRange()) + getMinPoint();
        controlHeal(b, healPoint);
        System.out.println(b.getName() + "のHPを" + healPoint + "ポイントかいふくした！");
    }
    public void controlHeal(Brave b, int healPoint) {     // 回復量が最大HPを超えないように調整
        if (healPoint > (b.getMaxHp() - b.getHp())) {
            b.setHp(b.getMaxHp());
        } else {
            b.setHp(b.getHp() + healPoint);
        }
    }
}