package battlechar.enemy.mountainenemy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import battlechar.brave.Brave;
import battlechar.enemy.Enemy;

public abstract class MountainEnemy extends Enemy {
    public MountainEnemy(String name) {
        super(name);
    }
    public abstract void turn(Brave brave);
}
