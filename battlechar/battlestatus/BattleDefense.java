package battlechar.battlestatus;

import battlechar.BattleChar;

public class BattleDefense extends BattleStatus {
    // コンストラクタ
    public BattleDefense(BattleChar anyone) {
        super("ぼうぎょりょく",anyone.getDefaultAttack());
    }
    public void guard() {
        // まず現在のステータス状態を取得、そこに+1したときの値を防御力とする
        int defense = checkValue() + 1;
        if (defense > 2) {
            defense = 2;
        }
        setValue(returnValue(defense));
    }
}
