package battlechar.battlestatus;

import battlechar.BattleChar;

public class BattleDefense extends BattleStatus {
    // コンストラクタ
    public BattleDefense(BattleChar anyone) {
        super("ぼうぎょりょく",anyone.getDefaultAttack());
    }
}
