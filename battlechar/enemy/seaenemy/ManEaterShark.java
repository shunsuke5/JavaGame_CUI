package battlechar.enemy.seaenemy;

import battlechar.brave.Brave;

public class ManEaterShark extends SeaEnemy {
    public ManEaterShark() {
        super("ひとくいザメ");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(1)) {
            default:
                attack(brave);
                break;
        }
    }
}
