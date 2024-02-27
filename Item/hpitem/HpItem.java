package item.hpitem;
import item.Item;

public class HpItem extends Item {
    private int healPoint;

    // コンストラクタ
    public HpItem(String name, String explanation, int minHealPoint, int range) {
        super(name,explanation);
        this.healPoint = new java.util.Random().nextInt(range) + minHealPoint;
    }
    // メソッド
    public int use() {
        return this.healPoint;
    }
    // アクセサ
    public int getHealPoint() { return this.healPoint; }
    public void setHealPoint(int healPoint) { this.healPoint = healPoint; }
}