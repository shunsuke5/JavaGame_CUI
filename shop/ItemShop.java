package shop;
import text.Text;
import item.Item;
import item.hpitem.Herb;

public class ItemShop extends Shop {
    // コンストラクタ
    public ItemShop() {
        Item herb = new Herb();
        setMenu(herb,5);
        setMenu("　　　まりょくのみず：",5);
        setMenu("　　　　かいふくやく：",10);
        setMenu("　　まほうのせいすい：",10);
        setMenu("　　　　せいめいそう：",25);
        setMenu("いにしえのまどうしょ：",25);
        setMenu("　だいちのしゅくふく：",100);
        setMenu("　めがみのしゅくふく：",100);
    }
    // メソッド
    public void sell(int bossKillCount) {
        System.out.println("ショップへようこそ。");
        System.out.println("なにをかいますか？\n");
        int i = 1;
        for (String itemName : getMenu().keySet()) {
            System.out.println(itemName + getMenu().get(itemName) + "m" + "\s->\s" + i);
            i++;
        }
        Text.printAnswerArrow();
        int number = new java.util.Scanner(System.in).nextInt();

        switch(number) {
            case 1:
        }
    }
}
