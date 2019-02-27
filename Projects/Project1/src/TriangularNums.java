
/**
 * Read a file that contains 2 lines:
 * <p>
 * <ol>
 *     <li>There are 2 numbers in this line:
 *         <ul>
 *             <li>First one specifies maximum digits allowed for each number.</li>
 *             <li>Second one specifies X such that if current line in output has C numbers, then next line has C+X numbers.
 *                  The last line in the output can ignore this rule if numbers provided cannot fill up the last line.
 *             </li>
 *         </ul>
 *     </li>
 *     <li>Sequence of numbers.</li>
 * </ol>
 * writeToFile() writes the string of these numbers which are in triangular pattern to a file.
 * All numbers are <b>left justified</b>.
 * There is no "\n" at the end of the last line.
 *
 * E.g.
 * Input file:
 * 2 2
 * 1 2 3 4 5 6 7 8 9 10
 *
 * Output:
 * | 1  |
 * | 2  | 3  | 4  |
 * | 5  | 6  | 7  | 8  | 9  |
 * | 10 |
 *
 * @author Hongxin Chu
 */

import java.io.*;
import java.lang.String;

public class TriangularNums {

    private int numDigits;
    private int numIncrement;
    private String numbers = " ";

    /**
     * Construct a new TriangularNums object.
     * @param inputPath Path of the input file.
     */
    public TriangularNums(String inputPath) {
        //throw new NotImplementedException();

        FileReader file;
        BufferedReader in;
        String fileLine;

        try {

            file = new FileReader(inputPath);
            in = new BufferedReader(file);

            fileLine = in.readLine();

            setNumDigits(Character.getNumericValue(fileLine.charAt(0)));
            setNumIncrement(Character.getNumericValue(fileLine.charAt(2)));

            System.out.println("Increment by: " + getNumIncrement());
            System.out.println("Num Digits: " + getNumDigits());

            // Store numbers from the file

            numbers = in.readLine();

            System.out.println("Numbers: " + numbers);

            in.close();
            file.close();
        }
        catch (IOException ioExc) {
            System.out.println("IO Exception...");
        }
    }

    /**
     * Writes the string of numbers which are in triangular pattern to a file.
     * Make sure you flush the content to file instead of keeping the string inside the buffer.
     * Make sure no output stream is open when return.
     * @param filePath The path of the file to write.
     */
    public void writeToFile(String filePath) {
        //throw new NotImplementedException();

        try {

            // Split the numbers string at a space ' '
            String[] splitted = numbers.split(" ");

            FileWriter file = new FileWriter(filePath);
            BufferedWriter out = new BufferedWriter(file);

            int totNums = (1 + getNumIncrement());

            // Write the first number into the file
            out.write("| " + splitted[0] + " |\n");

            // Loop iterates through numbers in string
            for(int i = 1; i < splitted.length; i++) {

                out.write("| " + splitted[i] + " ");

                if((i != splitted.length - 1) && ((i % totNums) == 0)) {
                    out.write("|\n");
                    totNums += getNumIncrement();
                    System.out.println("\n\nNew line. i: " + i
                                        + "\nTotal Numbers: " + totNums);
                }
                else if(i == splitted.length - 1) {
                    out.write('|');
                }
            }

            out.close();
            file.close();
        }
        catch (IOException ioExc) {
            System.out.println("IOException Caught");
        }
    }

    public void setNumDigits(int newDigits) {
        numDigits = newDigits;
    }

    public int getNumDigits(){
        return numDigits;
    }

    public void setNumIncrement(int newIncrement) {
        numIncrement = newIncrement;
    }

    public int getNumIncrement() {
        return numIncrement;
    }
}
