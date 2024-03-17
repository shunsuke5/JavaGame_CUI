package item.hpitem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import item.Item;
import brave.*;

public class HpItem extends Item {
    private int minHealPoint;
    private int healRange;

    // コンストラクタ
    public HpItem(String name) {
        super(name);
        try {
            BufferedReader br = new BufferedReader(new FileReader("HpItem_data.csv"));
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // メソッド
    public void use(Brave b) {
        System.out.println(b.getName() + "は" + this.getName() + "をつかった！");
        int healPoint = new java.util.Random().nextInt(this.healRange) + this.minHealPoint;
        controlHp(b, healPoint);
        System.out.println(b.getName() + "のHPを" + healPoint + "ポイントかいふくした！");
    }
    public void controlHp(Brave b, int healPoint) {
        if (healPoint > (b.getMaxHp() - b.getHp())) {
            b.setHp(b.getMaxHp());
        } else {
            b.setHp(b.getHp() + healPoint);
        }
    }
}