package shop.itemshop;
import text.Text;
import brave.Brave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ItemShop {
    // フィールド
    private boolean isLeave;
    // コンストラクタ
    public ItemShop() {
        
    }
    // メソッド
    public void sell(Brave b) {
        // 購入するアイテムとその購入数をHashMapの形で返す
        this.isLeave = false;
        System.out.println("ショップへようこそ。");

        while (!isLeave) {
            System.out.println("なにをかいますか？(-1でマップにもどる)\n");
            displayMenu(b.getBossKillCount());
            int itemId = Text.inputChoice();
            if (itemId == -1) {                 // -1を選んだ場合マップアクションへ
                this.isLeave = true;
                continue;
            }
            System.out.println("いくつかいますか？(-1でこうにゅうがめんにもどる)");
            int count = Text.inputChoice();
            if (count == -1) {                  // -1を選んだ場合ショップのアイテム一覧へ
                continue;
            }
            int price = payment(itemId, count); // 金額の計算
            if (b.getMoney() < price) {
                System.out.println("おかねがたりません！");
                continue;
            }
            System.out.println("おかいけい" + price + "マネーです。よろしいですか？");
            System.out.println("かう：0　かわない：-1");
            if (Text.inputChoice() == -1) {
                System.out.println("いやひやかしかーーーーい");
                continue;
            }
            b.setMoney(b.getMoney() - price);
            b.getItemBag().increase(itemId, count);
            
            System.out.println("おかいあげありがとうございます。");
            System.out.println("ほかにもなにかかいますか？");
            System.out.println("かう：0　かわない：-1");
            if (Text.inputChoice() == -1) {
                this.isLeave = true;
                continue;
            } else {
                continue;
            }
        }
    }
    public int payment(int itemId, int count) {  // 金額を返す
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\..\\data\\ItemId_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(Integer.toString(itemId))) {
                    String[] itemArray = str.split(",");
                    return Integer.parseInt(itemArray[3]) * count;
                }
                br.readLine();
            }
            return -1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return -1;
        }
    }
    public void displayMenu(int bossKillCount) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\..\\data\\ItemId_data.csv"));
            String str = br.readLine();
            while(str != null) {
                String[] itemArray = str.split(",");
                if (bossKillCount <= Integer.parseInt(itemArray[4])) {
                    br.readLine();
                    continue;
                }
                System.out.println(itemArray[0] + "：" + itemArray[3] + "m -> " + Integer.parseInt(itemArray[1]));
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}
