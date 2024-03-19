package battlechar.enemy.seaenemy;

import battlechar.brave.Brave;

public class CorpsFish extends SeaEnemy {
    public CorpsFish() {
        super("ぐんだんぎょ");
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
