package battlechar.enemy.seaenemy.boss;

import battlechar.brave.Brave;
import battlechar.enemy.seaenemy.SeaEnemy;
import spell.attackspell.BigWota;
import spell.healspell.Pyoimi;
import text.Text;

public class MythicalWaterDragon extends SeaEnemy {
    // コンストラクタ
    public MythicalWaterDragon() {
        super("まぼろしのすいりゅう");
    }
    // メソッド
    public void turn(Brave brave) {
        switch(decideAction(4)) {
            case 0:
                attack(brave);
                break;
            case 1:
                new Pyoimi().resite(this, this);
                break;
            case 2:
                new BigWota().resite(this, brave);
                break;
            case 3:
                iceBreath(brave);
                break;
        }
    }
    public void iceBreath(Brave brave) {    // 強い攻撃かつすばやさを1ダウン
        System.out.println(getName() + "はこおりのいきをはいてきた！");
        int damage = ((int)calculateDamage(getAbnormalTurnPeriod()) * 1.5)
        Text.attack(brave.getName(), damage);
        brave.getBattleAgility().changedStatus(brave, -1);
    }
}
