package item.mpitem;
import item.Item;

public class MpItem extends Item {
    private int healPoint;

    // コンストラクタ
    public MpItem(String name, String explanation, int healPoint) {
        super(name,explanation);
        this.healPoint = healPoint;
    }
    // メソッド
    public int use() {
        return this.healPoint;
    }
    // アクセサ
    public int getHealPoint() { return this.healPoint; }
    public void setHealPoint(int healPoint) { this.healPoint = healPoint; }
}
