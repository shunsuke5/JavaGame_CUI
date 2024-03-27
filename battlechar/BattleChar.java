package battlechar;

import state.*;

public abstract class BattleChar {
    private String name;            // 名前

    private int hp;                 // 体力
    private int maxHp;              // 最大体力
    private int mp;                 // 魔力
    private int maxMp;              // 最大魔力

    private int inBattleAttack;     // バトル時の攻撃力(状態によって変化)
    private int defaultAttack;      // 通常の攻撃力

    private int inBattleDefense;    // バトル時の防御力(状態によって変化)
    private int defaultDefense;     // 通常の防御力

    private int inBattleAgility;    // バトル時のすばやさ(状態によって変化)
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
    public void damaged(int damage) {
        setHp(getHp() - damage);
    }
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
    // アクセサ
    public String getName() { return this.name; }
    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getMp() { return this.mp; }
    public int getMaxMp() { return this.maxMp; }
    public int getInBattleAttack() { return this.inBattleAttack; }
    public int getDefaultAttack() { return this.defaultAttack; }
    public int getInBattleDefense() { return this.inBattleDefense; }
    public int getDefaultDefense() { return this.defaultDefense; }
    public int getInBattleAgility() { return this.inBattleAgility; }
    public int getDefaultAgility() { return this.defaultAgility; }
    public int getTurnCount() { return this.turnCount; }
    public State getState() { return this.state; }
    public int getAbnormalTurnPeriod() { return this.abnormalTurnPeriod; }

    public void setName(String name) { this.name = name; }
    public void setHp(int hp) { this.hp = hp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }
    public void setMp(int mp) { this.mp = mp; }
    public void setMaxMp(int maxMp) { this.maxMp = maxMp; }
    public void setInBattleAttack(int inBattleAttack) { this.inBattleAttack = inBattleAttack; }
    public void setDefaultAttack(int defaultAttack) { this.defaultAttack = defaultAttack; }
    public void setInBattleDefense(int inBattleDefense) { this.inBattleDefense = inBattleDefense; }
    public void setDefaultDefense(int defaultDefense) { this.defaultDefense = defaultDefense; }
    public void setInBattleAgility(int inBattleAgility) { this.inBattleAgility = inBattleAgility; }
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
