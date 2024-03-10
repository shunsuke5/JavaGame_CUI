package item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public abstract class Item {
    private String name;          // 名前
    private int itemId;           // 識別番号
    private int price;            // 値段
    private String explanation;   // 説明
    private int haveCount = 0;    // 所持数

    // コンストラクタ
    public Item(String name) throws IOException {
        this.name = name;
    }
    // 抽象メソッド
    public abstract int use();
    // アクセサ
    public String getName() { return this.name; }
    public int getItemId() { return this.itemId; }
    public int getPrice() { return this.price; }
    public String getExplanation() { return this.explanation; }
    public int getHaveCount() { return this.haveCount; }

    public void setName(String name) { this.name = name; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setPrice(int price) { this.price = price; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public void setHaveCount(int haveCount) { this.haveCount = haveCount; }
    public void plusHaveCount(int haveCount) { this.haveCount += haveCount; }
    public void minusHaveCount() { this.haveCount--; }
}