package equipment.armor;
import equipment.Equipment;

public class Armor extends Equipment {
    private int armorId;
    private int defense;

    // コンストラクタ
    public Armor(String name) {
        super(name);
    }
    // アクセサ
    public int getArmorId() { return this.armorId; }
    public int getDefense() { return this.defense; }
}