package state;

import battlechar.BattleChar;

public class IsUsually extends State {  // 通常状態のため、何も起こらない
    // コンストラクタ
    public IsUsually() {
        super("つうじょう");
    }

    public void effect(BattleChar anyone) {}
}
