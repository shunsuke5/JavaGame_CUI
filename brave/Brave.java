package brave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import map.*;
import spell.*;
import enemy.Enemy;
import equipment.*;
import text.Text;
import item.Item;
import item.hpitem.Herb;

public class Brave {
    // 基礎ステータス
    private String name;    // 名前
    private int level;      // レベル、上限は20
    private int levelPoint; // 経験値
    private int hp;         // 体力
    private int maxHp;      // 最大体力
    private int mp;         // 魔力
    private int maxMp;      // 最大魔力
    private int power;      // ちから
    private int protect;    // みのまもり
    private int attack;     // 総攻撃力
    private int defense;    // 総防御力
    private int agility;    // すばやさ
    private int turnCount;  // 経過ターン数

    // 所持金
    private int money;

    // 装備やアイテム
    private Sword sword;            // 剣
    private Helmet helmet;          // かぶと
    private Armor armor;            // よろい
    private ArrayList<Item> itemList;    // 所持アイテム一覧

    // 呪文
    private Spell spell;

    // マップ情報
    private Map forestMap = new Forest();
    private Map seaMap = new Sea();
    private Map mountainMap = new Mountain();

    // プログラム用変数
    private int levelIndex = 0;         // checkLevelUpメソッドでのみ使用、アクセサ不要
    private int spellIndex = 0;         // checkSpellUpメソッドでのみ使用、アクセサ不要
    private String spellFormat;         // たたかいの呪文テキスト
    private int spellChoiceNumber = 1;  // 呪文選択時の番号
    private int itemIndex = 0;          // addShopItem()でのみ使用、アクセサ不要
    private int itemChoiceNumber = 3;   // ショップのアイテム選択番号
    private String itemFormat;      // ショップのアイテムテキスト
    private boolean battleWin;          // バトルに勝った時にtrue
    private boolean battleLose;         // バトルに負けた時にtrue
    private boolean escapeFlag;         // バトルから逃げた時にtrue
    private int bossKillCount;          // ボスを倒した数
    
