package spell.healspell;
import battlechar.BattleChar;
import spell.Spell;

public class HealSpell extends Spell {

    // コンストラクタ
    public HealSpell(String name) {
        super(name);
    }
    // メソッド
    public void resite(BattleChar user, BattleChar receiver) {                       // 指定した範囲からランダムにポイントを生成
        if (user.getMp() < getConsumptionMp()) {
            System.out.println("MPがたりない！");
            // MPがたりないのが敵ならターンカウントをプラスする処理を入れる
            if (user.toString().equals("Enemy")) {
                user.plusTurnCount();
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
}