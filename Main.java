import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        List<Item> itemBag = new ArrayList<>();
        Item yakusou = new Herb();
        itemBag.add(new Herb());
        System.out.println(itemBag.get(0));
        if (itemBag.contains(Herb)) {

        }
    }
}