    // コンストラクタ
    public Brave() {
        System.out.print("主人公の名前を入力してください。:");
        String name = new java.util.Scanner(System.in).nextLine();
        this.name = name;
        System.out.println("勇者" + this.name + "の冒険が幕を開けた…");

        this.level = 1;
        this.hp = 20;
        this.maxHp = 20;
        this.mp = 5;
        this.maxMp = 5;
        this.attack = 10;
        this.defense = 10;
        this.agility = 5;

        this.itemFormat = """
                　　　　　　やくそう：1
                　　　まりょくのみず：2
                """;

    }
    // メソッド
    public void chooseMap() {   // どのマップに行くかの選択
        Text.chooseMap();
        // 現在いるマップを選択したらもう一度マップアクションをやりなおさせたい、whileを追加
        // そのマップに最初に行く場合、そのマップのインスタンスを生成したい
        int number = new java.util.Scanner(System.in).nextInt();
        switch(number) {
            case 1:
                System.out.println(this.name + "は" + "森へむかった！");
                currentLocation().setThereIs(false);
                this.forestMap.setThereIs(true);
                break;
            case 2:
                System.out.println(this.name + "は" + "海へむかった！");
                currentLocation().setThereIs(false);
                this.seaMap.setThereIs(true);
                break;
            case 3:
                System.out.println(this.name + "は" + "山へむかった！");
                currentLocation().setThereIs(false);
                this.mountainMap.setThereIs(true);
                break;
        }
    }
    public void chooseMapAction() {   // マップにおいてどの行動をするか選ぶメソッド
        String str = """
                　　　　　てきをさがす：1
                　　　　　やどでやすむ：2
                　　　　アイテムをかう：3
                　　　　アイテムリスト：4
                　　ステータスかくにん：5
                　　ほかのマップへいく：6
                　　　　ボスとたたかう：7
                """;
        System.out.println("どのこうどうにする？");
        if (currentLocation().getEnemyKillCount() > 6) {
            str += "ひきょうをたんさくする：8";
            Text.chooseChangedText(str);
        } else {
            Text.chooseChangedText(str);
        }
        int number = new java.util.Scanner(System.in).nextInt();
        // 違った選択肢を選ばれたら繰り返したいのでwhileを追加する
        // というか、chooseMapActionはラスボス戦まで続くので
        // while (ラスボス戦フラグ == off) のような形？
        switch(number) {
            case 1:
                searchEnemy();      // 敵と戦う
                break;
            case 2:
                rest();             // 休んでHPとMP回復
                break;
            case 3:
                shopping();         // アイテム購入
                break;
            case 4:
                checkItemList();    // アイテムリスト確認
                break;
            case 5:
                checkStatus();      // ステータス確認
                break;
            case 6:
                chooseMap();        // 他のマップへ移動
                break;
            case 7:
                battleBoss();       // マップボス戦
                break;
            case 8:
                searchHikyou();     // 秘境探索(中ボス)
                break;
            default:
                break;              // 想定外の選択肢の場合、もう一度選ばせる
        }
    }
    public void searchEnemy() {     // 敵と戦う
        System.out.println(this.name + "はてきをみつけた！");
        battle(currentLocation().createEnemy());
    }
    public void rest() {            // 休んで体力と魔力を回復する
        this.hp = this.maxHp;
        this.mp = this.maxMp;
    }
    public void shopping() {        // アイテム購入
        System.out.println("なにをかいますか？");
        Text.chooseChangedText(this.itemFormat);
        int buyItem = new java.util.Scanner(System.in).nextInt();
    }
    public void checkItemList() {   // アイテムリスト確認

    }
    public void checkStatus() {     // ステータス確認
        String str = """
                なまえ：%s　レベル：%d
                HP：%d / %d　MP：%d / %d
                ちから：%d　まもり：%d
                すばやさ：%d
                """;
        System.out.printf(str,this.name,this.level,this.hp,this.maxHp,this.mp,this.maxMp,
                            this.attack,this.defense,this.agility);
    }

