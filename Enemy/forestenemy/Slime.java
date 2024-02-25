package enemy.forestenemy;
import enemy.Enemy;

public class Slime extends Enemy {
    // コンストラクタ
    public Slime() {
        setLevel(2);
        setHp(10);
        setMp(2);
        setAttack(2);
        setDefense(2);
        setAgility(1);
        setPoint(2);
        setMoney(1);
    }
    // メソッド
    public int turn(String braveName, int braveLevel, int braveDefense) {
        if(super.runJadgement(braveLevel)) {    // 敵が逃げる時、-1を返す
            return -1;
        }
        // ランダムで自分の行動を決め、ダメージを返す
    }
    public int 
}