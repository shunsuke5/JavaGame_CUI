package shop.itemshop;
import text.Text;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ItemShop {
    // フィールド
    private boolean leaveFlag;
    // コンストラクタ
    public ItemShop() {
        
    }
    // メソッド
    public HashMap<Integer,Integer> sell(int bossKillCount) throws IOException {
        // 購入するアイテムとその購入数をHashMapの形で返す
        this.leaveFlag = false;
        HashMap<Integer,Integer> buyList = new HashMap<Integer,Integer>();
        System.out.println("ショップへようこそ。");

        while (!leaveFlag) {
            System.out.println("なにをかいますか？(-1でもどる)\n");
            displayMenu(bossKillCount);
            int itemId = Text.inputChoice();
            if (itemId == -1) {  // -1を選んだ場合マップアクションへ
                this.leaveFlag = true;
                continue;
            }
            System.out.println("いくつかいますか？(-1でこうにゅうがめんにもどる)");
            int count = Text.inputChoice();
            if (count == -1) {   // -1を選んだ場合ショップのアイテム一覧へ
                continue;
            }
            buyList.put(itemId, count);
            System.out.println("おかいあげありがとうございます。");
            System.out.println("ほかにもなにかかいますか？");
            System.out.println("かう：0　かわない：-1");
            int choice = Text.inputChoice();
            if (choice == -1) {
                this.leaveFlag = true;
                continue;
            } else {
                continue;
            }
        }
        return buyList;
    }
    public void displayMenu(int bossKillCount) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("..\\..\\data\\ItemId_data.csv"));
        String str = br.readLine();
        while(str != null) {
            String[] itemArray = str.split(",");
            if (bossKillCount <= Integer.parseInt(itemArray[4])) {
                return;
            }
            System.out.println(itemArray[0] + "：" + itemArray[3] + "m -> " + Integer.parseInt(itemArray[1]));
            str = br.readLine();
        }
    }
}
