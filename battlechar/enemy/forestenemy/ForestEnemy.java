package battlechar.enemy.forestenemy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import battlechar.enemy.Enemy;
import battlechar.brave.Brave;

public abstract class ForestEnemy extends Enemy {
    // コンストラクタ
    public ForestEnemy(String name) {
        super(name);
    }
    public abstract void turn(Brave brave);
}
