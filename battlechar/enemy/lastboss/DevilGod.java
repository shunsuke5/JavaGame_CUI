package battlechar.enemy.lastboss;

import battlechar.enemy.Enemy;
import spell.abnormalityspell.Nekaasu;
import spell.attackspell.BigWota;
import spell.healspell.Bepyoimi;
import state.IsSleep;
import battlechar.brave.Brave;

public class DevilGod extends Enemy {
    // コンストラクタ
    public DevilGod() {
        super("だいまじん");
    }
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                new Bepyoimi().resite(this, this);
                break;
            case 2:
                new Nekaasu().resite(this, brave, new IsSleep(brave), 80);
                break;
            case 3:
                new BigWota().resite(this, brave);
            case 4:
                doubleAttack(brave);
                break;
        }
    }
    private void doubleAttack(Brave brave) {
        System.out.println(getName() + "はれんぞくでこうげきしてきた！");
        attack(brave);
        attack(brave);
    }
}
