package spell;

public abstract class Spell{
    private String name;
    private String explanation;

    // アクセサ
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public String getExplanation() { return this.explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}