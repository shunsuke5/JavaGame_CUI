package state;

import battlechar.BattleChar;

public class IsParalysis extends State {    // 数ターン休み
    // コンストラクタ
    public IsParalysis(BattleChar anyone) {
        super("まひ");
        anyone.decideAbnormalTurnPeriod();
    }

    public void effect(BattleChar anyone) {
        System.out.println(anyone.getName() + "はしびれてうごけない！");
        if (anyone.toString().equals("Brave")) {    // 勇者の場合
            anyone.plusTurnCount();
            anyone.plusAbnormalTurnPeriod();
            return;
        } else {                                    // 敵の場合
            anyone.plusAbnormalTurnPeriod();
            return;
        }
    }
}
