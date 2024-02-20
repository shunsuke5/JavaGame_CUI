package map;

import enemy.Enemy;
import enemy.mountainenemy.*;

public class Mountain extends Map {
    // コンストラクタ
    public Mountain() {
        super("山");
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
        }
    }
}