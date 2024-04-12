package equipment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Equipment {
    private String name;        // 名前
    private int equipmentId;    // ID
    private int value;          // ステータス値
    private int price;          // 値段
    private String explanation; // 説明

    // コンストラクタ
    public Equipment(String name) {
        this.name = name;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Equipment_data.csv"));
            String data = br.readLine();
            while(data != null) {
                if (data.contains(getName())) {
                    Object[] dataArray = data.split(",");
                    this.equipmentId = (int)(dataArray[1]);
                    this.value = (int)(dataArray[2]);
                    this.price = (int)(dataArray[3]);
                    this.explanation = (String)(dataArray[4]);
                }
                data = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getEquipmentId() { return this.equipmentId; }
    public int getPrice() { return this.price; }
    public int getValue() { return this.value; }
    public String getExplanation() { return this.explanation; }

    public void setName(String name) { this.name = name; }
}
