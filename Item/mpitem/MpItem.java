package item.mpitem;
import battlechar.brave.Brave;
import item.Item;

public class MpItem extends Item {
    // コンストラクタ
    public MpItem(String name) {
        super(name);
    }
    // メソッド
    public void use(Brave brave) {
        if (brave.getMp() == brave.getMaxMp()) {
            System.out.println("しかし" + brave.getName() + "のMPはまんたんだ！");
        } else {
            System.out.println(brave.getName() + "は" + this.getName() + "をつかった！");
            int healPoint = new java.util.Random().nextInt(getHealRange()) + getMinHealPoint();
            brave.healMp(brave, healPoint);
            System.out.println(brave.getName() + "のMPを" + healPoint + "ポイントかいふくした！");
            brave.plusTurnCount();
        }
    }
}
