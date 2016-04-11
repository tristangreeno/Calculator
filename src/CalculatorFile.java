import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tristangreeno on 4/11/16.
 */
public class CalculatorFile {

    private static ArrayList<String> calculationsDone = new ArrayList<>();

    public static void addToFile(double preCalcValue, double postCalcValue, String operation) throws IOException {

        File f = new File("src/CalcFile.txt");
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);

        String line = "The current value changed from " + preCalcValue + " to " + postCalcValue + " through " +
                operation + ".\n";
        calculationsDone.add(line);
    }

    public static void writeToFile() throws IOException{
        File f = new File("src/CalcFile.txt");
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String calculation : calculationsDone) {
            bw.append(calculation);
        }
        bw.close();
    }
}
