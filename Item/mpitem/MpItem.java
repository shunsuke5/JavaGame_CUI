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
    public MpItem(String name) {
        super(name);
        try {
            BufferedReader br = new BufferedReader(new FileReader("MpItem_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(getName())) {
                    Object[] dataArray = str.split(",");
                    setItemId((int)(dataArray[1]));
                    setPrice((int)(dataArray[2]));
                    this.minHealPoint = (int)(dataArray[3]);
                    this.healRange = (int)(dataArray[4]);
                    setExplanation((String)(dataArray[5]));
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
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
