package spell;
import battlechar.brave.Brave;
import battlechar.enemy.Enemy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Spell{
    private String name;
    private int needLevel;
    private int consumptionMp;
    private int minpoint;
    private int pointRange;
    private String explanation;

    // コンストラクタ
    public Spell(String name) {
        this.name = name;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Spell_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(this.name)) {
                    String[] itemArray = str.split(",");
                    this.needLevel = Integer.parseInt(itemArray[1]);
                    this.consumptionMp = Integer.parseInt(itemArray[2]);
                    this.minpoint = Integer.parseInt(itemArray[3]);
                    this.pointRange = Integer.parseInt(itemArray[4]);
                    this.explanation = itemArray[5];
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // 抽象メソッド
    public abstract void resite(Brave b, Enemy e);
    // アクセサ
    public String getName() { return this.name; }
    public int getNeedLevel() { return this.needLevel; }
    public int getConsumptionMp() { return this.consumptionMp; }
    public int getMinPoint() { return this.minpoint; }
    public int getPointRange() { return this.pointRange; }
    public String getExplanation() { return this.explanation; }

    public void setName(String name) { this.name = name; }
    public void setNeedLevel(int needLevel) { this.needLevel = needLevel; }
    public void setConsumptionMp(int consumptionMp) { this.consumptionMp = consumptionMp; }
    public void setMinPoint(int minpoint) { this.minpoint = minpoint; }
    public void setPointRange(int pointRange) { this.pointRange = pointRange; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}