package brave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import map.*;
import spell.*;
import enemy.Enemy;
import equipment.*;
import text.Text;

public class Brave {
    // 基礎ステータス
    private String name;    // 名前
    private int level;      // レベル
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
        // 現在いるマップを選択したらもう一度マップアクションをやりなおさせたい、whileを追加
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
                Map seaMap = new Sea();
                this.map = seaMap;
                break;
            case 3:
                System.out.println(this.name + "は" + "山へむかった！");
                Map mountainMap = new Mountain();
                this.map = mountainMap;
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
        if (this.map.getBossFlag()) {
            str += "　　ボスとたたかう：5";
            System.out.print(str + "\n\n\s->\s");
        } else {
            System.out.println(str);
            System.out.print(str + "\n\n\s->\s");
        }
        int number = new java.util.Scanner(System.in).nextInt();
        // 違った選択肢を選ばれたら繰り返したいのでwhileを追加する
        // というか、chooseMapActionはラスボス戦まで続くので
        // while (ラスボス戦フラグ == off) のような形？
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
        Enemy enemy = this.map.createEnemy();
        battle(enemy);
    }
    
    public void battle(Enemy e) { // 敵とエンカウントする
        System.out.println(e.getName() + "があらわれた！");     // この文は最初だけ表示する

        while (this.hp <= 0 || e.getHp() <= 0) {
            System.out.println(this.name + "はどうする？");
            System.out.print("攻撃：１　呪文：２　防御：３　アイテム：４　逃げる：５　-> ");
            int action = new java.util.Scanner(System.in).nextInt();

            // ここに主人公と敵の素早さを比較してどちらが先制かを決めるプログラムを記述
            if (this.agility >= e.getAgility()) {
                switch (action) {
                    case 1:
                        attack(e);
                        // ここに敵のターンを入れるにはどうすればよい？
                        // int damage = e.turn();
                        // if (damage > 0) {
                                // this.hp -= damage;
                        // }
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
                        break;
                    default:
                        break;
                }
                // ここがターン内の区切りなので、ここにHPチェックを挟んで0なら残っているターンをスキップ
                // int damage = e.turn();   勇者の方が早い場合、敵は後に行動
                // this.hp -= damage;
            } else {
                // int damage = e.turn();   敵の方が早い場合、敵は先に行動
                // this.hp -= damage;
                // ここがターン内の区切りなので、ここにHPチェックを挟んで0なら残っているターンをスキップ
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
                        break;
                    default:
                        break;
                }
            }
        }
        if (this.hp <= 0) {
            this.die();
        } else {
            System.out.println(e.getName() + "をたおした！");
            System.out.println(e.getPoint() + "ポイントのけいけんちをかくとく！");
            checkLevelUp(e);
            checkSpellUp();
            this.map.setEnemyKillCount(this.map.getEnemyKillCount() + 1);
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
        spellNameList.add("ピョイミ");
        spellNameList.add("ミョラ");
        spellNameList.add("ベピョイミ");
        spellNameList.add("ミョラミ");
        spellNameList.add("ミョラゾーマ");
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

    public void spell(Enemy e) {    // 戦闘において呪文を使用するメソッド
        // まだ1つも呪文を習得していなかった場合、battleメソッドに戻す
        if (this.level < 3) {
            System.out.println("つかえるじゅもんがない！");
            return;
        }
        // 呪文一覧を表示して選択させる
        int point = 0;
        System.out.println("どのじゅもんをつかう？(0でたたかいのせんたくしにもどる)");
        System.out.println(this.spellFormat);
        System.out.println("\n->\s");
        int spellNumber = new java.util.Scanner(System.in).nextInt();
        switch(spellNumber) {
            case 1:
                point = HealSpell.pyoimi();
                this.hp += point;
                Text.healSpell(this.name, point);
                break;
            case 2:
                // レベルが満たない場合に0が返ってきたときの判定メソッドをここに置く
                /*
                 * if (point == 0) {
                 *      return;
                 * }
                 */
                point = HealSpell.pyoimi();
                this.hp += point;
                Text.healSpell(this.name, point);
                break;
            case 3:
                this.hp += HealSpell.bepyoimi(this.level);
                break;
            case 4:
                e.setHp(e.getHp() - AttackSpell.myorami(this.level));
                break;
            case 5:
                e.setHp(e.getHp() - AttackSpell.myorazoma(this.level));
                break;
            default:
                break;
        }
    }
    public void spellReturn() {
        /*
         * switch(spellNumber) {
            case 1:
                return HealSpell.pyoimi();
            case 2:
                return AttackSpell.myora(this.level);
            case 3:
                return HealSpell.bepyoimi(this.level);
            case 4:
                return AttackSpell.myorami(this.level);
            case 5:
                return AttackSpell.myorazoma(this.level);
            default:
                return 0;
        }
        回復なら正の数、攻撃なら負の数として
        まずは正の数か負の数を判定するプログラムを置き、
        そのあとに仮に負の値(攻撃呪文)ならばそれを絶対値として正の数にしてreturnさせたい
        switch文はこの記述で行きたいのでその判定を置くとするならおおもとのspellメソッドの方か？
        そして0が返ってきたら(呪文で0が返ってくることはありえないことを想定しているので)
        じゅもんのせんたくしから一旦戦いの選択肢に戻るようにする
         */
    }
    public void defense() { // 戦闘において防御するメソッド
        int defaultDefense = getDefense();
        int strongDefense = (int) (getDefense() * 1.5);
        // この行動は素早さ関係なく勇者が先攻となる
        // そしてこのターン終了時には防御力を元に戻さなければならない
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