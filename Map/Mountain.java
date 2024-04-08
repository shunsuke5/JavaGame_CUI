package map;

import battlechar.enemy.Enemy;
import battlechar.enemy.mountainenemy.*;

public class Mountain extends Map {
    // コンストラクタ
    public Mountain() {
        super("やま");
    }
    // メソッド
    public Enemy createEnemy() {
        int enemyNumber = new java.util.Random().nextInt(3);
        switch(enemyNumber) {
            case 0:
                return new DeathEagle();
            case 1:
                return new RockDemon();
            default:
                return new MagmaGolem();
        }
    }
}