package battlechar.enemy.seaenemy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import battlechar.brave.Brave;
import battlechar.enemy.Enemy;

public abstract class SeaEnemy extends Enemy {
    public SeaEnemy(String name) {
        super(name);
    }
    public abstract void turn(Brave brave);
}
