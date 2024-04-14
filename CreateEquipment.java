import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateEquipment {
    public String equipmentName;
    public String equipmentClassName;

    public void print() {
        System.out.println("case \"" + equipmentName +"\":\n\t" + 
        "return new " + equipmentClassName + "()");
    }
    public void printToFile() {
        try {
            FileWriter fr = new FileWriter("test.txt");
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String data = br.readLine();
            while(data != null) {
                String[] dataArray = data.split(",");
                this.equipmentName = dataArray[0];
                this.equipmentClassName = dataArray[1];
                fr.write("case \"" + equipmentName +"\":\n\t" + 
                            "return new " + equipmentClassName + "();\n");
                data = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}
