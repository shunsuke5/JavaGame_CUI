package item.hpitem;

public class Herb extends HpItem {
    // コンストラクタ
    public Herb() {
        super("やくそう", 
        "たいりょくをかいふくできるきちょうなしょくぶつ。\nHPを10～15ポイントかいふくする。",
         10, 6);
    }
    // メソッド
    public static String toString() {
        return getName();
    }
}
