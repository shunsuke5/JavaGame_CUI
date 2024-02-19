package equipment;

public abstract class Equipment {
    private String name;
    private String explanation;

    // コンストラクタ
    public Equipment(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }
    // アクセサ
    public String getName() { return this.name; }
    public String getExplanation() { return this.explanation; }
    public void setName(String name) { this.name = name; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}