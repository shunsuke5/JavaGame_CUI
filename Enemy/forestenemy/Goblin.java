package enemy.forestenemy;
import enemy.Enemy;

public class Goblin extends Enemy {
    // コンストラクタ
    public Goblin() {
        setHp(16);
        setMp(0);
        setAttack(4);
        setDefense(3);
        setAgility(3);
        setPoint(4);
        setMoney(3);
    }
}