package item;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import battlechar.brave.*;

public abstract class Item {
    private String name;            // 名前
    private int itemId;             // 識別番号
    private String targetStatus;    // ターゲット値
    private int price;              // 値段
    private int minHealPoint;       // 最低回復値
    private int healRange;          // 回復範囲
    private String targetAbnormal;  // 回復する状態異常
    private int needBossKill;       // 必要ボス討伐数(ショップに現れる条件)
    private String explanation;     // 説明

    // コンストラクタ
    public Item(String name) {
        this.name = name;
        try {
            BufferedReader br = new BufferedReader(new FileReader("HpItem_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(getName())) {
                    Object[] dataArray = str.split(",");
                    this.itemId = (int)(dataArray[1]);
                    this.targetStatus = (String)(dataArray[2]);
                    this.price = (int)(dataArray[3]);
                    this.minHealPoint = (int)(dataArray[4]);
                    this.healRange = (int)(dataArray[5]);
                    this.targetAbnormal = (String)(dataArray[6]);
                    this.needBossKill = (int)(dataArray[7]);
                    this.explanation = (String)(dataArray[8]);
                }
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // 抽象メソッド
    public abstract void use(Brave brave);
    // アクセサ
    public String getName() { return this.name; }
    public int getItemId() { return this.itemId; }
    public String getTargetStatus() { return this.targetStatus; }
    public int getPrice() { return this.price; }
    public int getMinHealPoint() { return this.minHealPoint; }
    public int getHealRange() { return this.healRange; }
    public String getTargetAbnormal() { return this.targetAbnormal; }
    public int getNeedBossKill() { return this.needBossKill; }
    public String getExplanation() { return this.explanation; }

    public void setName(String name) { this.name = name; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setTargetStatus(String targetStatus) { this.targetStatus = targetStatus; }
    public void setPrice(int price) { this.price = price; }
    public void setMinHealPOint(int minHealPoint) { this.minHealPoint = minHealPoint; }
    public void setHealRange(int healRange) { this.healRange = healRange; }
    public void setTargetAbnormal(String targetAbnormal) { this.targetAbnormal = targetAbnormal; }
    public void setNeedBossKill(int needBossKill) { this.needBossKill = needBossKill; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}