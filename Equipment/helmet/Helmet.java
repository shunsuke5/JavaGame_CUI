package equipment.helmet;
import equipment.Equipment;

public class Helmet extends Equipment {
    private int helmetId;
    private int defense;

    // コンストラクタ
    public Helmet(String name) {
        super(name);
    }
    // アクセサ
    public int getHelmetId() { return this.helmetId; }
    public int getDefense() { return this.defense; }
}