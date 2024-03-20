package battlechar.state;

import battlechar.BattleChar;

public class IsSleep extends State {    // 数ターン休み
    // コンストラクタ
    public IsSleep() {
        super("ねむり");
    }

    public void effect(BattleChar anyone) {
        System.out.println(anyone.getName() + "はねむっている！");
        anyone.plusTurnCount();
    }
}
