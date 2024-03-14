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
        int i = 1;

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
            String[] itemArray = str.split(",");
            itemName = itemArray[0];
            itemId = Integer.parseInt(itemArray[1]);
            if (checkStorage(itemId) == 0) {
                str = br.readLine();
            } else {
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
        String[] itemArray = itemLookUp(itemId);
        String itemName = itemArray[0];
        switch(itemName) {
            case "やくそう":
                return new Herb();
            case "まりょくのみず":
                return new MagicWater();
            case "かいふくやく":
                return new MedicineLiquid();
            case "まほうのせいすい":
                return new MagicHolyWater();
            case "せいめいそう":
                return new LifeHerb();
            case "いにしえのまどうしょ":
                return new AncientMagicBook();
            case "だいちのしゅくふく":
                return new BlessingOfGround();
            case "めがみのしゅくふく":
                return new BlessingOfVenus();
        }
        return null;
    }
    public String[] itemLookUp(int itemId) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("..\\data\\ItemId_data.csv"));
        String str = br.readLine();
        while(str != null) {
            if (str.contains(Integer.toString(itemId))) {
                String[] itemArray = str.split(",");
                return itemArray;
            }
            str = br.readLine();
        }
        return null;
    }
    // アクセサ
    public Item[][] getItem() { return this.item; }
}