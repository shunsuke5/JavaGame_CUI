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
    public void contStub(Brave brave) {
        System.out.println(getName() + "は連続で刺してきた！");
        attack(brave);
        attack(brave);
        plusTurnCount();
    }
}
