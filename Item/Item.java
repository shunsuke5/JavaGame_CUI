package item;

public abstract class Item {
    private String name;            // アイテム名
    private String explanation;     // アイテムの説明
    private int price;              // アイテムの値段
    private int haveCount;          // アイテム所持数

    // コンストラクタ
    public Item(String name, String explanation, int price) {
        this.name = name;
        this.explanation = explanation;
        this.price = price;
    }
    // 抽象メソッド
    public abstract int use();
    // アクセサ
    public String getName() { return this.name; }
    public String getExplanation() { return this.explanation; }
    public int getPrice() { return this.price; }
    public int getHaveCount() { return this.haveCount; }

    public void setName(String name) { this.name = name; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public void setPrice(int price) { this.price = price; }
    public void setHaveCount(int haveCount) { this.haveCount = haveCount; }
    public void plusHaveCount(int haveCount) { this.haveCount += haveCount; }
    public void minusHaveCount(int haveCount) { this.haveCount--; }
}