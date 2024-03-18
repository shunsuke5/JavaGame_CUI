package equipment.helmet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import equipment.Equipment;

public class Helmet extends Equipment {
    private int helmetId;
    private int defense;

    // コンストラクタ
    public Helmet(String name) {
        super(name);
        try {
            BufferedReader br = new BufferedReader(new FileReader("Helmet_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(getName())) {
                    Object[] dataArray = str.split(",");
                    this.helmetId = (int)(dataArray[1]);
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
    public int getHelmetId() { return this.helmetId; }
    public int getDefense() { return this.defense; }
}