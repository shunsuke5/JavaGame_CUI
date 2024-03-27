package item.stateitem;
import battlechar.brave.Brave;
import item.Item;

public class StateItem extends Item {
    // コンストラクタ
    public StateItem(String name) {
        super("name");
    }
    public void use(Brave brave) {
        if (isRightItem(brave)) {
            System.out.println(brave.getName() + "は" + this.getName() + "をつかった！");
            System.out.println(brave.getName() + "の\s" + brave.getState().getName() +
                                "\sじょうたいがなおった！");
            brave.setAbnormalTurnPeriod(brave.getTurnCount() + 1);
            brave.plusTurnCount();
        } else {
            System.out.println("いまはこのアイテムはこうかがないようだ…");
        }
    }
    public String knowBraveStatus(Brave brave) {
        return brave.getState().getName();
    }
    public boolean isRightItem(Brave brave) {
        return this.getTargetAbnormal().equals(knowBraveStatus(brave));
    }
}
