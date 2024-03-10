package itembag;
import item.Item;

public class ItemBag {
    private int itemId; // アイテム識別番号
    private Item[][] item;      // アイテムインスタンス

    // コンストラクタ
    public ItemBag() {

    }
    // メソッド

    // increase(new herb(), 3)
    // this.item[0][0] = new herb();
    // (int i = 3; i < 5; i++) {
        // this.item[item.getItemId()][i] = item;
    // }
    /*
     * 3個持っていて、5個買う
     * itemCount = 3;
     * add = 5;
     * total = 8;
     * for (int i = itemCount(3); i(3) < total(8); i++) {
     *      this.item[item.getItemId()][i(3)] = item;
     * }
     */

    public void increase(Item item, int add) {  // アイテムが増える動作
        int itemCount = item.getHaveCount();
        int total = itemCount + add;    // アイテムの合計所持数 = 現在のアイテム所持数 + 増えるアイテム数

        for (int i = itemCount; i < total; i++) {
            this.item[item.getItemId()][i] = item;
        }
        item.setHaveCount(total);
    }
    public void decrease(Item item) {           // アイテムが減る動作
        this.item[item.getItemId()][item.getHaveCount()] = null;
        item.minusHaveCount();
    }
    public int checkStorage(int itemId) {       // 配列の要素がどこまで入っているかを調べる
        int i = 0;
        while(this.item[itemId][i] != null) {
            i++;
        }
        return i;
    }

    /*
     * item[0][0] = new Herb();
     * item[0][1] = new Herb();
     * item[1][1] = new MagicWater();
     * item[0][1] = null;
     * this.itemBag.increase(herb,herb.HERB)
     * this.itemBag += new Herb();
     * this.itemBag.herb.use();
     */
    // アクセサ
    public int getItemId() { return this.itemId; }
    public Item[][] getItem() { return this.item; }
    // getItem()[0][0]　といった書き方は勇者クラス側で可能か？　→　なんかいけそう

    public void setItemId(int itemId) { this.itemId = itemId; }
}