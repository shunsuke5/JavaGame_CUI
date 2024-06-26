package spell.abnormalityspell;
import spell.Spell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import battlechar.BattleChar;
import state.*;
import text.Text;

public class AbnormalitySpell extends Spell {
    // コンストラクタ
    public AbnormalitySpell(String name) {
        super("name");
    }
    // メソッド
    public void resite(BattleChar user, BattleChar receiver, State state, int probability) {
        if (user.getMp() < getConsumptionMp()) {
            if (user.toString().equals("Brave")) {
                System.out.println("MPがたりない！");
            } else {
                System.out.println("しかしMPがたりなかった！");
            }
            return;
        }
        System.out.println(user.getName() + "は" + getName() + "をとなえた！");
        if (isSuccessGiveAbnormality(probability)) {
            switchAbnormalityText(receiver, state.getName());
            receiver.setState(state);
        } else {
            System.out.println("ミス！じゅもんはあたらなかった！");
        }
    }
    protected boolean isSuccessGiveAbnormality(int probability) {  // 状態異常を相手に付与する時にtrue
        int result = new java.util.Random().nextInt(100);
        return 0 < result && result < probability;
    }
    public String returnAbnormalityName(String spellName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("..\\Spell_data.txt"));
            String data = br.readLine();
            while(data != null) {
                if (data.contains(spellName)) {
                    Object[] dataArray = data.split(",");
                    br.close();
                    return (String)(dataArray[6]);
                }
                data = br.readLine();
            }
            br.close();
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return null;
        }
    }
    public State returnGiveAbnormality(String abnormalityName, BattleChar anyone) {
        switch(abnormalityName) {
            case "どく":
                return new IsPoison(anyone);
            case "まひ":
                return new IsParalysis(anyone);
            case "ねむり":
                return new IsSleep(anyone);
            case "のろい":
                return new IsCursed(anyone);
        }
        return null;
    }
    public void switchAbnormalityText(BattleChar receiver, String stateName) {
        switch(stateName) {
            case "どく":
                Text.makePoison(receiver.getName());
                break;
            case "まひ":
                Text.makeParalysis(receiver.getName());
                break;
            case "のろい":
                Text.makeCurse(receiver.getName());
                break;
            case "ねむり":
                Text.makeSleep(receiver.getName());
                break;
                
        }
    }
    public void resite(BattleChar user, BattleChar receiver) {}
}
