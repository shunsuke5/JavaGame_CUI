package map;
import enemy.Enemy;

public abstract class Map {
    private String name;
    private Enemy enemy;
    private int enemyKillCount;
    private boolean treasureObtain;
    private boolean bossFlag;
    
    // コンストラクタ
    public Map(String name) {
        this.name = name;
    }
    // メソッド
    public abstract Enemy createEnemy();
    
    // アクセサ
    public String getName() { return this.name; }
    public Enemy getEnemy() { return this.enemy; }
    public int getEnemyKillCount() { return this.enemyKillCount; }
    public boolean getTreasureObtain() { return this.treasureObtain; }
    public boolean getBossFlag() { return this.bossFlag; }

    public void setName(String name) { this.name = name; }
    public void setEnemy(Enemy enemy) { this.enemy = enemy; }
    public void setEnemyKillCount(int enemyKillCount) { this.enemyKillCount = enemyKillCount; }
    public void setTreasureObtain(boolean treasureObtain) { this.treasureObtain = treasureObtain; }
    public void setBossFlag(boolean bossFlag) { this.bossFlag = bossFlag; }
}