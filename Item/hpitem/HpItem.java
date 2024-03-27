package item.hpitem;
import item.Item;
import battlechar.brave.*;

public class HpItem extends Item {
    // コンストラクタ
    public HpItem(String name) {
        super(name);
    }
    // メソッド
    public void use(Brave brave) {
        if (brave.getHp() == brave.getMaxHp()) {
            System.out.println("しかし" + brave.getName() + "のHPはまんたんだ！");
        } else {
            System.out.println(brave.getName() + "は" + this.getName() + "をつかった！");
            int healPoint = new java.util.Random().nextInt(getHealRange()) + getMinHealPoint();
            brave.healHp(brave, healPoint);
            System.out.println(brave.getName() + "のHPを" + healPoint + "ポイントかいふくした！");
            brave.plusTurnCount();
        }
    }
}