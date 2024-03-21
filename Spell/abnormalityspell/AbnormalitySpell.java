package spell.abnormalityspell;
import spell.Spell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import battlechar.BattleChar;
import battlechar.state.IsCursed;
import battlechar.state.IsParalysis;
import battlechar.state.IsPoison;
import battlechar.state.IsSleep;
import battlechar.state.State;

public class AbnormalitySpell extends Spell {
    // コンストラクタ
    public AbnormalitySpell(String name) {
        super("name");
    }
    // メソッド
    public void resite(BattleChar user, BattleChar receiver, State state) {
        if (user.getMp() < getConsumptionMp()) {
            System.out.println("MPがたりない！");
            return;
        }
        System.out.println(user.getName() + "は" + getName() + "をとなえた！");

    }
    public boolean isSuccessGiveAbnormality(int probability) {  // 状態異常を相手に付与する時にtrue
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
                    return (String)dataArray[6];
                }
                br.readLine();
            }
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return null;
        }
    }
    public State returnGiveAbnormality(String abnormalityName) {
        switch(abnormalityName) {
            case "どく":
                return new IsPoison();
            case "まひ":
                return new IsParalysis();
            case "ねむり":
                return new IsSleep();
            case "のろい":
                return new IsCursed();
        }
        return null;
    }
}
