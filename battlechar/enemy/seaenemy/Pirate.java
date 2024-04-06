package battlechar.enemy.seaenemy;

import battlechar.brave.Brave;
import state.IsParalysis;
import text.Text;

public class Pirate extends SeaEnemy {
    public Pirate() {
        super("かいぞく");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                paralysis(brave);
                break;
        }
    }
    public void paralysis(Brave brave) {
        System.out.println(getName() + "はしびれぐすりをなげつけてきた！");
        if (isSuccessGiveAbnormality(20)) {
            Text.makeParalysis(brave.getName());
            brave.setState(new IsParalysis(brave));
        } else {
            System.out.println("しかしあたらなかった！");
        }
    }
}
