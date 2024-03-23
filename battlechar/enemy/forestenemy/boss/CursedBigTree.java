package battlechar.enemy.forestenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.forestenemy.ForestEnemy;
import state.IsCursed;
import text.Text;

public class CursedBigTree extends ForestEnemy {
    public CursedBigTree() {
        super("のろわれたたいぼく");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                curse(brave);
                break;
            case 2:
                suffer();
                break;
        }
    }
    public void curse(Brave brave) {
        Text.isCursed(getName(), brave.getName());
        brave.setState(new IsCursed());
    }
    public void suffer() {
        System.out.println(getName() + "はのろいにくるしんでいる。");
        setState(new IsCursed());
    }
}
