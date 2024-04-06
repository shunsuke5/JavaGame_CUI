package battlechar.enemy.seaenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.seaenemy.SeaEnemy;
import spell.attackspell.BigWota;
import spell.attackspell.Wota;
import state.IsPoison;
import text.Text;

public class Kraken extends SeaEnemy {
    public Kraken() {
        super("クラーケン");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(3)) {
            case 0:
                attack(brave);
                break;
            case 1:
                new BigWota().resite(this, brave);
                break;
            case 2:
                poisonAttack(brave);
                break;
        }
    }
    public void poisonAttack(Brave brave) {
        System.out.println(getName() + "はどくをふきかけてきた！");
        if (isSuccessGiveAbnormality(30)) {
            Text.makePoison(brave);
            brave.setState(new IsPoison(brave));
        } else {
            System.out.println("しかしあたらなかった！");
        }
    }
}
