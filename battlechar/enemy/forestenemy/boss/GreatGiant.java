package battlechar.enemy.forestenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.forestenemy.ForestEnemy;
import spell.healspell.Pyoimi;
import text.Text;

public class GreatGiant extends ForestEnemy{
    public GreatGiant() {
        super("おおいなるきょじん");
    }
    // メソッド
    public void turn(Brave brave) {     // ランダムで自分の行動を決める
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                new Pyoimi().resite(this, brave);;
                break;
            case 2:
                stamp(brave);
                break;
        }
    }
    public void stamp(Brave brave) {
        System.out.println(getName() + "はおおきなあしでふみつぶしてきた！");
        int damage = (int)(calculateDamage(brave.getBattleDefense()) * 1.5);
        Text.attack(brave.getName(), damage);
        brave.damaged(damage);
        int defenseDown = returnRandomNum(1, 100);
        if (defenseDown < 20) {
            System.out.println(brave.getName() + "のぼうぎょりょくがすこしさがってしまった！");
            brave.downBattleDefense(0.75);
        }
    }
}
