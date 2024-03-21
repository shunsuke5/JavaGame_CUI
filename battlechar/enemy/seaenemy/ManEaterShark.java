package battlechar.enemy.seaenemy;

import battlechar.brave.Brave;

public class ManEaterShark extends SeaEnemy {
    public ManEaterShark() {
        super("ひとくいザメ");
    }
    // メソッド
    public void turn(Brave b) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            default:
                attack(b);
                break;
        }
    }
}
