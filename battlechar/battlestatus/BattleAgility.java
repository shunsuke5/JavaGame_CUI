package battlechar.battlestatus;

import battlechar.BattleChar;

public class BattleAgility extends BattleStatus {
    // コンストラクタ
    public BattleAgility(BattleChar anyone) {
        super("すばやさ",anyone.getDefaultAttack());
    }
}
