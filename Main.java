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
        
        // メンバクラスの呼び出し方
        // 外部クラスのインスタンスを生成
        Inner in = new Inner();
        // 外部クラス内のメンバクラスを生成する
        Inner.Inclass inClass = in.new Inclass();
        inClass.methodA();
    }
}