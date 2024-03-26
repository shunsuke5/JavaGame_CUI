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
    public ItemBag() {   // Item[][]の要素数をデータファイルから読み込んで初期化する処理
        int i = 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\item\\Item_data.csv"));
            String str = br.readLine();
            while(str != null) {
                i++;
                br.readLine();
            }
            item = new Item[i][99];    
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // メソッド
    public void increase(int itemId, int add) {  // アイテムが増える動作
        int itemCount = checkStorage(itemId);
        int total = itemCount + add;    // アイテムの合計所持数 = 現在のアイテム所持数 + 増えるアイテム数

        for (int i = itemCount; i < total; i++) {
            this.item[itemId][i] = createItem(itemId);    // ここでアイテムを生成したい　new アイテム();　としたい
        }
    }
    public void decrease(int itemId) {           // アイテムが減る動作
        this.item[itemId][checkStorage(itemId)] = null;
    }
    public void displayItemBag() {
        // Item_data.csvファイルから各アイテムの識別番号を取得していく
        int itemId;
        String itemName;

        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\item\\Item_data.csv"));
            String str = br.readLine();
            while(str != null) {
                String[] dataArray = str.split(",");
                itemName = dataArray[0];
                itemId = Integer.parseInt(dataArray[1]);
                if (checkStorage(itemId) == 0) {
                    str = br.readLine();
                } else {
                    int itemCount = checkStorage(itemId);
                    System.out.println(itemName + "：" + itemCount + "こ");
                    str = br.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    public int checkStorage(int itemId) {       // 配列の要素がどこまで入っているかを調べる
        int i = 0;
        while(this.item[itemId][i] != null) {
            i++;
        }
        return i;
    }
    public Item createItem(int itemId) {
        String[] dataArray = itemLookUp(itemId);
        String itemName = dataArray[0];
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
    public String[] itemLookUp(int itemId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\item\\Item_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(Integer.toString(itemId))) {
                    String[] dataArray = str.split(",");
                    return dataArray;
                }
                str = br.readLine();
            }
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return null;
        }
    }
    // アクセサ
    public Item[][] getItem() { return this.item; }
}