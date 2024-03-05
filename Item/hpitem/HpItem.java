package item.hpitem;
import item.Item;

public class HpItem extends Item {
    private int healPoint;
    private int minHealPoint;
    private int healRange;

    // コンストラクタ
    public HpItem(String name, String explanation, int price, int minHealPoint, int healRange) {
        super(name,explanation,price);
        this.minHealPoint = minHealPoint;
        this.healRange = healRange;
    }
    // メソッド
    public int use() {
        this.healPoint = new java.util.Random().nextInt(this.healRange) + this.minHealPoint;
        return this.healPoint;
    }
    // アクセサ
    public int getHealPoint() { return this.healPoint; }
    public void setHealPoint(int healPoint) { this.healPoint = healPoint; }
}