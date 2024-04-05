package spell.attackspell;
import state.State;

import battlechar.BattleChar;
import spell.Spell;

public class AttackSpell extends Spell {
    // コンストラクタ
    public AttackSpell(String name) {
        super(name);
    }

    // メソッド
    public void resite(BattleChar user, BattleChar receiver) {              // 指定した範囲からランダムにポイントを生成
        if (user.getMp() < getConsumptionMp()) {
            if (user.toString().equals("Brave")) {
                System.out.println("MPがたりない！");
            } else {
                System.out.println("しかしMPがたりなかった！");
            }
            return;
        }
        System.out.println(user.getName() + "は" + getName() + "をとなえた！");
        int attackPoint = new java.util.Random().nextInt(getPointRange()) + getMinPoint();
        controlReceiverHp(receiver, attackPoint);
        System.out.println(receiver.getName() + "に" + attackPoint + "のダメージ！");
    }
    public void controlReceiverHp(BattleChar receiver, int attackPoint) {     // 敵HPが0を下回らないように調整
        if (attackPoint > receiver.getHp()) {
            receiver.setHp(0);
        } else {
            receiver.setHp(receiver.getHp() - attackPoint);
        }
    }
    public void resite(BattleChar user, BattleChar receiver, State state, int probability) {}
}
