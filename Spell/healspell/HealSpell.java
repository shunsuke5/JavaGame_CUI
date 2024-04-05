package spell.healspell;
import battlechar.BattleChar;
import spell.Spell;
import state.State;

public class HealSpell extends Spell {

    // コンストラクタ
    public HealSpell(String name) {
        super(name);
    }
    // メソッド
    public void resite(BattleChar user, BattleChar receiver) {                       // 指定した範囲からランダムにポイントを生成
        if (user.getMp() < getConsumptionMp()) {
            if (user.toString().equals("Brave")) {
                System.out.println("MPがたりない！");
            } else {
                System.out.println("しかしMPがたりなかった！");
            }
            return;
        }
        System.out.println(user.getName() + "は" + getName() + "をとなえた！");
        int healPoint = new java.util.Random().nextInt(getPointRange()) + getMinPoint();
        controlHeal(user, healPoint);
        System.out.println(user.getName() + "のHPを" + healPoint + "ポイントかいふくした！");
    }
    public void controlHeal(BattleChar user, int healPoint) {     // 回復量が最大HPを超えないように調整
        if (healPoint > (user.getMaxHp() - user.getHp())) {
            user.setHp(user.getMaxHp());
        } else {
            user.setHp(user.getHp() + healPoint);
        }
    }
    public void resite(BattleChar user, BattleChar receiver, State state, int probability) {}
}