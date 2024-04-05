package battlechar.brave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import map.*;
import shop.itemshop.ItemShop;
import spell.*;
import spell.attackspell.*;
import spell.healspell.*;
import state.IsUsually;
import battlechar.BattleChar;
import battlechar.enemy.Enemy;
import equipment.*;
import equipment.sword.*;
import equipment.helmet.*;
import equipment.armor.*;
import text.Text;
import item.Item;
import item.hpitem.*;
import item.mpitem.*;
import itembag.ItemBag;

public class Brave extends BattleChar {
    // 基礎ステータス
    private int level;      // レベル、上限は20
    private int levelPoint; // 経験値
    private int power;      // ちから
    private int protect;    // みのまもり

    // 所持金
    private int money;

    // 装備やアイテム
    private Sword sword;                        // 剣
    private Helmet helmet;                      // かぶと
    private Armor armor;                        // よろい
    private ItemBag itemBag;                    // 自作アイテムバッグクラス
    private ArrayList<Equipment> equipmentBag;  // 所持そうび一覧

    // 呪文
    private Spell spell;

    // マップ情報
    private Map forestMap = new Forest();
    private Map seaMap = new Sea();
    private Map mountainMap = new Mountain();

    // プログラム用変数
    private boolean winBattle;          // バトルに勝った時にtrue
    private boolean loseBattle;         // バトルに負けた時にtrue
    private boolean isEscape;           // バトルから逃げた時にtrue
    private int bossKillCount;          // ボスを倒した数
    
    // コンストラクタ
    public Brave() {
        System.out.print("主人公の名前を入力してください。:");
        String name = new java.util.Scanner(System.in).nextLine();
        setName(name);
        System.out.println("勇者" + getName() + "の冒険が幕を開けた…");

        this.level = 1;
        setHp(20);
        setMaxHp(20);
        setMp(5);
        setMaxMp(5);
        setPower(10);
        setProtect(10);
        setDefaultAgility(10);

        // 装備の初期化もここで行いたい
        // へいしのけん、へいしのかぶと、へいしのよろい　などを装備させる
        // 装備 + ステータスでdefaultAttack,defaultDefenseをセットする

    }
    // メソッド
    public void chooseMap() {               // どのマップに行くかの選択
        Text.chooseMap();
        // 現在いるマップを選択したらもう一度マップアクションをやりなおさせたい、whileを追加
        // そのマップに最初に行く場合、そのマップのインスタンスを生成したい
        int number = new java.util.Scanner(System.in).nextInt();
        switch(number) {
            case 1:
                System.out.println(getName() + "は" + "森へむかった！");
                currentLocation().setThereIs(false);
                this.forestMap.setThereIs(true);
                break;
            case 2:
                System.out.println(getName() + "は" + "海へむかった！");
                currentLocation().setThereIs(false);
                this.seaMap.setThereIs(true);
                break;
            case 3:
                System.out.println(getName() + "は" + "山へむかった！");
                currentLocation().setThereIs(false);
                this.mountainMap.setThereIs(true);
                break;
        }
    }
    public void chooseMapAction() {         // マップにおいてどの行動をするか選ぶメソッド
        // 違った選択肢を選ばれたら繰り返したいのでwhileを追加する
        // というか、chooseMapActionはラスボス戦まで続くので
        // while (ラスボス戦フラグ == off) のような形？
        String str = """
                　　　　　てきをさがす：1
                　　　　　やどでやすむ：2
                　　　　ショップにいく：3
                　　　　アイテムリスト：4
                　　ステータスかくにん：5
                　　ほかのマップへいく：6
                　　　　ボスとたたかう：7
                """;
        System.out.println("なにをする？");
        if (currentLocation().getEnemyKillCount() > 12) {
            str += "ひきょうをたんさくする：8";
            Text.chooseChangedText(str);
        } else {
            Text.chooseChangedText(str);
        }
        int number = new java.util.Scanner(System.in).nextInt();

        switch(number) {
            case 1:
                searchEnemy();              // 敵と戦う
                break;
            case 2:
                rest();                     // 休んでHPとMP回復
                break;
            case 3:
                shopping();                 // アイテム購入
                break;
            case 4:
                checkItemBag();             // アイテムリスト確認
                break;
            case 5:
                checkStatus();              // ステータス確認
                break;
            case 6:
                chooseMap();                // 他のマップへ移動
                break;
            case 7:
                battleBoss();               // マップボス戦
                break;
            case 8:
                searchHikyou();             // 秘境探索(中ボス)
                break;
            default:
                break;                      // 想定外の選択肢の場合、もう一度選ばせる
        }
    }
    public void searchEnemy() {             // 敵と戦う
        System.out.println(getName() + "はてきをみつけた！");
        battle(currentLocation().createEnemy());
    }
    public void rest() {                    // 休んで体力と魔力を回復する
        Text.rest();
        int choose = new java.util.Scanner(System.in).nextInt();
        if (choose == 1) {
            this.money -= 20;
            setHp(getMaxHp());
            setMp(getMaxMp());
        } else {
            return;
        }
    }
    public void shopping() {                // アイテム購入
        ItemShop itemShop = new ItemShop();
        itemShop.sell(this);
    }
    public void checkItemBag() {            // アイテムリスト確認
        int itemId = 0;

        do {
            this.itemBag.displayItemBag();
            System.out.println("つかいたいアイテムはありますか？(-1でマップへ)");
            itemId = new java.util.Random().nextInt();
            if (canUseItem(itemId)) {
                useItem(itemId);
            }
        } while(itemId != -1);
    }
    public void checkStatus() {             // ステータス確認
        String str = """
                なまえ：%s　レベル：%d
                HP：%d / %d　MP：%d / %d
                ちから：%d　まもり：%d
                すばやさ：%d
                """;
        System.out.printf(str,getName(),this.level,getHp(),getMaxHp(),getMp(),getMaxMp(),
                            getDefaultAttack(),getDefaultDefense(),getDefaultAgility());
    }

