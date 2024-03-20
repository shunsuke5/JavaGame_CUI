package battlechar.state;

import battlechar.BattleChar;

public class IsPoison extends State {   // HPが徐々に減っていく
    // コンストラクタ
    public IsPoison() {
        super("どく");
    }

    public void effect(BattleChar anyone) {
        System.out.println(anyone.getName() + "はどくがまわっている！");
        int damage = returnRandomNum(3, 4);
        anyone.setHp(anyone.getHp() - damage);
        System.out.println(anyone.getName() + "に" + damage + "ポイントのダメージ！");
    }
}
