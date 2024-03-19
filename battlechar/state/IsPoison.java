package battlechar.state;

import battlechar.BattleChar;

public class IsPoison extends State {
    private boolean isPoison;
    public boolean getIsPoison() { return this.isPoison; }
    public void setIsPoison(boolean isPoison) { this.isPoison = isPoison; }

    public void effect(BattleChar anyone) {
        int damage = returnRandomNum(3, 4);
        anyone.setHp(anyone.getHp() - damage);
    }
}
