package equipmentbag;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import equipment.Equipment;
import equipment.sword.*;
import equipment.helmet.*;
import equipment.armor.*;

public class EquipmentBag {
    private Equipment[][] equipment;

    // コンストラクタ
    public EquipmentBag() {   // equipment[][]の要素数をデータファイルから読み込んで初期化する処理
        int i = 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\equipment\\equipment_data.csv"));
            String data = br.readLine();
            while(data != null) {
                i++;
                data = br.readLine();
            }
            this.equipment = new Equipment[i][99];
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // メソッド
    public void increase(int equipmentId, int add) {  // アイテムが増える動作
        int equipmentCount = checkStorage(equipmentId);
        int total = equipmentCount + add;    // アイテムの合計所持数 = 現在のアイテム所持数 + 増えるアイテム数

        for (int i = equipmentCount; i < total; i++) {
            this.equipment[equipmentId][i] = createEquipment(equipmentId);    // ここでアイテムを生成したい　new アイテム();　としたい
        }
    }
    public void decrease(int equipmentId) {           // アイテムが減る動作
        this.equipment[equipmentId][checkStorage(equipmentId)] = null;
    }
    public void displayEquipmentBag() {
        // equipment_data.csvファイルから各アイテムの識別番号を取得していく
        int equipmentId;
        String equipmentName;

        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\equipment\\equipment_data.csv"));
            String data = br.readLine();
            while(data != null) {
                String[] dataArray = data.split(",");
                equipmentName = dataArray[0];
                equipmentId = Integer.parseInt(dataArray[1]);
                if (checkStorage(equipmentId) == 0) {
                    data = br.readLine();
                } else {
                    int equipmentCount = checkStorage(equipmentId);
                    System.out.println(equipmentName + "：" + equipmentCount + "こ");
                    data = br.readLine();
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    public int checkStorage(int equipmentId) {       // 配列の要素がどこまで入っているかを調べる
        int i = 0;
        while(this.equipment[equipmentId][i] != null) {
            i++;
        }
        return i;
    }
    public Equipment createEquipment(int equipmentId) {
        String[] dataArray = equipmentLookUp(equipmentId);
        String equipmentName = dataArray[0];
        switch(equipmentName) {
            case "へいしのつるぎ":
                return new SoldierSword();
            case "どうのつるぎ":
                return new BronzeSword();
            case "てつのつるぎ":
                return new IronSword();
            case "はがねのつるぎ":
                return new SteelSword();
            case "ゆうしゃのつるぎ":
                return new BraveSword();
            case "へいしのかぶと":
                return new SoldierHelmet();
            case "どうのかぶと":
                return new BronzeHelmet();
            case "てつのかぶと":
                return new IronHelmet();
            case "はがねのかぶと":
                return new SteelHelmet();
            case "ゆうしゃのかぶと":
                return new BraveHelmet();
            case "へいしのよろい":
                return new SoldierArmor();
            case "どうのよろい":
                return new BronzeArmor();
            case "てつのよろい":
                return new IronArmor();
            case "はがねのよろい":
                return new SteelArmor();
            case "ゆうしゃのよろい":
                return new BraveArmor();
        

        }
        return null;
    }
    public String[] equipmentLookUp(int equipmentId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\equipment\\Equipment_data.csv"));
            String data = br.readLine();
            while(data != null) {
                if (data.contains(Integer.toString(equipmentId))) {
                    String[] dataArray = data.split(",");
                    br.close();
                    return dataArray;
                }
                data = br.readLine();
            }
            br.close();
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return null;
        }
    }
    // アクセサ
    public Equipment[][] getEquipment() { return this.equipment; }
}
