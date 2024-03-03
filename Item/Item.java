package item;

public abstract class Item {
    private String name;            // アイテム名
    private String explanation;     // アイテムの説明
    private int haveCount;          // アイテム所持数

    // コンストラクタ
    public Item(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }
    // 抽象メソッド
    public abstract int use();
    // アクセサ
    public String getName() { return this.name; }
    public String getExplanation() { return this.explanation; }
    public int getHaveCount() { return this.haveCount; }

    public void setName(String name) { this.name = name; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public void setHaveCount(int haveCount) { this.haveCount = haveCount; }
    public void plusHaveCount(int haveCount) { this.haveCount += haveCount; }
    public void minusHaveCount(int haveCount) { this.haveCount--; }
}