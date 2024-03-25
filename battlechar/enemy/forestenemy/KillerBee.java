package battlechar.enemy.forestenemy;
import battlechar.brave.Brave;
import state.IsPoison;
import text.Text;

public class KillerBee extends ForestEnemy{
    // コンストラクタ
    public KillerBee() {
        super("キラービー");
    }
    public void turn(Brave brave) {             // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                poisonNeedle(brave);
                break;
        }
    }
    public void poisonNeedle(Brave brave) {     // 毒針で刺す攻撃
        System.out.println(getName() + "はどくばりでさしてきた！");
        int damage = calculateDamage(brave.getInBattleDefense());
        Text.attack(brave.getName(), damage);
        if (isSuccessGiveAbnormality(10)) {
            brave.setState(new IsPoison(brave));
            Text.makePoison(brave.getName());
        }
        plusTurnCount();
    }
}
