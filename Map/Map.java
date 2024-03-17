package map;
import enemy.Enemy;

public abstract class Map {
    private String name;            // マップ名
    private Enemy enemy;            // マップの敵
    private int enemyKillCount;     // 敵を倒した数
    private boolean bossKill;       // ボスを倒したかどうか
    private boolean thereIs;        // 現在地を判別
    
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
    public boolean isBossKill() { return this.bossKill; }
    public boolean isThereIs() { return this.thereIs; }

    public void setName(String name) { this.name = name; }
    public void setEnemy(Enemy enemy) { this.enemy = enemy; }
    public void setEnemyKillCount(int enemyKillCount) { this.enemyKillCount = enemyKillCount; }
    public void setBossKill(boolean bossKill) { this.bossKill = bossKill; }
    public void setThereIs(boolean thereIs) { this.thereIs = thereIs; }
}