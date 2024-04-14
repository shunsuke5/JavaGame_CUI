package shop.equipmentshop;
import battlechar.brave.Brave;
import text.Text;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EquipmentShop {
    private boolean isLeave;
    // メソッド
    public void sell(Brave brave) {
        this.isLeave = false;
        System.out.println("ショップへようこそ。");

        while (!isLeave) {
            System.out.println("なにをかいますか？(-1でマップにもどる)\n");
            displayMenu(brave.getBossKillCount());
            int equipmentId = Text.inputChoice();
            if (equipmentId == -1) {                 // -1を選んだ場合マップアクションへ
                this.isLeave = true;
                continue;
            }
            System.out.println("いくつかいますか？(-1でこうにゅうがめんにもどる)");
            int count = Text.inputChoice();
            if (count == -1) {                  // -1を選んだ場合ショップのアイテム一覧へ
                continue;
            }
            int price = payment(equipmentId, count); // 金額の計算
            if (brave.getMoney() < price) {
                System.out.println("おかねがたりません！");
                continue;
            }
            System.out.println("おかいけい" + price + "マネーです。よろしいですか？");
            System.out.println("かう：0　かわない：-1");
            if (Text.inputChoice() == -1) {
                System.out.println("いやひやかしかーーーーい");
                continue;
            }
            brave.setMoney(brave.getMoney() - price);
            brave.getEquipmentBag().increase(equipmentId, count);
            
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
    private int payment(int equipmentId, int count) {  // 金額を返す
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\..\\equipment\\Equipment_data.csv"));
            String data = br.readLine();
            while(data != null) {
                if (data.contains(Integer.toString(equipmentId))) {
                    Object[] dataArray = data.split(",");
                    br.close();
                    return (int)(dataArray[3]) * count;
                }
                data = br.readLine();
            }
            br.close();
            return -1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return -1;
        }
    }
    private void displayMenu(int bossKillCount) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\..\\equipment\\Equipment_data.csv"));
            String data = br.readLine();
            while(data != null) {
                Object[] dataArray = data.split(",");
                if (bossKillCount <= (int)(dataArray[4])) {
                    br.readLine();
                    continue;
                }
                System.out.println(dataArray[0] + "：" + dataArray[3] + "m -> " + (int)(dataArray[1]));
                data = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}