    public void battleBoss() {              // マップボス戦
        // ボスとのバトル
        // 勝ったら以下のようにボスキルカウントに+1する
        this.bossKillCount++;
        // ショップのアイテムを増やす処理を以下に記述
    }
    public void searchHikyou() {            // 秘境探索(中ボス)
        // 秘境が解放されているかのチェック
    }
    public void battle(Enemy enemy) {       // 敵とエンカウントする
        System.out.println(enemy.getName() + "があらわれた！");
        // バトルステータスとターンカウント初期化
        initializationTurnCount();
        initializationBattleStatus();
        enemy.initializationTurnCount();
        enemy.initializationBattleStatus();

        while (!this.winBattle || !this.loseBattle || !this.isEscape || !enemy.getIsEscape()) {
            if (getBattleAgility().getValue() >= enemy.getBattleAgility().getValue()) { // 勇者が先攻の場合
                judgeAbnormalPeriod();                              // 状態異常終了判定
                getState().effect(this);                            // 状態異常効果
                statusUpDown();                                     // ステータス上下処理
                while (getTurnCount() == enemy.getTurnCount()) {    // 勇者ターン
                    Text.chooseBattleAction(this);
                    int action = new java.util.Scanner(System.in).nextInt();

                    switch (action) {
                        case 1:
                            attack(enemy);
                            break;
                        case 2:
                            spell(enemy);
                            break;
                        case 3:
                            defense();
                            break;
                        case 4:
                            battleUseItem();
                            break;
                        case 5:
                            run();
                            continue;
                        default:
                            break;
                    }
                }
                if (enemy.getHp() <= 0) {                           // 勇者ターン終了、敵HPチェック
                    win(enemy);
                    continue;
                }
                if (enemy.isRun(getLevel())) {                      // 敵の逃げ判定
                    continue;
                }
                enemy.judgeAbnormalPeriod();                        // 状態異常終了判定
                enemy.getState().effect(enemy);                     // 状態異常効果
                enemy.statusUpDown();                               // 敵ステータス上下処理
                if (!(enemy.getState().getStateDetail() == "cannotAction")) {
                    enemy.turn(this);                               // 敵ターン
                }
                enemy.plusTurnCount();
                if (getHp() <= 0) {                                 // 敵ターン終了、勇者HPチェック
                    die();
                    continue;
                }
            } else {                                                // 敵が先攻の場合
                if (enemy.isRun(getLevel())) {                      // 敵の逃げ判定
                    continue;
                }
                enemy.judgeAbnormalPeriod();                        // 状態異常終了判定
                enemy.getState().effect(enemy);                     // 状態異常効果
                enemy.statusUpDown();                               // 敵ステータス上下処理
                if (!(enemy.getState().getStateDetail() == "cannotAction")) {
                    enemy.turn(this);                               // 敵ターン
                }
                enemy.plusTurnCount();
                if (getHp() <= 0) {                                 // 敵ターン終了、勇者HPチェック
                    die();
                    continue;
                }
                judgeAbnormalPeriod();                              // 状態異常終了判定
                getState().effect(this);                            // 状態異常効果
                statusUpDown();                                     // ステータス上下処理
                while(getTurnCount() != enemy.getTurnCount()) {                                                // 勇者ターン
                    Text.chooseBattleAction(this);
                    int action = new java.util.Scanner(System.in).nextInt();

                    switch (action) {
                        case 1:
                            attack(enemy);
                            break;
                        case 2:
                            spell(enemy);
                            break;
                        case 3:
                            defense();
                            break;
                        case 4:
                            battleUseItem();
                            break;
                        case 5:
                            run();
                            continue;
                        default:
                            break;
                    }
                }
                if (enemy.getHp() <= 0) {                           // 勇者ターン終了、敵HPチェック
                    win(enemy);
                    continue;
                }
            }
        }
    }
    public void attack(Enemy enemy) {       // ミス、通常攻撃、痛恨の一撃のどれが出るかをランダムに決定する
        int result = new java.util.Random().nextInt(100) + 1;

        if (1 <= result && result <= 10) {              // 1から10が出たらミス
            System.out.println("ミス！" + enemy.getName() + "はダメージをうけない！");
        } else if (95 <= result && result >= 100) {     // 95から100が出たら痛恨の一撃
            int damage = calculateDamage(enemy) * 2;
            System.out.println("かいしんのいちげき！");
            System.out.println(enemy.getName() + "に" + damage + "ポイントのダメージ！");
            enemy.setHp(enemy.getHp() - damage);
        } else {                                        // それ以外は通常攻撃
            int damage = calculateDamage(enemy);
            System.out.println(enemy.getName() + "に" + damage + "ポイントのダメージ！");
            enemy.setHp(enemy.getHp() - damage);
        }
        plusTurnCount();
    }
    public void spell(Enemy enemy) {        // 戦闘において呪文を使用するメソッド
        if (this.level < 3) {
            System.out.println("つかえるじゅもんがない！");
            return;
        }
        System.out.println("どのじゅもんをつかう？(-1でもどる)");
        displaySpell();
        int spellId = new java.util.Scanner(System.in).nextInt();
        useSpell(spellId).resite(this, enemy);
        plusTurnCount();
    }
    public void defense() {                 // 戦闘において防御するメソッド
        int strongDefense = (int) (getDefaultDefense() * 1.5);
        // この行動は素早さ関係なく勇者が先攻となる
        // そしてこのターン終了時には防御力を元に戻さなければならない
        plusTurnCount();
    }

