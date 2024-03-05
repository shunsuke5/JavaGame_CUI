package shop;
import java.util.LinkedHashMap;
import item.Item;

public abstract class Shop {
    private LinkedHashMap<Item,Integer> menu;     // 売るアイテム
    // メソッド
    public void sell() {
        
    }
    // アクセサ
    public LinkedHashMap<Item,Integer> getMenu() { return this.menu; }
    public void setMenu(Item item, int buyNumber) { this.menu.put(item,buyNumber); }  
}
