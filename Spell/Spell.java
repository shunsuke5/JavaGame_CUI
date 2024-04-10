package spell;
import battlechar.BattleChar;
import state.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Spell{
    private String name;
    private int spellId;
    private int needLevel;
    private int consumptionMp;
    private int minpoint;
    private int pointRange;
    private String giveAbnormal;
    private String explanation;

    // コンストラクタ
    public Spell(String name) {
        this.name = name;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Spell_data.csv"));
            String data = br.readLine();
            while(data != null) {
                if (data.contains(this.name)) {
                    Object[] dataArray = data.split(",");
                    this.spellId = (int)(dataArray[1]);
                    this.needLevel = (int)(dataArray[2]);
                    this.consumptionMp = (int)(dataArray[3]);
                    this.minpoint = (int)(dataArray[4]);
                    this.pointRange = (int)(dataArray[5]);
                    this.giveAbnormal = (String)(dataArray[6]);
                    this.explanation = (String)(dataArray[7]);
                }
                data = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // 抽象メソッド
    public abstract void resite(BattleChar user, BattleChar receiver);
    public abstract void resite(BattleChar user, BattleChar receiver, State state, int probability);
    // アクセサ
    public String getName() { return this.name; }
    public int getSpellId() { return this.spellId; }
    public int getNeedLevel() { return this.needLevel; }
    public int getConsumptionMp() { return this.consumptionMp; }
    public int getMinPoint() { return this.minpoint; }
    public int getPointRange() { return this.pointRange; }
    public String getGiveAbnormal() { return this.giveAbnormal; }
    public String getExplanation() { return this.explanation; }
}