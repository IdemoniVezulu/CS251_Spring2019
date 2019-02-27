import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = null;
        PrintStream out = null;
        try {
            in = new Scanner(new File(args[0]));
            out = new PrintStream(new File(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Construct HashTable or Heap here
        HashTable hashTable = new HashTable(2);
        Heap heap = new Heap();

        String firstLine = in.nextLine();

        switch (firstLine.charAt(0)){
            case '1': hashTable.generateOutput(2, in, out);
                    break;

            case '2': hashTable.generateOutput(2, in, out);
                    break;

            case '3': heap.generateOutput(in, out);
                    break;
        }

        in.close();
        out.close();
    }
}
