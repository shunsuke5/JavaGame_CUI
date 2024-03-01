package item.hpitem;

public class BlessingOfGround extends HpItem {
    // コンストラクタ
    public BlessingOfGround() {
        super("だいちのしゅくふく",
        "おおいなるだいちからのしゅくふく。HPをすべてかいふくする。",
        0,0);
    }

    public int use(int braveHp, int braveMaxHp) {
        this.setHealPoint(braveMaxHp - braveHp);
        return getHealPoint();
    }
}
