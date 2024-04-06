package battlechar.enemy.seaenemy.boss;

import battlechar.enemy.seaenemy.SeaEnemy;
import spell.attackspell.BigWota;
import spell.healspell.Pyoimi;

public class MythicalWaterDragon extends SeaEnemy {
    // コンストラクタ
    public MythicalWaterDragon() {
        super("まぼろしのすいりゅう");
    }
    // メソッド
    public void turn(Brave brave) {
        switch(decideAction()) {
            case 1:
                attack(brave);
                break;
            case 2:
                new Pyoimi().resite(this, this);
                break;
            case 3:
                new BigWota().resite(this, brave);
                break;
            case 4:

        }
    }
    
}
