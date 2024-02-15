public class Slime extends Enemy {
    // コンストラクタ
    public Slime(int enemyCount) {
        setHp(10);
        setMp(2);
        setPoint(2);
        setMoney(1);
        setEnemyCount(enemyCount);
    }
}