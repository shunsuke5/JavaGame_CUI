package equipment.sword;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import equipment.Equipment;

public class Sword extends Equipment {
    private int swordId;
    private int attack;

    // コンストラクタ
    public Sword(String name) {
        super(name);
        try {
            BufferedReader br = new BufferedReader(new FileReader("Sword_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(getName())) {
                    Object[] dataArray = str.split(",");
                    this.swordId = (int)(dataArray[1]);
                    this.attack = (int)(dataArray[2]);
                    setExplanation((String)(dataArray[3]));
                }
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // アクセサ
    public int getAttack() { return this.attack; }
    public int getSwordId() { return this.swordId; }
}