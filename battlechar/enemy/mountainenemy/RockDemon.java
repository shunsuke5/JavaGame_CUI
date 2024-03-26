package battlechar.enemy.mountainenemy;

import battlechar.brave.Brave;

public class RockDemon extends MountainEnemy {
    public RockDemon() {
        super("がんせきまじん");
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
