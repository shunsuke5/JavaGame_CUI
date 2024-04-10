package battlechar.enemy.forestenemy;

import battlechar.enemy.Enemy;
import battlechar.brave.Brave;

public abstract class ForestEnemy extends Enemy {
    // コンストラクタ
    public ForestEnemy(String name) {
        super(name);
    }
    public abstract void turn(Brave brave);
}