    public void battleBoss() {      // マップボス戦
        // ボスとのバトル
        // 勝ったら以下のようにボスキルカウントに+1する
        this.bossKillCount++;
        // ショップのアイテムを増やす処理を以下に記述
        addItemFormat();
    }
    public void searchHikyou() {    // 秘境探索(中ボス)
        // 秘境が解放されているかのチェック
    }
    public void battle(Enemy e) {   // 敵とエンカウントする
        System.out.println(e.getName() + "があらわれた！");
        this.turnCount = 0;

        while (!this.battleWin || !this.battleLose || !this.escapeFlag || !e.getEscapeFlag()) {
            // じゅもんやアイテム画面から「0」で戻った時、ここに戻りたい
            if (this.agility >= e.getAgility()) {   // 勇者が先攻の場合
                do {    // 勇者ターン
                    System.out.println(this.name + "はどうする？");
                    System.out.println("攻撃：１　呪文：２　防御：３　アイテム：４　逃げる：５");
                    System.out.print("\n\s->\s");
                    int action = new java.util.Scanner(System.in).nextInt();

                    switch (action) {
                        case 1:
                            attack(e);
                            break;
                        case 2:
                            spell(e);
                            break;
                        case 3:
                            defense();
                            break;
                        case 4:
                            useItem();
                            break;
                        case 5:
                            run();
                            continue;
                        default:
                            break;
                    }
                } while(this.turnCount < ( this.turnCount + 1 ));
                // 勇者ターン終了、敵HPチェック
                if (e.getHp() <= 0) {
                    win(e);
                    continue;
                }
                if (e.runJadgement(this.level)) {   // 敵の逃げ判定
                    e.run();
                    continue;
                } else {    // 敵ターン
                    int damage = e.turn(this.name,this.defense);
                    this.hp -= damage;
                }
                // 敵ターン終了、勇者HPチェック
                if (this.hp <= 0) {
                    die();
                    continue;
                }
            } else {    // 敵が先攻の場合
                if (e.runJadgement(this.level)) {
                    e.run();
                    continue;
                } else {    // 敵ターン
                    int damage = e.turn(this.name,this.defense);
                    this.hp -= damage;
                }
                // 敵ターン終了、勇者HPチェック
                if (this.hp <= 0) {
                    die();
                    continue;
                }
                do {    // 勇者ターン
                    System.out.println(this.name + "はどうする？");
                    System.out.println("攻撃：１　呪文：２　防御：３　アイテム：４　逃げる：５");
                    System.out.print("\n\s->\s");
                    int action = new java.util.Scanner(System.in).nextInt();

                    switch (action) {
                        case 1:
                            attack(e);
                            break;
                        case 2:
                            spell(e);
                            break;
                        case 3:
                            defense();
                            break;
                        case 4:
                            useItem();
                            break;
                        case 5:
                            run();
                            continue;
                        default:
                            break;
                    }
                } while(this.turnCount < ( this.turnCount + 1 ));
                // 勇者ターン終了、敵HPチェック
                if (e.getHp() <= 0) {
                    win(e);
                    continue;
                }
            }
        }
    }
    public void attack(Enemy e) {
        // ミス、通常攻撃、痛恨の一撃のどれが出るかをランダムに決定する
        int result = new java.util.Random().nextInt(100) + 1;

        if (1 <= result && result <= 10) {              // 1から10が出たらミス
            System.out.println("ミス！" + e.getName() + "はダメージをうけない！");
            this.turnCount += 1;
        } else if (95 <= result && result >= 100) {     // 95から100が出たら痛恨の一撃
            int damage = calculateDamage(e) * 2;
            System.out.println("かいしんのいちげき！");
            System.out.println(e.getName() + "に" + damage + "ポイントのダメージ！");
            e.setHp(e.getHp() - damage);
            this.turnCount += 1;
        } else {                                        // それ以外は通常攻撃
            int damage = calculateDamage(e);
            System.out.println(e.getName() + "に" + damage + "ポイントのダメージ！");
            e.setHp(e.getHp() - damage);
            this.turnCount += 1;
        }
    }
    public void spell(Enemy e) {    // 戦闘において呪文を使用するメソッド
        // まだ1つも呪文を習得していなかった場合、battleメソッドに戻す
        if (this.level < 3) {
            System.out.println("つかえるじゅもんがない！");
            return;
        }
        // 呪文一覧を表示して選択させる
        int point = 0;
        System.out.println("どのじゅもんをつかう？(0でもどる)");
        Text.chooseChangedText(spellFormat);
        int spellNumber = new java.util.Scanner(System.in).nextInt();
        switch(spellNumber) {
            case 1:
                point = HealSpell.pyoimi();                 // ピョイミ
                healSpell(point);
                break;
            case 2:
                point = HealSpell.bepyoimi(this.level);     // ベピョイミ
                healSpell(point);
                break;
            case 3:
                point = AttackSpell.myora(this.level);      // ミョラ
                attackSpell(point, e);
                break;
            case 4:
                point = AttackSpell.myorami(this.level);    // ミョラミ
                attackSpell(point, e);
                break;
            case 5:
                point = AttackSpell.myorazoma(this.level);  // ミョラゾマ
                attackSpell(point, e);
                break;
            default:
                break;
        }
    }
    public void defense() { // 戦闘において防御するメソッド
        int strongDefense = (int) (this.defense * 1.5);
        // この行動は素早さ関係なく勇者が先攻となる
        // そしてこのターン終了時には防御力を元に戻さなければならない
        this.turnCount += 1;
    }