    public void battleUseItem() {           // 戦闘においてアイテムを使用するメソッド
        System.out.println("どのアイテムをつかう？(-1でもどる)");
        this.itemBag.displayItemBag();
        int itemId = new java.util.Scanner(System.in).nextInt();
        if (itemId == -1) {
            return;
        }
        if (canUseItem(itemId)) {
            useItem(itemId);
        } else {
            return;
        }
    }
    public boolean canUseItem(int itemId) { // アイテムが使用可能か返す
        return this.itemBag.checkStorage(itemId) == 0;
    }
    public void useItem(int itemId) {       // アイテムを使用する
        if (canUseItem(itemId)) {
            this.itemBag.getItem()[itemId][0].use(this);
            this.itemBag.decrease(itemId);
        }
    }
    public void run() {     // 戦闘において逃げるメソッド
        // if (相手がボスの場合) 逃げられない
        // if (自分と相手のレベルと素早さの合計を比べてなんやかんや計算) → 逃げられるか無理かを決める
    }

    public void win(Enemy enemy) {          // 戦いに勝利
        this.winBattle = true;
        System.out.println(enemy.getName() + "をたおした！");
        System.out.println(enemy.getPoint() + "ポイントのけいけんちをかくとく！");
        if (isLevelUp(enemy)) {
            upLevel();
            checkSpellUp(this.level);
        }
        this.money += enemy.getMoney();
        System.out.println(enemy.getMoney() + "マネーをてにいれた！");
        restoreBattleStatus();
    }

