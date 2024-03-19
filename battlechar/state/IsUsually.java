package battlechar.state;

import battlechar.BattleChar;

public class IsUsually extends State {
    private boolean isUsually;

    public boolean getIsUsually() { return this.isUsually; }
    public void setIsUsually(boolean isUsually) { this.isUsually = isUsually; }

    public void effect(BattleChar anyone) {

    }
}
