package map;

import battlechar.enemy.Enemy;
import battlechar.enemy.seaenemy.*;

public class Sea extends Map {
    // コンストラクタ
    public Sea() {
        super("海");
    }
    // メソッド
    public Enemy createEnemy() {
        int enemyNumber = new java.util.Random().nextInt(3);
        switch(enemyNumber) {
            case 0:
                return new ManEaterShark();
            case 1:
                return new Pirate();
            default:
                return new CorpsFish();
        }
    }
}