    public void useItem() {    // 戦闘においてアイテムを使用するメソッド
        System.out.println("どのアイテムをつかう？");
        // ここでアイテム一覧を表示、0で戦う選択肢に戻るなど
        Text.chooseChangedText(itemFormat);
        int useItem = new java.util.Scanner(System.in).nextInt();
        if (useItem == 0) {
            return;
        }
        switch(useItem) {
            case 1:

        }
        this.turnCount += 1;
    }

    public void run() {     // 戦闘において逃げるメソッド
        // if (相手がボスの場合) 逃げられない
        // if (自分と相手のレベルと素早さの合計を比べてなんやかんや計算) → 逃げられるか無理かを決める
    }

    public void win(Enemy e) {  // 戦いに勝利
        this.battleWin = true;
        System.out.println(e.getName() + "をたおした！");
        System.out.println(e.getPoint() + "ポイントのけいけんちをかくとく！");
        checkLevelUp(e);
        checkSpellUp();
    }

    public void die() {         // 戦いに敗北
        this.battleLose = true;
        System.out.println(this.name + "はしんでしまった！");
    }

    public void checkLevelUp(Enemy e) { // レベルが上がっているかチェックする
        this.levelPoint += e.getPoint();
        List<Integer> levelList = createLevelList();
        int upLevel = 0;
        if (this.levelPoint >= levelList.get(this.levelIndex)) {
             do {
                this.level += 1;
                this.levelIndex += 1;
                upLevel += 1;
            } while (this.getLevelPoint() >= levelList.get(this.levelIndex));
            System.out.println(this.name + "のレベルが" + (this.level - upLevel) + "から" + 
                            this.level + "にあがった！");
        }
    }
    public void levelUp(int beforeLevel, int afterLevel) {
        // レベルアップによるステータス上昇の処理
    }
    public void checkSpellUp() {        // 呪文を習得できるかチェックする
        List<Integer> getSpellLevelList = createGetSpellLevelList();
        List<String> spellNameList = createSpellNameList();
        if (this.level >= getSpellLevelList.get(this.spellIndex)) {
            do {
                this.spellFormat += "\n" + spellNameList.get(this.spellIndex) + this.spellChoiceNumber;
                System.out.println(this.name + "は" + spellNameList.get(this.spellIndex) + 
                                    "のじゅもんがつかえるようになった！");
                this.spellIndex++;
                this.spellChoiceNumber++;
            } while (this.getLevel() >= getSpellLevelList.get(spellIndex));
        }
    }
    public List<Integer> createLevelList() {    // レベルアップに必要な経験値リストを作成して返す
        List<Integer> levelList = new ArrayList<Integer>();
        int levelCount = 1;
        int needLevelPoint = 4;

        while (levelCount < 21) {
            levelList.add(needLevelPoint);
            levelPoint = (int)( (needLevelPoint + levelCount ) * 1.25);
            levelCount++;
        }
        return java.util.Collections.unmodifiableList(levelList);
    }
    public List<Integer> createGetSpellLevelList() {  // 呪文習得レベルリストを作成する
        List<Integer> spellLevelList = new ArrayList<Integer>();
        spellLevelList.add(3);
        spellLevelList.add(5);
        spellLevelList.add(9);
        spellLevelList.add(14);
        spellLevelList.add(17);
        return java.util.Collections.unmodifiableList(spellLevelList);
    }
    public List<String> createSpellNameList() {    // 呪文名リストを作成する
        List<String> spellNameList = new ArrayList<String>();
        spellNameList.add("　　ピョイミ：");
        spellNameList.add("　　　ミョラ：");
        spellNameList.add("　ベピョイミ：");
        spellNameList.add("　　ミョラミ：");
        spellNameList.add("ミョラゾーマ：");
        return java.util.Collections.unmodifiableList(spellNameList);
    }
    public void addItemFormat() {
        List<String> shopItemList = createShopItemList();
        do {
            this.itemFormat += "\n" + shopItemList.get(this.itemIndex) + this.itemChoiceNumber;
            this.itemIndex++;
            this.itemChoiceNumber++;    
        } while(this.itemIndex % 2 == 0);
    }

