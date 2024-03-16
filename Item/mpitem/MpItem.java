package item.mpitem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import brave.Brave;
import item.Item;

public class MpItem extends Item {
    private int minHealPoint;
    private int healRange;

    // コンストラクタ
    public MpItem(String name) throws IOException {
        super(name);
        
        BufferedReader br = new BufferedReader(new FileReader("MpItem_data.csv"));
        String str = br.readLine();
        while(str != null) {
            if (str.contains(getName())) {
                String[] itemArray = str.split(",");
                setItemId(Integer.parseInt(itemArray[1]));
                setPrice((Integer.parseInt(itemArray[2])));
                this.minHealPoint = Integer.parseInt(itemArray[3]);
                this.healRange = Integer.parseInt(itemArray[4]);
                setExplanation(itemArray[5]);
            }
            str = br.readLine();
        }
    }
    // メソッド
    public void use(Brave b) {
        System.out.println(b.getName() + "は" + this.getName() + "をつかった！");
        int healPoint = new java.util.Random().nextInt(this.healRange) + this.minHealPoint;
        controlMp(b, healPoint);
        System.out.println(b.getName() + "のMPを" + healPoint + "ポイントかいふくした！");
    }
    public void controlMp(Brave b, int healPoint) {
        if (healPoint > (b.getMaxMp() - b.getMp())) {
            b.setMp(b.getMaxMp());
        } else {
            b.setMp(b.getMp() + healPoint);
        }
    }
}
