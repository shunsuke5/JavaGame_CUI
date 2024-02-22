package map;

import enemy.Enemy;
import enemy.seaenemy.*;

public class Sea extends Map {
    private static int enemyKillCount;
    private static boolean treasureFlag;
    private static boolean bossFlag;

    // コンストラクタ
    public Sea() {
        super("海");
    }
    // メソッド
    public Enemy createEnemy() {
        setEnemy(returnEnemy());
        return getEnemy();
    }
    public Enemy returnEnemy() {
        int enemyNumber = new java.util.Random().nextInt(3);
        switch(enemyNumber) {
            case 0:
                return new 
            case 1:
                return new 
            default:
                return new 
            // 敵の実装を考える
        }
    }
    // アクセサ
    public static int getEnemyKillCount() { return enemyKillCount; }
    public static boolean getTreasureFlag() { return treasureFlag; }
    public static boolean getBossFlag() { return bossFlag; }
}