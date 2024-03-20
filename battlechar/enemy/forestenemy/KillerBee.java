package battlechar.enemy.forestenemy;
import battlechar.brave.Brave;

public class KillerBee extends ForestEnemy{
    // コンストラクタ
    public KillerBee() {
        super("キラービー");
    }
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
            case 1:
                contStub(brave);
        }
    }
    public void poisonNeedle(Brave brave) {
        System.out.println(getName() + "はどくばりでさしてきた！");
        // 状態異常は絶対ではなく、確率でなるようにしたいのでその処理を入れる
        System.out.println();
        plusTurnCount();
    }
}
