package battlechar.enemy.forestenemy;
import battlechar.brave.Brave;

public class Slime extends ForestEnemy {
    // コンストラクタ
    public Slime() {
        super("スライム");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                purupuru();
                break;
        }
    }
    public void purupuru() {         // スライムの特殊行動
        // ぷるぷるするだけの行動
        System.out.println(getName() + "はぷるぷるしている。");
        plusTurnCount();
    }
}