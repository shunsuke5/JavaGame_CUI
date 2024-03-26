package battlechar.enemy.mountainenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.mountainenemy.MountainEnemy;

public class HellWarrior extends MountainEnemy {
    public HellWarrior() {
        super("じごくのせんし");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                break;
        }
    }
}
