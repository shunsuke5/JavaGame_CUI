package battlechar.state;

import battlechar.BattleChar;

public class IsParalysis extends State {
    private boolean isParalysis;
    public boolean getIsParalysis() { return this.isParalysis; }
    public void setIsParalysis(boolean isParalysis) { this.isParalysis = isParalysis; }

    public void effect(BattleChar anyone) {

    }
}
