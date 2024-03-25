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
        B b = new B();
        A aa = new AA();
        b.setA(aa);
        System.out.println(b.getA().getStr());

        b.setA(null);
        System.out.println(b.getA().getStr());
    }
}