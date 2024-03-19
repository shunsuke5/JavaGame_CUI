package battlechar.enemy;
import battlechar.BattleChar;
import battlechar.brave.*;

public abstract class Enemy extends BattleChar {
    private String name;        // モンスター名
    private int enemyId;        // 敵ID
    private int level;          // レベル、これを用いて逃げるかの判断をする
    private int point;          // 経験値
    private int dropMoney;      // 落とすお金
    private int turnCount;      // ターン経過数
    private boolean isEscape;   // 逃げた時にtrue
    private int enemyCount;     // 敵の数、今回は使わない予定

    // コンストラクタ
    public Enemy(String name) {
        this.name = name;
        this.turnCount = 0;
    }

    // 抽象メソッド
    public abstract void turn(Brave brave);                     // 敵の行動をランダムに決めるメソッド
    
    // メソッド
    public void attack(Brave brave) {
        // ミス、通常攻撃、痛恨の一撃のどれが出るかをランダムに決定する
        int result = new java.util.Random().nextInt(100) + 1;

        if (1 <= result && result <= 10) {                      // 1から10が出たらミス
            System.out.println("ミス！" + brave.getName() + "はダメージをうけない！");
        } else if (95 <= result && result >= 100) {             // 95から100が出たら痛恨の一撃
            int damage = calculateDamage(brave.getInBattleDefense()) * 2;
            brave.setHp(brave.getHp() - damage);
            System.out.println("つうこんのいちげき！");
            System.out.println(brave.getName() + "に" + damage + "ポイントのダメージ！");
        } else {                                                // それ以外は通常攻撃
            int damage = calculateDamage(brave.getInBattleDefense());
            brave.setHp(brave.getHp() - damage);
            System.out.println(brave.getName() + "に" + damage + "ポイントのダメージ！");
        }
        plusTurnCount();
    }
    public int calculateDamage(int braveDefense) {              // ダメージ値を計算して返す
        final int DEFAULT_RANGE = 1;
        int attackRange = (getInBattleAttack() % 4) + DEFAULT_RANGE;    // 攻撃力が4増える毎にダメージ範囲を +1
        int enemyAttack = new java.util.Random().nextInt(attackRange) + getInBattleAttack();
        int damage = enemyAttack - braveDefense;
        damage = controlDamage(damage);
        return damage;
    }
    public int controlDamage(int damage) {                      // ダメージ値がマイナス値だった場合に0に変換する
        if (damage < 0) {
            return 0;
        } else {
            return damage;
        }
    }
    public void run() {
        System.out.println(this.name + "はにげだした！");
        this.isEscape = true;
    }
    public boolean isRun(int braveLevel) {                      // trueであれば逃げる、falseであれば逃げない
        // 勇者と自身のレベルを比較し、相手の方が大きければ大きいほど逃げる確率を高くする
        int levelGap = braveLevel - this.level;
        if (levelGap < 0) {                                     // levelGapが負の値なら0に変換
            levelGap = 0;
        }
        int runProbability = levelGap * 5;
        // 範囲が100の乱数を用意し、1からrunProbability(逃げる確率)の数までが出たら逃げる
        int result = new java.util.Random().nextInt(100) + 1;
        if (result < runProbability) {
            return true;
        } else {
            return false;
        }
    }
    public int decideAction(int actionKinds) {                  // 行動を決める乱数を返す          
        return new java.util.Random().nextInt(actionKinds);
    }
    public int returnRandomNum(int min, int range) {            // 各敵行動で乱数を使いたい時に使用
        return new java.util.Random().nextInt(range) + min;
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getEnemyId() { return this.enemyId; }
    public int getLevel() { return this.level; }
    public int getPoint() { return this.point; }
    public int getMoney() { return this.dropMoney; }
    public int getEnemyCount() { return this.enemyCount; }
    public boolean getIsEscape() { return this.isEscape; }
    public int getTurnCount() { return this.turnCount; }

    public void setName(String name) { this.name = name; }
    public void setEnemyId(int enemyId) { this.enemyId = enemyId; }
    public void setLevel(int level) { this.level = level; }
    public void setPoint(int point) { this.point = point; }
    public void setMoney(int dropMoney) { this.dropMoney = dropMoney; }
    public void setEnemyCount(int enemyCount) { this.enemyCount = enemyCount; }
    public void setIsEscape(boolean isEscape) { this.isEscape = isEscape; }
    public void setTurnCount() { this.turnCount = 0; }

    public void plusTurnCount() { this.turnCount++; }
}