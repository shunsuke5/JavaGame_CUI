package battlechar.enemy.mountainenemy.boss;

import battlechar.enemy.mountainenemy.MountainEnemy;

public class MythologyGuard extends MountainEnemy {
    // コンストラクタ
    public MythologyGuard() {
        super("しんわのばんにん")
    }
    // メソッド
    public void turn(Brave brave) {
        switch (decideAction(2)) {
            case 0:
                attack(brave);
                break;
            case 1:
                reinforce();
                break;
        }
    }
    public void reinforce() {   // 自身を強化する
        System.out.println(getName() + "はふしぎなちからにつつまれた！");
        getBattleAttack().changedStatus(this, 1);
        getBattleAgility().changedStatus(this, 1);
    }
}
