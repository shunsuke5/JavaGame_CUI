package enemy.forestenemy;
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
    }
    // メソッド
    public int turn(String braveName, int braveDefense) {
        // ランダムで自分の行動を決め、ダメージを返す
        int turn = new java.util.Random().nextInt(2);

        switch (turn) {
            case 0:
                return attack(braveName, braveDefense);
            case 1:
                return purupuru();
            default:
                // 余裕があればなんか例外処理みたいなの挟みたい、0か1しか返ってくるはずないんだけれども
                return 0;
        }
    }
    public int purupuru() {
        // ぷるぷるするだけの行動
        System.out.println(getName() + "はぷるぷるしている。");
        return 0;
    }
}