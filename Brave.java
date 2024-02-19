import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import map.*;
import spell.*;

public class Brave {
    // 基礎ステータス
    private String name;    // 名前
    private int level;      // レベル
    private int levelPoint; // 経験値
    private int hp;         // 体力
    private int maxHp;      // 最大体力
    private int mp;         // 魔力
    private int maxMp;      // 最大魔力
    private int attack;     // 攻撃力
    private int defense;    // 防御力
    private int agility;    // すばやさ
    private int turnCount;  // 経過ターン数

    // 装備やアイテム
    private Sword sword;    // 剣
    private Helmet helmet;  // かぶと
    private Armor armor;    // よろい

    // 呪文
    private Spell spell;

    // 現在地
    private Map map;

    // ボスフラグ
    private int forestBossFlag = 0;
    private int seaBossFlag = 0;
    private int mountainBossFlag = 0;

    // プログラム用変数
    private int levelIndex = 0; // checkLevelUpメソッドでのみ使用、アクセサ不要
    private int spellIndex = 0; // checkSpellUpメソッドでのみ使用、アクセサ不要
    private String spellFormat;
    private int spellChoiceNumber = 1;
    private String mapAttribute;    // searchEnemyやsearchTreasureで使用するマップ属性
    
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
    }
    // メソッド
    public void chooseMap() {   // どのマップに行くかの選択
        System.out.println("どのマップにいきますか？");
        System.out.println("森:1 海:2 山:3");
        System.out.print("つぎにいくのは…->\s");
        // 現在いるマップを選択したらもう一度マップアクションをやりなおさせたい
        // そのマップに最初に行く場合、そのマップのインスタンスを生成したい
        int number = new java.util.Scanner(System.in).nextInt();
        switch(number) {
            case 1:
                System.out.println(this.name + "は" + "森へむかった！");
                Map forestMap = new Forest();
                this.map = forestMap;    // forestMapのスコープ的にthis.mapはメソッド終了後も大丈夫か？
                break;
            case 2:
                System.out.println(this.name + "は" + "海へむかった！");
                this.mapAttribute = "海";
                break;
            case 3:
                System.out.println(this.name + "は" + "山へむかった！");
                this.mapAttribute = "山";
                break;
        }
    }
    public void chooseMapAction() {   // マップにおいてどの行動をするか選ぶメソッド
        String str = """
                　　　てきをさがす：1
                　おたからをさがす：2
                　　　　　　やすむ：3
                ほかのマップへいく：4
                """;
        System.out.println("どのこうどうにする？");
        System.out.println("""
                　　　てきをさがす：1
                　おたからをさがす：2
                　　　　　　やすむ：3
                ほかのマップへいく：4

                ->\s
                """);
        int number = new java.util.Scanner(System.in).nextInt();
        switch(number) {
            case 1:
                this.searchEnemy();;    // 敵と戦う
                break;
            case 2:
                this.searchTreasure();  // お宝を探す
                break;
            case 3:
                this.rest();    // 休んでHPとMP回復
                break;
            case 4:
                this.chooseMap();   // 他のマップへ移動
            case 5:
                this.battleBoss();  // マップボス戦
        }
    }
    public void searchEnemy() { // 敵を探す
        System.out.println(this.name + "はてきをみつけた！");

        // Enemy enemy = this.map.createEnemy();
        
        // if (this.map.name.equals("森"))
        if (this.mapAttribute.equals("森")) {     // マップ属性が"森"の場合
            // Enemy enemy = Forest.createEnemy();
            // battle(enemy);
            int enemyNumber = new java.util.Random().nextInt(3);
            switch(enemyNumber) {
                case 0:
                    // this.map.enemy = new Slime();
                    // battle(this.map.enemy);
                    Slime slime = new Slime();
                    battle(slime);
                    this.forestBossFlag += 1;
                    break;
                case 1:
                    // battle()に渡す敵インスタンス「e」に"ゴブリン"を格納
                    // battle()メソッドへ
                    this.forestBossFlag += 1;
                    break;
                case 2:
                    // battle()に渡す敵インスタンス「e」に"さんぞく"を格納
                    // battle()メソッドへ
                    this.forestBossFlag += 1;
                    break;
            }
        } else if (this.mapAttribute.equals("海")) {    // マップ属性が"海"の場合
            int enemyNumber = new java.util.Random().nextInt(4);
            switch(enemyNumber) {
                case 0:
                    // battle()に渡す敵インスタンス「e」に"スライム"を格納
                    // battle()メソッドへ
                    break;
                case 1:
                    // battle()に渡す敵インスタンス「e」に"ゴブリン"を格納
                    // battle()メソッドへ
                    break;
                case 2:
                    // battle()に渡す敵インスタンス「e」に"さんぞく"を格納
                    // battle()メソッドへ
                    break;
                case 3:
                    // battle()に渡す敵インスタンス「e」に"さんぞく"を格納
                    // battle()メソッドへ
            }
        } else {    // マップ属性が"山"の場合
            int enemyNumber = new java.util.Random().nextInt(6);
            switch(enemyNumber) {
                case 0:
                    // battle()に渡す敵インスタンス「e」に"スライム"を格納
                    // battle()メソッドへ
                    break;
                case 1:
                    // battle()に渡す敵インスタンス「e」に"ゴブリン"を格納
                    // battle()メソッドへ
                    break;
                case 2:
                    // battle()に渡す敵インスタンス「e」に"さんぞく"を格納
                    // battle()メソッドへ
                    break;
                case 3:
                    // battle()に渡す敵インスタンス「e」に"さんぞく"を格納
                    // battle()メソッドへ
            }
        }
    }
    public void battle(Enemy e) { // 敵とエンカウントする
        System.out.println(e.getName() + "があらわれた！");     // この文は最初だけ表示する

        while (this.hp <= 0 || e.getHp() <= 0) {
            System.out.println(this.name + "はどうする？");
            System.out.print("攻撃：１　呪文：２　防御：３　アイテム：４　逃げる：５　-> ");
            int action = new java.util.Scanner(System.in).nextInt();

            // ここに主人公と敵の素早さを比較してどちらが先制かを決めるプログラムを記述

            switch (action) {
                case 1:
                    attack(e);
                    break;
                case 2:
                    spell();
                    break;
                case 3:
                    defense();
                    break;
                case 4:
                    useItem();
                    break;
                case 5:
                    run();
                    break;
                default:
                    break;
            }
        }
        if (this.hp <= 0) {
            this.die();
        } else {
            System.out.println(e.getName() + "をたおした！");
            System.out.println(e.getPoint() + "ポイントのけいけんちをかくとく！");
            checkLevelUp(e);
            checkSpellUp();
        }
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
        }
        System.out.println(this.name + "のレベルが" + (this.level - upLevel) + "から" + 
                            this.level + "にあがった！");
    }
    public void checkSpellUp() {        // 呪文を習得できるかチェックする
        List<Integer> getSpellLevelList = createGetSpellLevelList();
        List<String> spellNameList = createSpellNameList();
        if (this.level >= getSpellLevelList.get(this.spellIndex)) {
            do {
                this.spellFormat += "\n" + spellNameList.get(this.spellIndex) + "：" + this.spellChoiceNumber;
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
    public List<Integer> createGetSpellLevelList() {  // 呪文習得レベルリストを作成して返す
        List<Integer> spellLevelList = new ArrayList<Integer>();
        spellLevelList.add(3);
        spellLevelList.add(5);
        spellLevelList.add(9);
        spellLevelList.add(14);
        spellLevelList.add(17);
        return java.util.Collections.unmodifiableList(spellLevelList);
    }
    public List<String> createSpellNameList() {    // 呪文名リストを作成して返す
        List<String> spellNameList = new ArrayList<String>();
        spellNameList.add("ホイミ");
        spellNameList.add("メラ");
        spellNameList.add("ベホイミ");
        spellNameList.add("メラミ");
        spellNameList.add("メラゾーマ");
        return java.util.Collections.unmodifiableList(spellNameList);
    }

    public void searchTreasure() {  // お宝を探す

    }

    public void checkStatus() {     // ステータス確認
        String str = """
                名前：%s    レベル：%d
                HP：%d / %d
                MP：%d / %d
                攻撃力：%d
                防御力：%d
                すばやさ：%d
                """;
        System.out.printf(str,this.name,this.level,this.hp,this.maxHp,this.mp,this.maxMp,
                            this.attack,this.defense,this.agility);
    }

    public void rest() {    // 休んで体力と魔力を回復する
        this.hp = this.maxHp;
        this.mp = this.maxMp;
    }

    public void battleBoss() {
        if (ボスフラグ == off) {
            return;
        } else if (ボスフラグ == on) {
            chooseMapActionで表示する文字列変数 += "ボスとたたかう：5"
        }
    }
    public void attack(Enemy e) {   // 戦闘において攻撃するメソッド
        System.out.println(this.name + "のこうげき！");
        int damage = e.getDefense() - this.attack;
        e.setHp(e.getHp() - damage);
        System.out.println(e.getName() + "に" + damage + "ポイントのダメージ！");
    }

    public void spell(Enemy e, Spell s) {    // 戦闘において呪文を使用するメソッド
        // まだ1つも呪文を習得していなかった場合、battleメソッドに戻す
        if (this.level < 3) {
            System.out.println("つかえるじゅもんがない！");
            return;
        }
        // 呪文一覧を表示して選択させる
        System.out.println("どのじゅもんをつかう？(0でたたかいのせんたくしにもどる)");
        System.out.println(this.spellFormat);
        int spellNumber = new java.util.Scanner(System.in).nextInt();
        switch(spellNumber) {
            case 1:
                HealSpell.hoimi();
        }
    }
    public void defense() { // 戦闘において防御するメソッド

    }

    public void useItem() {    // 戦闘においてアイテムを使用するメソッド
        System.out.println("どのアイテムをつかう？");
        // ここでアイテム一覧を表示、0で戦う選択肢に戻るなど
    }

    public void run() {     // 戦闘において逃げるメソッド
        // if (相手がボスの場合) 逃げられない
        // if (自分と相手のレベルと素早さの合計を比べてなんやかんや計算) → 逃げられるか無理かを決める
    }

    public void die() {     // HPが0になると死ぬ
        System.out.println(this.name + "はしんでしまった！");
    }
    
    // アクセサ
    public String getName() { return this.name; }
    public int getLevel() { return this.level; }
    public int getLevelPoint() { return this.levelPoint; }
    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getMp() { return this.mp; }
    public int getMaxMp() { return this.maxMp; }
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
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setAgility(int agility) { this.agility = agility; }
    public void setTurnCount(int turnCount) { this.turnCount = turnCount; }
    public void setSword(Sword sword) { this.sword = sword; }
    public void setHelmet(Helmet helmet) { this.helmet = helmet; }
    public void setArmor(Armor armor) { this.armor = armor; }
    public void setSpell(Spell spell) { this.spell = spell; }
}