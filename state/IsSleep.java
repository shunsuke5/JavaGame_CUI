package state;

import battlechar.BattleChar;

public class IsSleep extends State {    // 数ターン休み
    // コンストラクタ
    public IsSleep(BattleChar anyone) {
        super("ねむり");
        anyone.decideAbnormalTurnPeriod();
    }

    public void effect(BattleChar anyone) {
        System.out.println(anyone.getName() + "はねむっている！");
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
