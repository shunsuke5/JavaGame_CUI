package itembag;
import item.Item;

public class ItemBag {
    private int[] itemNumber; // アイテム識別番号
    private Item[] item;      // アイテムインスタンス

    // コンストラクタ
    public ItemBag() {

    }
    // メソッド
    public void increase(Item item, int itemNumber) {    // アイテムが増える動作
        
        this.item[itemNumber] = item;
    }
    public void decrease() {    // アイテムが減る動作

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
}