package battlechar.enemy.forestenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.forestenemy.ForestEnemy;

public class CursedBigTree extends ForestEnemy {
    public CursedBigTree() {
        super("のろわれたたいぼく");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
            case 1:
                curse(brave);
            case 2:
                suffer();
        }
    }
    public void curse(Brave brave) {
        System.out.println(getName() + "は" + brave.getName() + "をのろった！");
    }
    public void suffer() {
        System.out.println(getName() + "はのろいにくるしんでいる。");
    }
}
