package shop;
import java.util.LinkedHashMap;
import item.Item;

public abstract class Shop {
    private LinkedHashMap<String,Integer> menu;     // 売るアイテム一覧
    // メソッド
    public void sell() {
        
    }
    // アクセサ
    public LinkedHashMap<String,Integer> getMenu() { return this.menu; }
    public void setMenu(String itemName, int buyNumber) { this.menu.put(itemName,buyNumber); }  
}
