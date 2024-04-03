package battlechar.battlestatus;

import battlechar.BattleChar;

public class BattleAttack extends BattleStatus {
    // コンストラクタ
    public BattleAttack(BattleChar anyone) {
        super("こうげきりょく",anyone.getDefaultAttack());
    }
}
