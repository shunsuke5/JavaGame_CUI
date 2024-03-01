package item.mpitem;

public class BlessingOfVenus extends MpItem {
    // コンストラクタ
    public BlessingOfVenus() {
        super("めがみのしゅくふく",
        "ははなるめがみからのしゅくふく。MPをすべてかいふくする。",
        0,0);
    }
    public int use(int braveMp, int braveMaxMp) {
        this.setHealPoint(braveMaxMp - braveMp);
        return getHealPoint();
    }
}
