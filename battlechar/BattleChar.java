package battlechar;

import exception.StatusUpDownException;
import state.*;

public abstract class BattleChar {
    private String name;            // 名前

    private int hp;                 // 体力
    private int maxHp;              // 最大体力
    private int mp;                 // 魔力
    private int maxMp;              // 最大魔力

    private int battleAttack;     // バトル時の攻撃力(状態によって変化)
    private int defaultAttack;      // 通常の攻撃力

    private int battleDefense;    // バトル時の防御力(状態によって変化)
    private int defaultDefense;     // 通常の防御力

    private int battleAgility;    // バトル時のすばやさ(状態によって変化)
    private int defaultAgility;     // 通常の素早さ

    private int turnCount;          // 経過ターン数
    private State state;            // 状態
    private int abnormalTurnPeriod; // 状態異常持続ターン上限

    // 抽象メソッド
    public abstract String toString();
    // メソッド
    public void decideAbnormalTurnPeriod() {
        int abnormalTurnCount = new java.util.Random().nextInt(3) + 2;
        this.abnormalTurnPeriod = this.turnCount + abnormalTurnCount;
    }
    public void damagedHp(int damage) {
        setHp(getHp() - damage);
    }
    public void declineMp(int decline) {
        setMp(getMp() - decline);
    }
    // 回復時に使用するメソッド
    public void healHp(BattleChar user, int healPoint) {     // 回復量が最大HPを超えないように調整
        if (healPoint > (user.getMaxHp() - user.getHp())) {
            user.setHp(user.getMaxHp());
        } else {
            user.setHp(user.getHp() + healPoint);
        }
    }
    public void healMp(BattleChar user, int healPoint) {     // 回復量が最大MPを超えないように調整
        if (healPoint > (user.getMaxMp() - user.getMp())) {
            user.setMp(user.getMaxMp());
        } else {
            user.setMp(user.getMp() + healPoint);
        }
    }
    // バトル時のステータス上下用メソッド
    public void restoreBattleAttack() {
        this.battleAttack = this.defaultAttack;
    }
    public void restoreBattleDefense() {
        this.battleDefense = this.defaultDefense;
    }
    public void restoreBattleAgility() {
        this.battleAgility = this.defaultAgility;
    }
    public void upBattleAttack(double magnification) {
        try {
            if (magnification < 1) {
                throw new StatusUpDownException("ステータス「上昇」に 1 より「小さい」倍率が渡されました");
            }
        } catch (StatusUpDownException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        this.battleAttack = (int)(this.defaultAttack * magnification);
    }
    public void upBattleDefense(double magnification) {
        try {
            if (magnification < 1) {
                throw new StatusUpDownException("ステータス「上昇」に 1 より「小さい」倍率が渡されました");
            }
        } catch (StatusUpDownException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        this.battleDefense = (int)(this.defaultDefense * magnification);
    }
    public void upBattleAgility(double magnification) {
        try {
            if (magnification < 1) {
                throw new StatusUpDownException("ステータス「上昇」に 1 より「小さい」倍率が渡されました");
            }
        } catch (StatusUpDownException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        this.battleAgility = (int)(this.defaultAgility * magnification);
    }
    public void downBattleAttack(double magnification) {
        try {
            if (magnification < 1) {
                throw new StatusUpDownException("ステータス「降下」に 1 より「大きい」倍率が渡されました");
            }
        } catch (StatusUpDownException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        this.battleAttack = (int)(this.defaultAttack * magnification);
    }
    public void downBattleDefense(double magnification) {
        try {
            if (magnification < 1) {
                throw new StatusUpDownException("ステータス「降下」に 1 より「大きい」倍率が渡されました");
            }
        } catch (StatusUpDownException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        this.battleDefense = (int)(this.defaultDefense * magnification);
    }
    public void downBattleAgility(double magnification) {
        try {
            if (magnification < 1) {
                throw new StatusUpDownException("ステータス「降下」に 1 より「大きい」倍率が渡されました");
            }
        } catch (StatusUpDownException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        // ここにbattleステータスが0.5かどうかのチェック
        if ((getBattleAgility() * 2) < getDefaultAgility())
        this.battleAgility = (int)(this.defaultAgility * magnification);
    }
    public void upOneStepStatus() {

    }
    // アクセサ
    public String getName() { return this.name; }
    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getMp() { return this.mp; }
    public int getMaxMp() { return this.maxMp; }
    public int getBattleAttack() { return this.battleAttack; }
    public int getDefaultAttack() { return this.defaultAttack; }
    public int getBattleDefense() { return this.battleDefense; }
    public int getDefaultDefense() { return this.defaultDefense; }
    public int getBattleAgility() { return this.battleAgility; }
    public int getDefaultAgility() { return this.defaultAgility; }
    public int getTurnCount() { return this.turnCount; }
    public State getState() { return this.state; }
    public int getAbnormalTurnPeriod() { return this.abnormalTurnPeriod; }

    public void setName(String name) { this.name = name; }
    public void setHp(int hp) { this.hp = hp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }
    public void setMp(int mp) { this.mp = mp; }
    public void setMaxMp(int maxMp) { this.maxMp = maxMp; }
    public void setDefaultAttack(int defaultAttack) { this.defaultAttack = defaultAttack; }
    public void setDefaultDefense(int defaultDefense) { this.defaultDefense = defaultDefense; }
    public void setDefaultAgility(int defaultAgility) { this.defaultAgility = defaultAgility; }
    public void initializationTurnCount() { this.turnCount = 0;}
    public void plusTurnCount() { this.turnCount++;}
    public void setState(State state) {
        if (this.state.getName().equals(state.getName())) {
            System.out.println(this.name + "はすでに\s" + state.getName() + "\sじょうたいだ。");
            return;
        }
        this.state = state;
    }
    public void setAbnormalTurnPeriod(int abnormalTurnPeriod) { this.abnormalTurnPeriod = abnormalTurnPeriod; }
    public void plusAbnormalTurnPeriod() { this.abnormalTurnPeriod++; }
}
