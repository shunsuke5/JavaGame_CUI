package Map;

public abstract class Map {
    private String name;
    private String field_1;
    private String field_2;
    private String field_3;
    
    // コンストラクタ
    public Map(String name, String field_1, String field_2, String field_3) {
        this.name = name;
        this.field_1 = field_1;
        this.field_2 = field_2;
        this.field_3 = field_3;
    }
}