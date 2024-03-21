package battlechar.enemy.mountainenemy;

import battlechar.brave.Brave;

public class DeathEagle extends MountainEnemy {
    public DeathEagle() {
        super("デスイーグル");
    }
        // メソッド
    public void turn(Brave b) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(b);
                break;
            case 1:
                break;
        }
    }
}
