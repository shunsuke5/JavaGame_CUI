package enemy;

public abstract class Enemy {
    private String name;    // モンスター名
    private int level;      // レベル、これを用いて逃げるかの判断をする
    private int hp;         // 体力
    private int mp;         // 魔力
    private int attack;     // 攻撃力、これが通常攻撃の最低値となる
    private int defense;    // 防御力
    private int agility;    // すばやさ
    private int point;      // 経験値
    private int dropMoney;      // 落とすお金
    private boolean escapeFlag;     // 敵が逃げた時にtrue
    private int enemyCount; // 敵の数、今回は使わない予定

    // 抽象メソッド
    public abstract int turn(String braveName, int braveDefense);    // 敵の行動をランダムに決めるメソッド
    
    // メソッド
    public int attack(String braveName, int braveDefense) {
        // ミス、通常攻撃、痛恨の一撃のどれが出るかをランダムに決定する
        int result = new java.util.Random().nextInt(100) + 1;

        if (1 <= result && result <= 10) {  // 1から10が出たらミス
            System.out.println("ミス！" + braveName + "はダメージをうけない！");
            return 0;
        } else if (95 <= result && result >= 100) {     // 95から100が出たら痛恨の一撃
            int damage = calculateDamage(braveDefense) * 2;
            System.out.println("つうこんのいちげき！");
            System.out.println(braveName + "に" + damage + "ポイントのダメージ！");
            return damage;
        } else {    // それ以外は通常攻撃
            int damage = calculateDamage(braveDefense);
            System.out.println(braveName + "に" + damage + "ポイントのダメージ！");
            return damage;
        }
    }
    public int calculateDamage(int braveDefense) {      // ダメージ値を計算して返す
        final int DEFAULT_RANGE = 1;
        int attackRange = (this.attack % 4) + DEFAULT_RANGE;    // 攻撃力が4増える毎にダメージ範囲を +1
        int enemyAttack = new java.util.Random().nextInt(attackRange) + this.attack;
        int damage = enemyAttack - braveDefense;
        damage = adjustDamage(damage);
        return damage;
    }
    public int adjustDamage(int damage) {   // ダメージ値がマイナス値だった場合に0に変換する
        if (damage < 0) {
            return 0;
        } else {
            return damage;
        }
    }
    public void run() {
        System.out.println(this.name + "はにげだした！");
        this.escapeFlag = true;
    }
    public boolean runJadgement(int braveLevel) {
        // 勇者と自身のレベルを比較し、相手の方が大きければ大きいほど逃げる確率を高くする
        // trueであれば逃げる、falseであれば逃げない
        int levelGap = braveLevel - this.level;
        if (levelGap < 0) {     // levelGapが負の値なら0に変換
            levelGap = 0;
        }
        int runProbability = levelGap * 5;
        // 範囲が100の乱数を用意し、1からrunProbabilityの数までが出たら逃げる
        // それよりも大きい数が出たら逃げない、といった処理を行う
        int result = new java.util.Random().nextInt(100) + 1;
        if (result < runProbability) {
            return true;
        } else {
            return false;
        }
    }

    // アクセサ
    public String getName() { return this.name; }
    public int getLevel() { return this.level; }
    public int getHp() { return this.hp; }
    public int getMp() { return this.mp; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getAgility() { return this.agility; }
    public int getPoint() { return this.point; }
    public int getMoney() { return this.dropMoney; }
    public int getEnemyCount() { return this.enemyCount; }
    public boolean getEscapeFlag() { return this.escapeFlag; }

    public void setName(String name) { this.name = name; }
    public void setLevel(int level) { this.level = level; }
    public void setHp(int hp) { this.hp = hp; }
    public void setMp(int mp) { this.mp = mp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setAgility(int agility) { this.agility = agility; }
    public void setPoint(int point) { this.point = point; }
    public void setMoney(int dropMoney) { this.dropMoney = dropMoney; }
    public void setEnemyCount(int enemyCount) { this.enemyCount = enemyCount; }
    public void setEscapeFlag(boolean escapeFlag) { this.escapeFlag = escapeFlag; }
}