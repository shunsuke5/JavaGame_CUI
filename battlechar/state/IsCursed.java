package battlechar.state;

import battlechar.BattleChar;

public class IsCursed extends State {
    private boolean isCursed;
    public boolean getIsCursed() { return this.isCursed; }
    public void setIsCursed(boolean isCursed) { this.isCursed = isCursed; }

    public void effect(BattleChar anyone) {

    }
}
