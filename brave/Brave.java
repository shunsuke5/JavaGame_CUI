package brave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import map.*;
import shop.itemshop.ItemShop;
import spell.*;
import spell.attackspell.*;
import spell.healspell.*;
import enemy.Enemy;
import equipment.*;
import text.Text;
import item.Item;
import item.hpitem.*;
import item.mpitem.*;
import itembag.ItemBag;

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
    private boolean isEscape;         // バトルから逃げた時にtrue
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

        // 装備の初期化もここで行いたい
        // へいしのけん、へいしのかぶと、へいしのよろい　などを装備させる

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
                searchEnemy();      // 敵と戦う
                break;
            case 2:
                rest();             // 休んでHPとMP回復
                break;
            case 3:
                shopping();         // アイテム購入
                break;
            case 4:
                checkItemBag();    // アイテムリスト確認
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
        Text.rest();
        int choose = new java.util.Scanner(System.in).nextInt();
        if (choose == 1) {
            this.money -= 20;
            this.hp = this.maxHp;
            this.mp = this.maxMp;
        } else {
            return;
        }
    }
    public void shopping() {        // アイテム購入
        ItemShop itemShop = new ItemShop();
        itemShop.sell(this);
    }
    public void checkItemBag() {   // アイテムリスト確認
        int itemId = 0;

        do {
            this.itemBag.displayItemBag();
            System.out.println("つかいたいアイテムはありますか？(-1でマップへ)");
            itemId = new java.util.Random().nextInt();
            isUseItem(itemId);
        } while(itemId != -1);
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
    }
    public void searchHikyou() {    // 秘境探索(中ボス)
        // 秘境が解放されているかのチェック
    }
    public void battle(Enemy e) {   // 敵とエンカウントする
        System.out.println(e.getName() + "があらわれた！");
        this.turnCount = 0;

        while (!this.winBattle || !this.loseBattle || !this.isEscape || !e.getIsEscape()) {
            // じゅもんやアイテム画面から「0」で戻った時、ここに戻りたい
            if (this.agility >= e.getAgility()) {               // 勇者が先攻の場合
                do {                                            // 勇者ターン
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
                            battleUseItem();
                            break;
                        case 5:
                            run();
                            continue;
                        default:
                            break;
                    }
                } while(this.turnCount == e.getTurnCount());
                if (e.getHp() <= 0) {                           // 勇者ターン終了、敵HPチェック
                    win(e);
                    continue;
                }
                if (e.isRun(this.level)) {               // 敵の逃げ判定
                    e.run();
                    continue;
                }
                e.turn(this);                                   // 敵ターン
                e.plusTurnCount();
                if (this.hp <= 0) {                             // 敵ターン終了、勇者HPチェック
                    die();
                    continue;
                }
            } else {                                            // 敵が先攻の場合
                if (e.isRun(this.level)) {
                    e.run();
                    continue;
                }
                e.turn(this);                                   // 敵ターン
                e.plusTurnCount();
                if (this.hp <= 0) {                             // 敵ターン終了、勇者HPチェック
                    die();
                    continue;
                }
                do {                                            // 勇者ターン
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
                            battleUseItem();
                            break;
                        case 5:
                            run();
                            continue;
                        default:
                            break;
                    }
                } while(this.turnCount != e.getTurnCount());
                if (e.getHp() <= 0) {                           // 勇者ターン終了、敵HPチェック
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
            this.turnCount++;
        } else if (95 <= result && result >= 100) {     // 95から100が出たら痛恨の一撃
            int damage = calculateDamage(e) * 2;
            System.out.println("かいしんのいちげき！");
            System.out.println(e.getName() + "に" + damage + "ポイントのダメージ！");
            e.setHp(e.getHp() - damage);
            this.turnCount++;
        } else {                                        // それ以外は通常攻撃
            int damage = calculateDamage(e);
            System.out.println(e.getName() + "に" + damage + "ポイントのダメージ！");
            e.setHp(e.getHp() - damage);
            this.turnCount++;
        }
    }
    public void spell(Enemy e) {    // 戦闘において呪文を使用するメソッド
        if (this.level < 3) {
            System.out.println("つかえるじゅもんがない！");
            return;
        }
        System.out.println("どのじゅもんをつかう？(-1でもどる)");
        displaySpell();
        int spellId = new java.util.Scanner(System.in).nextInt();
        useSpell(spellId).resite(this, e);
        this.turnCount++;
    }
    public void defense() { // 戦闘において防御するメソッド
        int strongDefense = (int) (this.defense * 1.5);
        // この行動は素早さ関係なく勇者が先攻となる
        // そしてこのターン終了時には防御力を元に戻さなければならない
        this.turnCount++;
    }

    public void battleUseItem() {    // 戦闘においてアイテムを使用するメソッド
        System.out.println("どのアイテムをつかう？(-1でもどる)");
        this.itemBag.displayItemBag();
        int itemId = new java.util.Scanner(System.in).nextInt();
        if (itemId == -1) {
            return;
        }
        if (isUseItem(itemId)) {
            this.turnCount++;
        } else {
            return;
        }
    }
    public boolean isUseItem(int itemId) {    // アイテムを使用するメソッド
        // アイテムを使用すればtrue、使用しなければfalseを返す
        if (this.itemBag.checkStorage(itemId) == 0) {
            return false;
        }
        this.itemBag.getItem()[itemId][0].use(this);
        return true;
    }
    public void run() {     // 戦闘において逃げるメソッド
        // if (相手がボスの場合) 逃げられない
        // if (自分と相手のレベルと素早さの合計を比べてなんやかんや計算) → 逃げられるか無理かを決める
    }

    public void win(Enemy enemy) {  // 戦いに勝利
        this.winBattle = true;
        System.out.println(enemy.getName() + "をたおした！");
        System.out.println(enemy.getPoint() + "ポイントのけいけんちをかくとく！");
        if (isLevelUp(enemy)) {
            upLevel();
            checkSpellUp(this.level);
        }
        this.money += enemy.getMoney();
        System.out.println(enemy.getMoney() + "マネーをてにいれた！");
    }

    public void die() {         // 戦いに敗北
        this.loseBattle = true;
        System.out.println(this.name + "はしんでしまった！");
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
                        System.out.println(this.name + "のレベルが" + beforeLevel + 
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
            this.defense = (int)afterArray[3];
            this.agility = (int)afterArray[4];
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
                    System.out.println(this.name + "は" + dataArray[0] + "のじゅもんがつかえるようになった！");
                    return;
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    public int calculateDamage(Enemy e) {   // ダメージ値を計算して返す
        final int DEFAULT_RANGE = 1;
        int attackRange = (this.attack % 4) + DEFAULT_RANGE;    // 攻撃力が4増える毎にダメージ範囲を +1
        int braveAttack = new java.util.Random().nextInt(attackRange) + this.attack;
        int damage = braveAttack - e.getDefense();
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
    public int getMoney() { return this.money; }
    public int getTurnCount() { return this.turnCount; }
    public Sword getSword() { return this.sword; }
    public Helmet getHelmet() { return this.helmet; }
    public Armor getArmor() { return this.armor; }
    public ItemBag getItemBag() { return this.itemBag; }
    public Spell getSpell() { return this.spell; }
    public int getBossKillCount() { return this.bossKillCount; }

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
    public void setMoney(int money) { this.money = money; }
    public void setTurnCount(int turnCount) { this.turnCount = turnCount; }
    public void setSword(Sword sword) { this.sword = sword; }
    public void setHelmet(Helmet helmet) { this.helmet = helmet; }
    public void setArmor(Armor armor) { this.armor = armor; }
    public void setSpell(Spell spell) { this.spell = spell; }
}