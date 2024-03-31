package battlechar.battlestatus;

import battlechar.BattleChar;

public class BattleStatus {
    private String name;        // ステータス名
    private int value;          // 現在のステータス

    private final int TWO_DOWN_VALUE;   // 2ダウン
    private final int ONE_DOWN_VALUE;   // 1ダウン
    private final int DEFAULT_VALUE;   // 通常時
    private final int ONE_UP_VALUE;     // 1アップ
    private final int TWO_UP_VALUE;     // 2アップ
    // コンストラクタ
    public BattleStatus(String name, int value) {
        this.name = name;
        this.TWO_DOWN_VALUE = (int)(value * 0.5);
        this.ONE_DOWN_VALUE = (int)(value * 0.75);
        this.DEFAULT_VALUE = value;
        this.ONE_UP_VALUE = (int)(value * 1.25);
        this.TWO_UP_VALUE = (int)(value * 1.5);
    }
    // メソッド
    public int checkValue() {
        if (this.value == TWO_DOWN_VALUE) {
            return -2;
        } else if(this.value == ONE_DOWN_VALUE) {
            return -1;
        } else if(this.value == DEFAULT_VALUE) {
            return 0;
        } else if(this.value == ONE_UP_VALUE) {
            return 1;
        } else {
            return 2;
        }
    }
    public void twoUpValue(String uppedchar) {
        if (this.value == TWO_DOWN_VALUE) {
            this.value = DEFAULT_VALUE;
            System.out.println(uppedchar + "のこうげきがもとにもどった。");
            return;
        } else if(this.value == ONE_DOWN_VALUE) {
            this.value = ONE_UP_VALUE;
            System.out.println(uppedchar + "のこうげきが1だんかいあがった！");
            return;
        } else if(this.value == DEFAULT_VALUE) {
            this.value = TWO_UP_VALUE;
            System.out.println(uppedchar + "のこうげきが2だんかいあがった！");
            return;
        } else if(this.value == ONE_UP_VALUE) {
            this.value = TWO_UP_VALUE;
            System.out.println(uppedchar + "のこうげきが1だんかいあがった！");
        } else {
            System.out.println(uppedchar + "のこうげきはこれいじょうあがらない。");
        }
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }
}
