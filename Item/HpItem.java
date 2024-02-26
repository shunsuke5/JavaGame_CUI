package item;

public class HpItem extends Item {
    private int healPoint;

    // コンストラクタ
    public HpItem(String name, String explanation, int healPoint) {
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