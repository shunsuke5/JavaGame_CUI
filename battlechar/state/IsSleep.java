package battlechar.state;

import battlechar.BattleChar;

public class IsSleep extends State {
    private boolean isSleep;
    public boolean getIsSleep() { return this.isSleep; }
    public void setIsSleep(boolean isSleep) { this.isSleep = isSleep; }

    public void effect(BattleChar anyone) {
        
    }
}
