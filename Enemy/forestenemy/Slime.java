package enemy.forestenemy;
import brave.Brave;
import enemy.Enemy;

public class Slime extends Enemy {
    // コンストラクタ
    public Slime() {
        setName("スライム");
        setLevel(2);
        setHp(10);
        setMp(2);
        setAttack(2);
        setDefense(2);
        setAgility(1);
        setPoint(2);
        setMoney(2);
        setTurnCount();
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