    public List<String> createShopItemList() {      // ショップのアイテムリストを作成する
        List<String> shopItemList = new ArrayList<String>();
        shopItemList.add("　　　　かいふくやく：");
        shopItemList.add("　　まほうのせいすい：");
        shopItemList.add("　　　　せいめいそう：");
        shopItemList.add("いにしえのまどうしょ：");
        shopItemList.add("　だいちのしゅくふく：");
        shopItemList.add("　めがみのしゅくふく：");
        return java.util.Collections.unmodifiableList(shopItemList);
    }
    public int calculateDamage(Enemy e) {   // ダメージ値を計算して返す
        final int DEFAULT_RANGE = 1;
        int attackRange = (this.attack % 4) + DEFAULT_RANGE;    // 攻撃力が4増える毎にダメージ範囲を +1
        int braveAttack = new java.util.Random().nextInt(attackRange) + this.attack;
        int damage = braveAttack - e.getDefense();
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
    public void healSpell(int point) {              // 回復呪文で行う処理
        if (point == 0) {
            return;
        }
        this.hp += point;
        Text.healSpell(this.name, point);
        this.turnCount += 1;
    }
    public void attackSpell(int point, Enemy e) {   // 攻撃呪文で行う処理
        if (point == 0) {
            return;
        }
        e.setHp(e.getHp() - point);
        Text.attackSpell(e.getName(), point);
        this.turnCount += 1;
    }
    public Map currentLocation() {  // 現在地を返す
        if (this.forestMap.getThereIs()) {
            return this.forestMap;
        } else if(this.seaMap.getThereIs()) {
            return this.seaMap;
        } else {
            return this.mountainMap;
        }
    }
    public int countBossKill() {    // ボスを倒した数を取得
        int bossKillCount = 0;
        if (this.forestMap.getBossKill()) {
            bossKillCount++;
        }
        if (this.seaMap.getBossKill()) {
            bossKillCount++;
        }
        if (this.mountainMap.getBossKill()) {
            bossKillCount++;
        }
        return bossKillCount;
    }
    public Item createItem(String itemName) {
        switch(itemName) {
            case "yakusou":
                return new Herb();
        }
    }
    // アクセサ
    public String getName() { return this.name; }
    public int getLevel() { return this.level; }
    public int getLevelPoint() { return this.levelPoint; }
    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getMp() { return this.mp; }
    public int getMaxMp() { return this.maxMp; }
    public int getPower() { return this.power; }
    public int getProtect() { return this.protect; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getAgility() { return this.agility; }
    public int getTurnCount() { return this.turnCount; }
    public Sword getSword() { return this.sword; }
    public Helmet getHelmet() { return this.helmet; }
    public Armor getArmor() { return this.armor; }
    public Spell getSpell() { return this.spell; }

    public void setName(String name) { this.name = name; }
    public void setLevel(int level) { this.level = level; }
    public void setLevelPoint(int levelPoint) { this.levelPoint = levelPoint; }
    public void setHp(int hp) { this.hp = hp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }
    public void setMp(int mp) { this.mp = mp; }
    public void setMaxMp(int maxMp) { this.maxMp = maxMp; }
    public void setPower(int power) { this.power = power; }
    public void setProtect(int protect) { this.protect = protect; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setAgility(int agility) { this.agility = agility; }
    public void setTurnCount(int turnCount) { this.turnCount = turnCount; }
    public void setSword(Sword sword) { this.sword = sword; }
    public void setHelmet(Helmet helmet) { this.helmet = helmet; }
    public void setArmor(Armor armor) { this.armor = armor; }
    public void setSpell(Spell spell) { this.spell = spell; }
}