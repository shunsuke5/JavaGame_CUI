package equipment.armor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import equipment.Equipment;

public class Armor extends Equipment {
    private int armorId;
    private int defense;

    // コンストラクタ
    public Armor(String name) {
        super(name);
        try {
            BufferedReader br = new BufferedReader(new FileReader("Armor_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(getName())) {
                    Object[] dataArray = str.split(",");
                    this.armorId = (int)(dataArray[1]);
                    this.defense = (int)(dataArray[2]);
                    setExplanation((String)(dataArray[3]));
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // アクセサ
    public int getArmorId() { return this.armorId; }
    public int getDefense() { return this.defense; }
}