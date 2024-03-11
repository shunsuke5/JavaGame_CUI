package itembag;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import item.Item;
import item.hpitem.*;
import item.mpitem.*;

public class ItemBag {
    private Item[][] item;      // アイテムインスタンス

    // コンストラクタ
    public ItemBag() throws IOException {   // Item[][]の要素数をデータファイルから読み込んで初期化する処理
        int i = 0;

        BufferedReader br = new BufferedReader(new FileReader("..\\data\\ItemId_data.csv"));
        String str = br.readLine();
        while(str != null) {
            i++;
            br.readLine();
        }
        item = new Item[i][99];
    }
    // メソッド
    public void increase(int itemId, int add) throws IOException {  // アイテムが増える動作
        int itemCount = checkStorage(itemId);
        int total = itemCount + add;    // アイテムの合計所持数 = 現在のアイテム所持数 + 増えるアイテム数

        for (int i = itemCount; i < total; i++) {
            this.item[itemId][i] = createItem(itemId);    // ここでアイテムを生成したい　new アイテム();　としたい
        }
    }
    public void decrease(int itemId) {           // アイテムが減る動作
        this.item[itemId][checkStorage(itemId)] = null;
    }
    public void displayItemBag() throws IOException {
        // ItemId_data.csvファイルから各アイテムの識別番号を取得していく
        int itemId;
        String itemName;

        BufferedReader br = new BufferedReader(new FileReader("..\\data\\ItemId_data.csv"));
        String str = br.readLine();
        while(str != null) {
            Object[] objArray = str.split(",");
            itemName = (String)objArray[0];
            itemId = (int)objArray[1];
            if (checkStorage(itemId) == 0) {
                str = br.readLine();
            } else {
                // ここでアイテムを表示できる？
                int itemCount = checkStorage(itemId);
                System.out.println(itemName + "：" + itemCount + "こ");
                str = br.readLine();
            }
        }
    }
    public int checkStorage(int itemId) {       // 配列の要素がどこまで入っているかを調べる
        int i = 0;
        while(this.item[itemId][i] != null) {
            i++;
        }
        return i;
    }
    public Item createItem(int itemId) throws IOException {
        String itemName = itemIdToItemName(itemId);
        Item item = new Herb();
        switch(itemName) {
            case "やくそう":
                item = new Herb();
            case "まりょくのみず":
                item = new MagicWater();
            case "かいふくやく":
                item = new MedicineLiquid();
            case "まほうのせいすい":
                item = new MagicHolyWater();
            case "せいめいそう":
                item = new LifeHerb();
            case "いにしえのまどうしょ":
                item = new AncientMagicBook();
            case "だいちのしゅくふく":
                item = new BlessingOfGround();
            case "めがみのしゅくふく":
                item = new BlessingOfVenus();
        }
        return item;
    }
    public String itemIdToItemName(int itemId) throws IOException { // アイテムIDに対応するアイテム名を返す
        String itemName = "";
        BufferedReader br = new BufferedReader(new FileReader("..\\data\\ItemId_data.csv"));
        String str = br.readLine();
        while(str != null) {
            if (str.contains(Integer.toString(itemId))) {
                Object[] objArray = str.split(",");
                itemName = (String)objArray[0];
            }
            str = br.readLine();
        }
        return itemName;
    }
    // アクセサ
    public Item[][] getItem() { return this.item; }
}