package battlechar.battlestatus;

import battlechar.BattleChar;

public class BattleStatus {
    private String name;        // ステータス名
    private int value;          // 現在のステータス
    private int turnPeriod;     // ステータス上下のターン上限
    private boolean isChanged;  // ステータスが上下している場合true

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
    public void changedDefault(BattleChar anyone) {
        System.out.println(anyone.getName() + "の" + this.name + "がもとにもどった！");
        this.value = DEFAULT_VALUE;
    }
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
    public int decidedStatus(int changeValueStep) {
        return checkValue() + changeValueStep;
    }
    public void changedStatus(BattleChar anyone, int changeValueStep) {
        // 通常になった場合
        if (decidedStatus(changeValueStep) == 0) {
            System.out.println(anyone.getName() + "の" + this.name + "がもとにもどった！");
            this.value = DEFAULT_VALUE;
            this.isChanged = false;
            return;
        }
        // 1アップした場合
        if (decidedStatus(changeValueStep) == 1) {
            if (changeValueStep < 0) {
                System.out.println(anyone.getName() + "の" + this.name + "がすこしもどった。");
                this.value = ONE_UP_VALUE;
                isStatusChanged(anyone);
                return;
            } else {
                System.out.println(anyone.getName() + "の" + this.name + "がすこしあがった！");
                this.value = ONE_UP_VALUE;
                isStatusChanged(anyone);
                return;
            }
        }
        // 2アップした場合
        if (decidedStatus(changeValueStep) == 2) {
            System.out.println(anyone.getName() + "の" + this.name + "がかなりあがった！");
            this.value = TWO_UP_VALUE;
            isStatusChanged(anyone);
        }
        // 1アップ時に2アップ、もしくは2アップ時に1アップした場合(2アップ時に1アップのみ警告表示)
        if (decidedStatus(changeValueStep) == 3) {
            if (checkValue() == 1) {
                System.out.println(anyone.getName() + "の" + this.name + "がかなりあがった！");
                this.value = TWO_UP_VALUE;
                isStatusChanged(anyone);
            } else {
                System.out.println("しかし" + anyone.getName() + "の" + this.name + "はこれいじょうあがらない！");
                return;
            }
        }
        // 2アップ時に2アップした場合(警告表示)
        if (decidedStatus(changeValueStep) == 4) {
            System.out.println("しかし" + anyone.getName() + "の" + this.name + "はこれいじょうあがらない！");
        }
        // 1ダウンした場合
        if (decidedStatus(changeValueStep) == -1) {
            if (changeValueStep < 0) {
                System.out.println(anyone.getName() + "の" + this.name + "がすこしさがった！");
                this.value = ONE_DOWN_VALUE;
                isStatusChanged(anyone);
                return;
            } else {
                System.out.println(anyone.getName() + "の" + this.name + "がすこしもどった。");
                this.value = ONE_DOWN_VALUE;
                isStatusChanged(anyone);
                return;
            }
        }
        // 2ダウンした場合
        if (decidedStatus(changeValueStep) == -2) {
            System.out.println(anyone.getName() + "の" + this.name + "がかなりさがった！");
            this.value = TWO_DOWN_VALUE;
            isStatusChanged(anyone);
        }
        // 1ダウン時に2ダウン、もしくは2ダウン時に1ダウンした場合(2ダウン時に1ダウンのみ警告表示)
        if (decidedStatus(changeValueStep) == -3) {
            if (checkValue() == 1) {
                System.out.println(anyone.getName() + "の" + this.name + "がかなりさがった！");
                this.value = TWO_UP_VALUE;
                isStatusChanged(anyone);
            } else {
                System.out.println("しかし" + anyone.getName() + "の" + this.name + "はこれいじょうさがらない！");
                return;
            }
        }
        // 2ダウン時に2ダウンした場合(警告表示)
        if (decidedStatus(changeValueStep) == 4) {
            System.out.println("しかし" + anyone.getName() + "の" + this.name + "はこれいじょうあがらない！");
        }
    }
    public void isStatusChanged(BattleChar anyone) {
        this.isChanged = true;
        this.turnPeriod = new java.util.Random().nextInt(3) + anyone.getTurnCount();
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getTurnPeriod() { return this.turnPeriod; }
    public boolean getIsChanged() { return this.isChanged; }
    public int getValue() { return this.value; }
    public int getTwoDownValue() { return this.TWO_DOWN_VALUE; }
    public int getOneDownValue() { return this.ONE_DOWN_VALUE; }
    public int getDefaultValue() { return this.DEFAULT_VALUE; }
    public int getOneUpValue() { return this.ONE_UP_VALUE; }
    public int getTwoUpValue() { return this.TWO_UP_VALUE; }

    public void setValue(int value) { this.value = value; }
    public void setTurnPeriod(int turnPeriod) { this.turnPeriod = turnPeriod; }
    public void setIsChanged(boolean isChanged) { this.isChanged = isChanged; }
}
