package item.hpitem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import item.Item;

public class HpItem extends Item {
    private int minHealPoint;
    private int healRange;

    // コンストラクタ
    public HpItem(String name) throws IOException {
        super(name);
        
        BufferedReader br = new BufferedReader(new FileReader("HpItem_Data.csv"));
        String str = br.readLine();
        while(str != null) {
            if (str.contains(getName())) {
                Object[] objArray = str.split(",");
                setItemId((int)objArray[1]);
                setPrice((int)objArray[2]);
                this.minHealPoint = (int)objArray[3];
                this.healRange = (int)objArray[4];
                setExplanation((String)objArray[5]);
            }
            str = br.readLine();
        }
    }
    // メソッド
    public int use() {
        return new java.util.Random().nextInt(this.healRange) + this.minHealPoint;
    }
}