    public void die() {                     // 戦いに敗北
        this.loseBattle = true;
        System.out.println(getName() + "はしんでしまった！");
    }

    public boolean isLevelUp(Enemy enemy) {
        this.levelPoint += enemy.getPoint();
        try {
            BufferedReader br = new BufferedReader(new FileReader("LevelUp_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(Integer.toString(this.level + 1))) {
                    String[] dataArray = str.split(",");
                    return this.levelPoint > Integer.parseInt(dataArray[1]);
                }
            }
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return false;
        }
    }
    public void upLevel() {
        int beforeLevel = this.level;
        try {
            BufferedReader br = new BufferedReader(new FileReader("LevelUp_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(Integer.toString(this.level + 1))) {
                    String[] dataArray = str.split(",");
                    while(this.levelPoint >= Integer.parseInt(dataArray[1])) {
                        this.level++;
                        str = br.readLine();
                        str.split(",");
                    }
                    if (beforeLevel < this.level) {
                        System.out.println(getName() + "のレベルが" + beforeLevel + 
                                            "から" + this.level + "にあがった！");
                        upStatus(beforeLevel, this.level);
                    }
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void upStatus(int beforeLevel, int afterLevel) {     // レベルアップによるステータス上昇の処理
        Object[] beforeArray = new Object[3];
        Object[] afterArray = new Object[3];
        try {
            BufferedReader br = new BufferedReader(new FileReader("LevelUp_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(Integer.toString(beforeLevel))) {
                    beforeArray = str.split(",");
                }
                if (str.contains(Integer.toString(afterLevel))) {
                    afterArray = str.split(",");
                }
                br.readLine();
            }
            // ステータス上昇処理
            this.power = (int)afterArray[2];
            this.protect = (int)afterArray[3];
            setDefaultAgility((int)afterArray[4]);
            // 上がった分のステータス値を変数に格納
            int upPower = (int)afterArray[2] - (int)beforeArray[2];
            int upDefense = (int)afterArray[3] - (int)beforeArray[3];
            int upAgility = (int)afterArray[4] - (int)beforeArray[4];

            System.out.println("ちからが\s" + upPower + "\sあがった！");
            System.out.println("まもりが\s" + upDefense + "\sあがった！");
            System.out.println("すばやさが\s" + upAgility + "\sあがった！");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    public void checkSpellUp(int afterLevel) {        // 呪文を習得できるかチェックする
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\spell\\Spell_data.csv"));
            String str = br.readLine();
            while(str != null) {
                String[] dataArray = str.split(",");
                if (dataArray[2] == (Integer.toString(afterLevel))) {
                    System.out.println(getName() + "は" + dataArray[0] + "のじゅもんがつかえるようになった！");
                    return;
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    public int calculateDamage(Enemy enemy) {   // ダメージ値を計算して返す
        final int DEFAULT_RANGE = 1;
        int attackRange = (getBattleAttack().getValue() % 4) + DEFAULT_RANGE;    // 攻撃力が4増える毎にダメージ範囲を +1
        int braveAttack = new java.util.Random().nextInt(attackRange) + getBattleAttack().getValue();
        int damage = braveAttack - enemy.getBattleDefense().getValue();
        damage = controlDamage(damage);
        return damage;
    }
    public int controlDamage(int damage) {   // ダメージ値がマイナス値だった場合に0に変換する
        if (damage < 0) {
            return 0;
        } else {
            return damage;
        }
    }
    public Map currentLocation() {  // 現在地を返す
        if (this.forestMap.isThereIs()) {
            return this.forestMap;
        } else if(this.seaMap.isThereIs()) {
            return this.seaMap;
        } else {
            return this.mountainMap;
        }
    }
    public int countBossKill() {    // ボスを倒した数を取得
        int bossKillCount = 0;
        if (this.forestMap.isBossKill()) {
            bossKillCount++;
        }
        if (this.seaMap.isBossKill()) {
            bossKillCount++;
        }
        if (this.mountainMap.isBossKill()) {
            bossKillCount++;
        }
        return bossKillCount;
    }
    public void displaySpell() {     // 習得した呪文を表示する
        String spellName;
        int spellId;
        int needLevel;

        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\spell\\Spell_data.csv"));
            String str = br.readLine();
            while(str != null) {
                String[] dataArray = str.split(",");
                spellName = dataArray[0];
                spellId = Integer.parseInt(dataArray[1]);
                needLevel = Integer.parseInt(dataArray[2]);
                if (this.level < needLevel) {
                    return;
                }
                System.out.println(spellName + "：" + spellId);
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    public Spell useSpell(int spellId) {
        String[] dataArray = spellLookUp(spellId);
        String spellName = dataArray[0];
        switch(spellName) {
            case "ピョイミ":
                return new Pyoimi();
            case "ミョラ":
                return new Myora();
            case "ベピョイミ":
                return new Bepyoimi();
            case "ミョラミ":
                return new Myorami();
            case "ミョラゾーマ":
                return new Myorazoma();
        }
        return null;
    }
    public String[] spellLookUp(int spellId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\spell\\Spell_data.csv"));
            String str = br.readLine();
            while(str != null) {
                if (str.contains(Integer.toString(spellId))) {
                    String[] dataArray = str.split(",");
                    return dataArray;
                }
                str = br.readLine();
            }
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return null;
        }
    }
    public void equipSword() {      // 剣を装備する

    }
    public void equipHelmet() {     // 兜を装備する

    }
    public void equipArmor() {      // 鎧を装備する

    }
    public String toString() {
        return "Brave";
    }
    public boolean isWin(Enemy enemy) {
        return enemy.getHp() >= 0;
    }
    public boolean isDie() {
        return getHp() <= 0;
    }
    public void judgeRunEnemy(Enemy enemy) {
        if (enemy.isRun(this.level)) {                      // 敵の逃げ判定
            enemy.run();
            continue;
        }
    }
    // アクセサ
    public int getLevel() { return this.level; }
    public int getLevelPoint() { return this.levelPoint; }
    public int getPower() { return this.power; }
    public int getProtect() { return this.protect; }
    public int getMoney() { return this.money; }
    public Sword getSword() { return this.sword; }
    public Helmet getHelmet() { return this.helmet; }
    public Armor getArmor() { return this.armor; }
    public ItemBag getItemBag() { return this.itemBag; }
    public Spell getSpell() { return this.spell; }
    public int getBossKillCount() { return this.bossKillCount; }

    public void setLevel(int level) { this.level = level; }
    public void setLevelPoint(int levelPoint) { this.levelPoint = levelPoint; }
    public void setPower(int power) { this.power = power; }
    public void setProtect(int protect) { this.protect = protect; }
    public void setMoney(int money) { this.money = money; }
    public void setSword(Sword sword) { this.sword = sword; }
    public void setHelmet(Helmet helmet) { this.helmet = helmet; }
    public void setArmor(Armor armor) { this.armor = armor; }
    public void setSpell(Spell spell) { this.spell = spell; }
}