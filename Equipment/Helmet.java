public class Helmet extends Equipment {
    private int defense;

    // コンストラクタ
    public Helmet(String name, String explanation, int defense) {
        super(name,explanation);
        this.defense = defense;
    }
    // アクセサ
    public int getDefense() { return this.defense; }
    public void setDefense(int defense) { this.defense = defense; }
}