package battlechar.state;

import battlechar.BattleChar;

public class IsParalysis extends State {    // 数ターン休み
    // コンストラクタ
    public IsParalysis() {
        super("まひ");
    }

    public void effect(BattleChar anyone) {
        System.out.println(anyone.getName() + "はしびれてうごけない！");
        anyone.plusTurnCount();
    }
}
