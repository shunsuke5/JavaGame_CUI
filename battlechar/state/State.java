package battlechar.state;

import battlechar.BattleChar;

public abstract class State {
    // 抽象メソッド
    public abstract void effect(BattleChar anyone);

    // メソッド
    public int returnRandomNum(int min, int range) {
        return new java.util.Random().nextInt(range) + min;
    }
}
