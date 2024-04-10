package battlechar;

import battlechar.battlestatus.BattleAgility;
import battlechar.battlestatus.BattleAttack;
import battlechar.battlestatus.BattleDefense;
import state.*;
import text.Text;

public abstract class BattleChar {
    private String name;                    // 名前

    private int hp;                         // 体力
    private int maxHp;                      // 最大体力
    private int mp;                         // 魔力
    private int maxMp;                      // 最大魔力

    private int defaultAttack;              // 通常の攻撃力
    private BattleAttack battleAttack;      // バトル時のこうげきりょく
    private int defaultDefense;             // 通常の防御力
    private BattleDefense battleDefense;    // バトル時のぼうぎょりょく
    private int defaultAgility;             // 通常の素早さ
    private BattleAgility battleAgility;    // バトル時のすばやさ

    private int turnCount;                  // 経過ターン数
    private State state;                    // 状態
    private int abnormalTurnPeriod;         // 状態異常持続ターン上限

    
    private int statusTurnPeriod;           // ステータス上下持続ターン上限

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
    // ステータス上下処理
    public void statusUpDown() {
        if (getBattleAttack().getIsChanged()) {
            if (getBattleAttack().getTurnPeriod() == getTurnCount()) {
                getBattleAttack().changedDefault();
                getBattleAttack().setIsChanged(false);
            }
        }
        if (getBattleDefense().getIsChanged()) {
            if (getBattleDefense().getTurnPeriod() == getTurnCount()) {
                getBattleDefense().changedDefault();
                getBattleDefense().setIsChanged(false);
            }
        }
        if (getBattleAgility().getIsChanged()) {
            if (getBattleAgility().getTurnPeriod() == getTurnCount()) {
                getBattleAgility().changedDefault();
                getBattleAgility().setIsChanged(false);
            }
        }
    }
    // バトル開始時のステータス初期化メソッド
    public void initializationBattleStatus() {
        this.battleAttack = new BattleAttack(this);
        this.battleDefense = new BattleDefense(this);
        this.battleAgility = new BattleAgility(this);
    }
    // バトル終了時のステータス復元メソッド
    public void restoreBattleStatus() {
        this.battleAttack.setValue(this.defaultAttack);
        this.battleDefense.setValue(this.defaultDefense);
        this.battleAgility.setValue(this.defaultAgility);
    }
    // 状態異常終了判定
    public void judgeAbnormalPeriod() {
        if (getTurnCount() == getAbnormalTurnPeriod()) {    // 状態異常終了判定
            Text.healAbnormalState(this);
            setState(new IsUsually());
        }
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getMp() { return this.mp; }
    public int getMaxMp() { return this.maxMp; }
    public BattleAttack getBattleAttack() { return this.battleAttack; }
    public int getDefaultAttack() { return this.defaultAttack; }
    public BattleDefense getBattleDefense() { return this.battleDefense; }
    public int getDefaultDefense() { return this.defaultDefense; }
    public BattleAgility getBattleAgility() { return this.battleAgility; }
    public int getDefaultAgility() { return this.defaultAgility; }
    public int getTurnCount() { return this.turnCount; }
    public State getState() { return this.state; }
    public int getAbnormalTurnPeriod() { return this.abnormalTurnPeriod; }
    public int getStatusTurnPeriod() { return this.statusTurnPeriod; }

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
