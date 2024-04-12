package equipment.sword;
import equipment.Equipment;

public class Sword extends Equipment {
    private int swordId;
    private int attack;

    // コンストラクタ
    public Sword(String name) {
        super(name);
    }
    // アクセサ
    public int getAttack() { return this.attack; }
    public int getSwordId() { return this.swordId; }
}