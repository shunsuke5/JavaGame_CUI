import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.WeakHashMap;
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
        
        WeakHashMap<Integer,Integer> a = new WeakHashMap<>();
        a.put(0, 3);
        a.put(1, 2);
        System.out.println(a.get(0));

        if (a.containsKey(0)) {
            a.put(0, a.get(0) + 5);
        }

        System.out.println(a.get(0));
    }
}