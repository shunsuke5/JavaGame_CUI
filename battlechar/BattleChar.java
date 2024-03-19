package battlechar;

import battlechar.state.*;

public abstract class BattleChar {
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

    private State state;            // 状態

    // メソッド
    public void setIsUsually() { this.state = new IsUsually(); }
    public void setIsPoison() { this.state = new IsPoison(); }
    public void setIsCursed() { this.state = new IsCursed(); }
    public void setIsSleep() { this.state = new IsSleep(); }
    public void setIsParalysis() { this.state = new IsParalysis(); }

    // アクセサ
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
    public State getState() { return this.state; }

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
    public void setState(State state) { this.state = state; }
}
