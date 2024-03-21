package text;

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
    public static void isPoison(String poisonedChar) {
        System.out.println(poisonedChar + "はどくにおかされた！");
    }
    public static void isCursed(String cursingChar, String cursedChar) {
        System.out.println(cursingChar + "は" + cursedChar + "をのろった！");
    }
    public static void isSleep(String sleepingChar) {
        System.out.println(sleepingChar + "はねてしまった！");
    }
    public static void isParalysis(String paralysisChar) {
        System.err.println(paralysisChar + "はまひしてしまった！");
    }
}
