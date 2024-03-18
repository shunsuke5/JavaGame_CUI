package enemy.forestenemy;
import brave.Brave;

public class Slime extends ForestEnemy {
    // コンストラクタ
    public Slime() {
        super("スライム");
    }
    // メソッド
    public void turn(Brave b) {     // ランダムで自分の行動を決める
        int action = new java.util.Random().nextInt(2);

        switch (action) {
            case 0:
                attack(b);
            case 1:
                purupuru();
        }
    }
    public int purupuru() {         // スライムの特殊行動
        // ぷるぷるするだけの行動
        System.out.println(getName() + "はぷるぷるしている。");
        return 0;
    }
}