package battlechar.battlestatus;

public class BattleStatus {
    private String name;    // ステータス名
    private int value;      // ステータス値
    // コンストラクタ
    public BattleStatus(String name) {
        this.name = name;
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }
}
