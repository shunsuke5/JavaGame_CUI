package shop;
import text.Text;
import item.Item;
import item.hpitem.BlessingOfGround;
import item.hpitem.Herb;
import item.hpitem.LifeHerb;
import item.hpitem.MedicineLiquid;
import item.mpitem.AncientMagicBook;
import item.mpitem.BlessingOfVenus;
import item.mpitem.MagicHolyWater;
import item.mpitem.MagicWater;

public class ItemShop extends Shop {
    // フィールド
    private boolean leaveFlag;
    // コンストラクタ
    public ItemShop() {
        setMenu("　　　　　　やくそう：",5);
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

        // ここに最初のwhileを配置したい
        while (!leaveFlag) {
            System.out.println("なにをかいますか？(0でもどる)\n");
            displayMenu();
            int choice = Text.inputChoice();
            if (choice == 0) {  // 0を選んだ場合マップアクションへ
                this.leaveFlag = true;
                continue;
            }
            System.out.println("いくつかいますか？(0でこうにゅうがめんにもどる)");
            int count = Text.inputChoice();
            if (count == 0) {   // 0を選んだ場合ショップのアイテム一覧へ
                continue;
            }
        }
        
        
        // 何を買うかのwhile、0が選ばれたらマップアクションへ(return)
        // 「ほかにもなにかかいますか？」で「いいえ」がえらばれてもマップアクションへ(return)
        // 「(choice)番に対応したアイテムを(count)個生成し、返す」　といった処理にしたい
        // だが二重switchにはしたくない
        
        if (choice == 0) {
            return;
        }
        

        switch(number) {
            case 1:
                return new Herb();
            case 2:
                return new MagicWater();
            case 3:
                return new MedicineLiquid();
            case 4:
                return new MagicHolyWater();
            case 5:
                return new LifeHerb();
            case 6:
                return new AncientMagicBook();
            case 7:
                return new BlessingOfGround();
            case 8:
                return new BlessingOfVenus();
        }
    }
    public void displayMenu() {
        int i = 1;
        for (String itemName : getMenu().keySet()) {
            System.out.println(itemName + getMenu().get(itemName) + "m" + "\s->\s" + i);
            i++;
        }
    }
    public Item giveItem() {
        
    }
}
