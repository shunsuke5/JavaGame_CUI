package enemy.forestenemy;
import brave.Brave;
import enemy.Enemy;

public class KillerBee extends ForestEnemy{
    // コンストラクタ
    public KillerBee() {
        super("キラービー");
    }
    public void turn(Brave b) {     // ランダムで自分の行動を決める
        int action = new java.util.Random().nextInt(2);

        switch (action) {
            case 0:
                attack(b);
            case 1:
                purupuru();
        }
    }
}
