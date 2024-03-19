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
        try {
            BufferedReader br = new BufferedReader(new FileReader("ForestEnemy_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(getName())) {
                    Object[] dataArray = str.split(",");
                    setEnemyId((int)(dataArray[1]));
                    setLevel((int)(dataArray[2]));
                    setHp((int)(dataArray[3]));
                    setMp((int)(dataArray[4]));
                    setDefaultAttack((int)dataArray[5]);
                    setDefaultDefense((int)dataArray[6]);
                    setDefaultAgility((int)dataArray[7]);
                    setPoint((int)dataArray[8]);
                    setMoney((int)dataArray[9]);
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    public abstract void turn(Brave brave);
}
