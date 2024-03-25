package state;

import battlechar.BattleChar;

public class IsCursed extends State {   // MPが徐々に減っていく
    public IsCursed(BattleChar anyone) {
        super("のろい");
        anyone.decideAbnormalTurnPeriod();
    }

    public void effect(BattleChar anyone) {
        System.out.println(anyone.getName() + "はのろわれている！");
        int damage = returnRandomNum(2, 4);
        anyone.setMp(anyone.getMp() - damage);
        System.out.println(anyone.getName() + "のMPが" + damage + "ポイントへった！");
    }
}
