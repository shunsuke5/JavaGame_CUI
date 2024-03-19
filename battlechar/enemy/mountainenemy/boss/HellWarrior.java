package battlechar.enemy.mountainenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.mountainenemy.MountainEnemy;

public class HellWarrior extends MountainEnemy {
    public HellWarrior() {
        super("じごくのせんし");
    }
    // メソッド
    public void turn(Brave b) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(b);
            case 1:
                purupuru();
        }
    }
}
