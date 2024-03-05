import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import item.Item;
import item.hpitem.*;

public class Main {
    public static void main(String[] args) {
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
        LinkedHashMap<String,Integer> menu = new LinkedHashMap<String,Integer>();
        menu.put("　　　　　　やくそう：",5);
        menu.put("　　　まりょくのみず：",5);
        menu.put("　　　　かいふくやく：",10);
        menu.put("　　まほうのせいすい：",10);
        menu.put("　　　　せいめいそう：",25);
        menu.put("いにしえのまどうしょ：",25);
        menu.put("　だいちのしゅくふく：",100);
        menu.put("　めがみのしゅくふく：",100);

        System.out.println("ショップへようこそ。");
        System.out.println("なにをかいますか？\n");
        int i = 1;
        for (String itemName : menu.keySet()) {
            System.out.println(itemName + menu.get(itemName) + "m" + "\s->\s" + i);
            i++;
        }
        

        // やくそう：5m　->　1
    }
}