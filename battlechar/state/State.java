package battlechar.state;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import battlechar.BattleChar;

public abstract class State {
    private String stateName;
    private int stateId;
    private String stateDetail;
    // コンストラクタ
    public State(String stateName) {
        this.stateName = stateName;
        try {
            BufferedReader br = new BufferedReader(new FileReader("State_data.csv"));
            String data = br.readLine();
            while(data != null) {
                if (data.contains(this.stateName)) {
                    Object[] dataArray = data.split(",");
                    this.stateId = (int)dataArray[1];
                    this.stateDetail = (String)dataArray[2];
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
    // 抽象メソッド
    public abstract void effect(BattleChar anyone);

    // メソッド
    public int returnRandomNum(int min, int range) {
        return new java.util.Random().nextInt(range) + min;
    }
    // アクセサ
    public String getStateName() { return this.stateName; }
    public int getStateId() { return this.stateId; }
    public String getStateDetail() { return this.stateDetail; }
}
