package battlechar.enemy.forestenemy;
import battlechar.brave.Brave;
import text.Text;

public class Goblin extends ForestEnemy {
    // コンストラクタ
    public Goblin() {
        super("ゴブリン");
    }
    public void turn(Brave brave) {             // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                stealAttack(brave);
                break;
        }
    }
    public void stealAttack(Brave brave) {      // 攻撃しつつ勇者からマネーを少し奪う
        System.out.println(getName() + "のぬすむこうげき！");
        int damage = calculateDamage(brave.getBattleDefense());
        brave.damagedHp(damage);
        Text.attack(brave.getName(), damage);
        int stealMoney = returnRandomNum(2, 3);
        setMoney(getMoney() + stealMoney);
        brave.setMoney(brave.getMoney() - stealMoney);
        System.out.println(getName() + "は" + brave.getName() + "から" + 
                            stealMoney + "マネーをぬすんだ！");
        plusTurnCount();
    }
}