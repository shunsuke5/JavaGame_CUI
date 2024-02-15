public class Sword extends Equipment {
    private int attack;

    // コンストラクタ
    public Sword(String name, String explanation, int attack) {
        super(name,explanation);
        this.attack = attack;
    }
    // アクセサ
    public int getAttack() { return this.attack; }
    public void setAttack(int attack) { this.attack = attack; }
}