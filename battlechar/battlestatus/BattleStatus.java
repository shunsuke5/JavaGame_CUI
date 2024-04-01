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
    public void changedStatus(String statusanyone, int changeValueStep) {
        // つうじょうにもどる
        if ((this.value == TWO_DOWN_VALUE && changeValueStep == 2) || 
            (this.value == ONE_DOWN_VALUE && changeValueStep == 1) || 
            (this.value == ONE_UP_VALUE && changeValueStep == -1)  || 
            (this.value == TWO_UP_VALUE && changeValueStep == -2)) {
                System.out.println(statusanyone + "の" + this.name +"がもとにもどった！");
                this.value = DEFAULT_VALUE;
                return;
        }
        // 1アップ
        if ((this.value == DEFAULT_VALUE && changeValueStep == 1) || 
            (this.value == ONE_DOWN_VALUE && changeValueStep == 2)) {
                System.out.println(statusanyone + "の" + this.name +"がすこしあがった！");
                this.value = ONE_UP_VALUE;
                return;
        } else if(this.value == TWO_UP_VALUE && changeValueStep == -1) {
                System.out.println(statusanyone + "の" + this.name + "がすこしもどった！");
                this.value = ONE_UP_VALUE;
                return;
        }
        // 2アップ
        if ((this.value == ONE_UP_VALUE && changeValueStep == 1) || 
            (this.value == DEFAULT_VALUE && changeValueStep == 2) || 
            (this.value == ONE_UP_VALUE && changeValueStep == 2)) {
                System.out.println(statusanyone + "の" + this.name +"がかなりあがった！");
                this.value = TWO_UP_VALUE;
                return;
        }
        // 1ダウン
        if ((this.value == ONE_UP_VALUE && changeValueStep == 1) || 
            (this.value == DEFAULT_VALUE && changeValueStep == 2) || 
            (this.value == ONE_UP_VALUE && changeValueStep == 2)) {
                System.out.println(statusanyone + "の" + this.name +"がかなりあがった！");
                this.value = TWO_UP_VALUE;
                return;
        }
        // 2ダウン
        if ((this.value == ONE_UP_VALUE && changeValueStep == 1) || 
            (this.value == DEFAULT_VALUE && changeValueStep == 2) || 
            (this.value == ONE_UP_VALUE && changeValueStep == 2)) {
                System.out.println(statusanyone + "の" + this.name +"がかなりあがった！");
                this.value = TWO_UP_VALUE;
                return;
        }
        // これ以上上がりきらない
        if() {

        }
        // これ以上下がりきらない
        if() {

        }
    }
    public void changedStatus2(String anyone, int changeValueStep) {
        if (checkValue() + changeValueStep == 0) {
            System.out.println(anyone + "の" + this.name + "がもとにもどった！");
            return;
        }
        if (checkValue() + changeValueStep == 1) {
            if (changeValueStep < 0) {
                System.out.println(anyone + "の" + this.name + "がすこしもどった。");
                return;
            } else {
                System.out.println(anyone + "の" + this.name + "がすこしあがった！");
                return;
            }
        }
        if (checkValue() + changeValueStep == 2) {
            
        }
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }
}
