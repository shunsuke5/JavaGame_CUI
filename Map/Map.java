package map;
import enemy.Enemy;

public abstract class Map {
    private String name;
    private Enemy enemy;
    
    // コンストラクタ
    public Map(String name) {
        this.name = name;
    }
    // メソッド
    public abstract Enemy createEnemy();
    // アクセサ
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public Enemy getEnemy() { return this.enemy; }
    public void setEnemy(Enemy enemy) { this.enemy = enemy; }
}