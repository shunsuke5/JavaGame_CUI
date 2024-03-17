import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.FilterOutputStream;

import item.Item;
import item.hpitem.*;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
        
        // 主人公を生み出す
        Brave b = new Brave();
        // 冒険の始まり
        Event.startEvent();
        // マップ選択
        Event.chooseMap();
        // マップ内アクション
        Event.chooseMapAction();
        // 全てのマップをクリアしたらラスボス戦
        Event.lastBattle();
        // ラスボスを倒せばゲームクリア
        Event.congratulations();
        
        */
        int[] a = returnLevelDataIndex();
        for (int i = 0; i < 5; i++) {
            System.out.println(a[i]);
        }
    }
    public static final int[] returnLevelDataIndex() {
        final int LEVEL = 0;
        final int NEED_LEVEL_POINT = 1;
        final int POWER = 2;
        final int DEFENSE = 3;
        final int AGILITY = 4;
        final int[] DATA_ARRAY_INDEX = {LEVEL,NEED_LEVEL_POINT,POWER,DEFENSE,AGILITY};
        return DATA_ARRAY_INDEX;
    }
}