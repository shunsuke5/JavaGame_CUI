package enemy.forestenemy;
import enemy.Enemy;

public class Slime extends Enemy {
    // コンストラクタ
    public Slime() {
        setHp(10);
        setMp(2);
        setAttack(2);
        setDefense(2);
        setAgility(1);
        setPoint(2);
        setMoney(1);
    }
}