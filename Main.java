import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LinkedHashMap;
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
        
        if(a()) {
            System.out.println("a()でtrueが返されました");
        } else {
            System.out.println("a()でfalseが返されました");
        }
    }
    public static boolean a() {
        System.out.print("数字を入力してください：");
        int num = new java.util.Scanner(System.in).nextInt();
        if (num == 0) {
            return true;
        } else {
            return false;
        }
    }
}