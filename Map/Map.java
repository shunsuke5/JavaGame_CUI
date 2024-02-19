package map;

public abstract class Map {
    private String name;
    
    // コンストラクタ
    public Map(String name) {
        this.name = name;
    }
    // アクセサ
    public String getName() { return this.name; }
}