package battlechar.enemy.seaenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.seaenemy.SeaEnemy;

public class Kraken extends SeaEnemy {
    public Kraken() {
        super("クラーケン");
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
