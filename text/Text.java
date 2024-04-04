package text;

import battlechar.BattleChar;
import battlechar.brave.Brave;

public abstract class Text {
    private static final String ANSWER_ARROW = "\n\s->\s";

    public static void printAnswerArrow() {
        System.out.print(ANSWER_ARROW);
    }
    public static int inputChoice() {
        printAnswerArrow();
        return new java.util.Scanner(System.in).nextInt();
    }
    public static void chooseMap() {
        System.out.println("どのマップにいきますか？");
        System.out.println("森:1 海:2 山:3");
        System.out.println("つぎにいくのは…");
        System.out.print(ANSWER_ARROW);
    }
    public static void rest() {
        System.out.println("やどやでやすみますか？");
        System.out.println("ひとばん 20 マネーです。");
        System.out.println("やすむ：１　やめる：0");
        System.out.println(ANSWER_ARROW);
    }
    // マップアクションやショップなど、状況に応じて変化する選択肢を表示する時に用いるメソッド
    public static void chooseChangedText(String text) {
        System.out.println(text);
        System.out.print(ANSWER_ARROW);
    }
    public static void healSpell(String name, int healPoint) {      // 回復呪文使用時テキスト
        System.out.println(name + "のHPを" + healPoint + "ポイントかいふくした！");
    }
    public static void attackSpell(String name, int attackPoint) {  // 攻撃呪文使用時テキスト
        System.out.println(name + "に" + attackPoint + "ポイントのダメージ！");
    }
    public static void attack(String damagedChar, int damage) {     // 通常攻撃時など使用テキスト
        System.out.println(damagedChar + "に" + damage + "ポイントのダメージ！");
    }
    public static void selfAbnormalityState(BattleChar battleChar, String abnormalityState) {
        // 自らを状態異常にした時に表示するテキスト
        System.out.println(battleChar + "は\s" + abnormalityState + "\sじょうたいになった！");
    }
    public static void makeAbnormalityToEnemy(String enemy, String stateName) {
        // 勇者が敵を状態異常にしたときに呼び出すひな形
        System.out.println(enemy + "を\s" + stateName + "\sじょうたいにした！");
    }
    public static void makePoison(String poisonedChar) {
        if (poisonedChar.toString().equals("brave")) {
            System.out.println(poisonedChar + "はどくにおかされてしまった！");
        } else {
            makeAbnormalityToEnemy(poisonedChar, "どく");
        }
    }
    public static void makeCurse(String cursedChar) {
        if (cursedChar.toString().equals("brave")) {
            System.out.println(cursedChar + "はのろわれてしまった！");
        } else {
            makeAbnormalityToEnemy(cursedChar, "のろい");
        }
    }
    public static void makeSleep(String sleepingChar) {
        if (sleepingChar.toString().equals("brave")) {
            System.out.println(sleepingChar + "はねむってしまった！");
        } else {
            makeAbnormalityToEnemy(sleepingChar, "ねむり");
        }
    }
    public static void makeParalysis(String paralyzedChar) {
        if (paralyzedChar.toString().equals("brave")) {
            System.out.println(paralyzedChar + "はまひしてしまった！");
        } else {
           makeAbnormalityToEnemy(paralyzedChar, "まひ");
        }
    }
    public static void healAbnormalState(BattleChar anyone) {
        System.out.println(anyone.getName() + "の\s" + anyone.getState().getName() + "\sじょうたいがなおった！");
    }
    public static void chooseBattleAction(Brave brave) {
        System.out.printf("HP：%d / %d\nMP：%d / %d",
            brave.getHp(),brave.getMaxHp(),brave.getMp(),brave.getMaxMp());
        System.out.println(brave.getName() + "はどうする？");
        System.out.println("攻撃：１　呪文：２　防御：３　アイテム：４　逃げる：５");
        System.out.print("\n\s->\s");
    }
}
