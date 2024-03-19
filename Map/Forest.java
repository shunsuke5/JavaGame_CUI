package map;

import battlechar.enemy.Enemy;
import battlechar.enemy.forestenemy.*;

public class Forest extends Map {
    // コンストラクタ
    public Forest() {
        super("森");
    }
    // メソッド
    public Enemy createEnemy() {
        int enemyNumber = new java.util.Random().nextInt(3);
        switch(enemyNumber) {
            case 0:
                return new Slime();
            case 1:
                return new Goblin();
            default:
                return new KillerBee();
        }
    }
}