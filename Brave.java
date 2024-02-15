import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Spell.Spell;

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

    // プログラム用変数
    private int levelIndex = 0; // checkLevelUpメソッドでのみ使用、アクセサ不要
    private int spellIndex = 0; // checkSpellUpメソッドでのみ使用、アクセサ不要
    private String spellFormat;
    private int spellChoiceNumber = 1;
    
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
                
        }
    }
    public void defense() { // 戦闘において防御するメソッド

    }

    public void item() {    // 戦闘においてアイテムを使用するメソッド
        System.out.println("どのアイテムをつかう？");
        // ここでアイテム一覧を表示、0で戦う選択肢に戻るなど
    }

    public void run() {     // 戦闘において逃げるメソッド
        // if (自分と相手のレベルと素早さの合計を比べてなんやかんや計算) → 逃げられるか無理かを決める
        // if (相手がボスの場合) 逃げられない
    }

    public void chooseMapAction() {   // マップにおいてどの行動をするか選ぶメソッド
        // Eventクラスの定型文を呼び出して入力値を変数に格納し、switch文で分岐
        Event.chooseMapAction();
        int number = new java.util.Scanner(System.in).nextInt();
        switch(number) {
            case 1:
                this.searchEnemy();;
                this.battle();   // ここで渡す敵の引数をマップごとに変えるには？
                break;
            case 2:
                this.searchTreasure();
                break;
            case 3:
                this.rest();
                break;
            case 4:
                this.chooseMap();
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
                    item();
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
            checkLevelUp(e);
            checkSpellUp();
        }
    }
    public void checkLevelUp(Enemy e) { // レベルが上がっているかチェックする
        Event.killEnemy(e);
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
    public void searchEnemy() { // 敵を探す
        System.out.println(this.name + "はてきをみつけた！");
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
        System.out.printf(str,name,level,hp,maxHp,mp,maxMp,attack,defense,agility);
    }

    public void rest() {    // 休んで体力と魔力を回復する
        this.hp = this.maxHp;
        this.mp = this.maxMp;
    }

    public void chooseMap() {   // どのマップに行くかの選択
        System.out.println("どのマップに行きますか？");
        System.out.print("森：１　海：２　山：３");
    }

    public void die() {     // HPが0になると死ぬ

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