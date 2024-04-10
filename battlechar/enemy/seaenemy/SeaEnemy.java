package battlechar.enemy.seaenemy;

import battlechar.brave.Brave;
import battlechar.enemy.Enemy;

public abstract class SeaEnemy extends Enemy {
    public SeaEnemy(String name) {
        super(name);
    }
    public abstract void turn(Brave brave);
}
