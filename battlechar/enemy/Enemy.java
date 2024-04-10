package battlechar.enemy;
import battlechar.BattleChar;
import battlechar.brave.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Enemy extends BattleChar {
    private int enemyId;            // 敵ID
    private int level;              // レベル、これを用いて逃げるかの判断をする
    private int point;              // 経験値
    private int money;          // 落とすお金
    private boolean isEscape;       // 逃げた時にtrue
    private String appearanceMap;   // 出現マップ
    private String classfication;   // 分類(通常敵かボスか)

    // コンストラクタ
    public Enemy(String name) {
        setName(name);
        try {
            BufferedReader br = new BufferedReader(new FileReader("Enemy_data.csv"));
            String str = br.readLine();
            // 名前,ID,レベル,HP,MP,攻撃力,防御力,すばやさ,経験値,マネー,出現マップ,区分
            while(str != null) {
                if (str.contains(getName())) {
                    Object[] dataArray = str.split(",");
                    this.enemyId = ((int)(dataArray[1]));
                    this.level = ((int)(dataArray[2]));
                    setHp((int)(dataArray[3]));
                    setMp((int)(dataArray[4]));
                    setDefaultAttack((int)(dataArray[5]));
                    setDefaultDefense((int)(dataArray[6]));
                    setDefaultAgility((int)(dataArray[7]));
                    this.point = ((int)(dataArray[8]));
                    this.money = ((int)(dataArray[9]));
                    this.appearanceMap = (String)(dataArray[10]);
                    this.classfication = (String)(dataArray[11]);
                }
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }

    // 抽象メソッド
    public abstract void turn(Brave brave);                     // 敵の行動をランダムに決めるメソッド
    
    // メソッド
    protected void attack(Brave brave) {
        // ミス、通常攻撃、痛恨の一撃のどれが出るかをランダムに決定する
        int result = new java.util.Random().nextInt(100) + 1;

        if (1 <= result && result <= 10) {                      // 1から10が出たらミス
            System.out.println("ミス！" + brave.getName() + "はダメージをうけない！");
        } else if (95 <= result && result >= 100) {             // 95から100が出たら痛恨の一撃
            int damage = calculateDamage(brave.getBattleDefense().getValue()) * 2;
            brave.damagedHp(damage);
            System.out.println("つうこんのいちげき！");
            System.out.println(brave.getName() + "に" + damage + "ポイントのダメージ！");
        } else {                                                // それ以外は通常攻撃
            int damage = calculateDamage(brave.getBattleDefense().getValue());
            brave.damagedHp(damage);
            System.out.println(brave.getName() + "に" + damage + "ポイントのダメージ！");
        }
    }
    protected int calculateDamage(int braveDefense) {              // ダメージ値を計算して返す
        final int DEFAULT_RANGE = 1;
        int attackRange = (getBattleAttack().getValue() / 4) + DEFAULT_RANGE;    // 攻撃力が4増える毎にダメージ範囲を +1
        int enemyAttack = new java.util.Random().nextInt(attackRange) + getBattleAttack().getValue();
        int damage = enemyAttack - braveDefense;
        damage = controlDamage(damage);
        return damage;
    }
    private int controlDamage(int damage) {                      // ダメージ値がマイナス値だった場合に0に変換する
        if (damage < 0) {
            return 0;
        } else {
            return damage;
        }
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
            System.out.println(getName() + "はにげだした！");
            this.isEscape = true;
            return true;
        } else {
            return false;
        }
    }
    protected int decideAction(int actionKinds) {                  // 行動を決める乱数を返す          
        return new java.util.Random().nextInt(actionKinds);
    }
    protected int returnRandomNum(int min, int range) {            // 各敵行動で乱数を使いたい時に使用
        return new java.util.Random().nextInt(range) + min;
    }
    protected boolean isSuccessGiveAbnormality(int probability) {  // 状態異常を相手に付与する時にtrue
        int result = new java.util.Random().nextInt(100);
        return 0 < result && result < probability;
    }
    public String toString() {
        return "Enemy";
    }
    // アクセサ
    public int getEnemyId() { return this.enemyId; }
    public int getLevel() { return this.level; }
    public int getPoint() { return this.point; }
    public int getMoney() { return this.money; }
    public boolean getIsEscape() { return this.isEscape; }
    public String getAppearanceMap() { return this.appearanceMap; }
    public String getClassfication() { return this.classfication; }

    public void setMoney(int money) { this.money = money; }
    public void setIsEscape(boolean isEscape) { this.isEscape = isEscape; }
}