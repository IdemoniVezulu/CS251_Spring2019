import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is your Main program. This File will be run to test all cases, based on the input different function will be called.
 *
 * Complete the given and add necessary functions as  per your requirement.
 */

public class Main {
    public static void StackChecking(Scanner in, PrintWriter out){
        //use in and out for file input output
        //use your implemented stack for solving problem 1

        in.nextLine();

        // Create new Stack

        Stack stack = new Stack();

        // Read in first line and store the number of lines that are going to follow

        String curLine = in.nextLine();
        int numLines = Character.getNumericValue(curLine.charAt(0));

        // Loop to iterate through lines


        for(int i = 0; i < numLines; i++){

            // Get new line

            curLine = in.nextLine();

            // Read the first character

            char action = curLine.charAt(0);

            if(action == 'p'){

                // Pop element from stack

                if(stack.isEmpty()){
                   if(i < (numLines - 1)) {
                       out.write("empty\n");
                   }
                   else {
                       out.write("empty");
                   }
                   return;
                }

                Node popped = stack.pop();
                if(i < (numLines - 1)) {
                    out.write(popped.getData().getA() + " " + popped.getData().getB() + '\n');
                }
                else {
                    out.write(popped.getData().getA() + " " + popped.getData().getB());
                }
            }
            else if(action == 'i'){

                // Push element into stack

                Pair newPair = new Pair();

                newPair.setA(Character.getNumericValue(curLine.charAt(2)));
                newPair.setB(Character.getNumericValue(curLine.charAt(4)));

                stack.push(newPair);
            }
        }
    }

    public static void WordSearch(Scanner in, PrintWriter out){
        //use in and out for file input output
        //use your implemented stack for solving problem 2

        Stack stack = new Stack();

        in.nextLine();
        String curLine = in.nextLine();
        int rows = Character.getNumericValue(curLine.charAt(0));
        int columns = Character.getNumericValue(curLine.charAt(2));

        String[] stringMatrix = new String[rows];

        for(int i = 0; i < rows; i++){
            curLine = in.nextLine() + '\n';
            stringMatrix[i] = curLine;
        }

        String wordToFind = in.nextLine();

        // Convert string[] to char[][]

        char[][] matrix = new char[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0, k = 0; (j < columns); j++, k += 2){
                if(stringMatrix[i].charAt(k) != ' '){
                    matrix[i][j] = stringMatrix[i].charAt(k);
                }
            }
        }

        // Convert string to char array

        int length = wordToFind.length();
        char[] newWord = new char[length];

        for(int i = 0; i < length; i++) {
            if(wordToFind.charAt(i) != '\n') {
                newWord[i] = wordToFind.charAt(i);
            }
        }

        findChar(newWord, stack, matrix, out);

        if(stack.isEmpty()){
            out.write("not found");
        }
    }

    /*
        This method is used to find if the word is present in the given crossword.
        It accepts the word, stack, the grid, and the PrintWriter as arguments.
     */

    public static void findChar(char[] word, Stack stack, char[][] crossword, PrintWriter out){

        // TODO: Implement this function

        char prevFound = word[0];

        for(int k = 0; k < word.length; k++) {
            char letterOfWord = word[k];

            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[0].length; j++) {
                    if (crossword[j][i] == letterOfWord) {
                        Pair temp = new Pair();

                        if(letterOfWord == prevFound){
                            stack.pop();
                        }

                        temp.setA(j);
                        temp.setB(i);
                        stack.push(temp);
                        out.write(j + " " + i + "\n");
                        prevFound = letterOfWord;

                        if (findLeft(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                        else if (findRight(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                        else if (findBelow(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                        else if (findAbove(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                        else if (findDiagAboveLeft(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                        else if (findDiagBelowRight(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                        else if (findDiagBelowLeft(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                        else if (findDiagAboveRight(j, i, word, crossword)) {
                            temp.setA(j);
                            temp.setB(i);
                            stack.push(temp);
                            out.write(j + " " + i + "\n");
                        }
                    }
                }
            }
        }
    }

    /*
        This function is used to check to the left of the current char in a char[][]
     */

    private static boolean findLeft(int x, int y, char[] word, char[][] grid){
        boolean isFound = false;

        if (((x + 1) - word.length) < 0) {
            isFound = false;
        }
        else {
            int index = x;

            // Search through every letter in the word

            for (char letter : word) {
                if (grid[index][y] != letter)
                    return false;
                index--;
            }

            isFound = true;
        }

        return isFound;
    }

    /*
        This function is used to check to the right of the current char in a char[][]
     */

    private static boolean findRight(int x, int y, char[] word, char[][] grid)
    {
        boolean isFound = false;

        if ((x + word.length) > grid[0].length) {
            isFound = false;
        }
        else {
            int index = x;

            // Search through every letter in the word

            for (char letter : word) {
                if (grid[index][y] != letter) {
                    return false;
                }

                index++;
            }

            isFound = true;
        }

        return isFound;
    }

    /*
        This function is used to check below the current char in a char[][]
     */

    private static boolean findBelow(int x, int y, char[] word, char[][] grid){
        boolean isFound = false;

        if ((y + word.length) > grid.length) {
            isFound = false;
        }
        else {
            int index = y;
            for (char letter : word) {
                if (grid[x][index] != letter) {
                    return false;
                }

                index++;
            }

            isFound = true;
        }

        return isFound;
    }

    /*
        This function is used to check above the current char in a char[][]
     */

    private static boolean findAbove(int x, int y, char[] word, char[][] grid){
        boolean isFound = false;

        if (((y + 1) - word.length) < 0) {
            isFound = false;
        }
        else {
            int index = y;
            for (char letter : word) {
                if (grid[x][index] != letter) {
                    return false;
                }

                index--;
            }

            isFound = true;
        }

        return isFound;
    }

    /*
        This function is used to check diagonally left above the current char in a char[][]
     */

    private static boolean findDiagAboveLeft(int x, int y, char[] word, char[][] grid){
        boolean isFound = false;

        if ((((y + 1) - word.length) < 0) || ((x + 1) - word.length < 0)) {
            isFound = false;
        }
        else {
            int indexX = x, indexY = y;
            for (char letter : word) {
                if (grid[indexX][indexY] != letter) {
                    return false;
                }

                indexX--;
                indexY--;
            }

            isFound = true;
        }

        return isFound;
    }

    /*
        This function is used to check diagonally right below the current char in a char[][]
     */

    private static boolean findDiagBelowRight(int x, int y, char[] word, char[][] grid){
        boolean isFound = false;

        if (((y + word.length) > grid.length) || ((x + word.length) > grid[0].length)) {
            isFound = false;
        }
        else {
            int indexX = x, indexY = y;
            for (char letter : word) {
                if (grid[indexX][indexY] != letter) {
                    return false;
                }

                indexX++;
                indexY++;
            }

            isFound = true;
        }

        return isFound;
    }

    /*
        This function is used to check diagonally left below the current char in a char[][]
     */

    private static boolean findDiagBelowLeft(int x, int y, char[] word, char[][] grid){
        boolean isFound = false;

        if (((y + word.length) > grid.length) || (((x + 1) - word.length) < 0)) {
            isFound = false;
        }
        else {
            int indexX = x, indexY = y;
            for (char letter : word) {
                if (grid[indexX][indexY] != letter) {
                    return false;
                }

                indexX--;
                indexY++;
            }

            isFound = true;
        }

        return isFound;
    }

    /*
        This function is used to check diagonally left below the current char in a char[][]
     */

    private static boolean findDiagAboveRight(int x, int y, char[] word, char[][] grid){
        boolean isFound = false;

        if ((((y + 1) - word.length) < 0) || ((x + word.length) > grid[0].length)) {
            isFound = false;
        }
        else {
            int indexX = x, indexY = y;
            for (char letter : word) {
                if (grid[indexX][indexY] != letter) {
                    return false;
                }

                indexX++;
                indexY--;
            }

            return true;
        }

        return isFound;
    }

    /*
        This method is used to carry out the operations on a circular deque
        It enques, and deques depending on the input
     */

    public static void CircularQueue(Scanner in, PrintWriter out){
        //use in and out for file input output
        //use your implemented stack for solving problem 3

        CircularDeque circularDeque = new CircularDeque();

        in.nextLine();

        String curLine = in.nextLine();
        int lines = Character.getNumericValue(curLine.charAt(0));

        for(int i = 0; i < lines; i++){

            curLine = in.nextLine();

            char action = curLine.charAt(0);
            if(action == 'e'){

                char side = curLine.charAt(2);
                int data = Character.getNumericValue(curLine.charAt(4));

                if(side == 'f'){
                    circularDeque.enqueue_front(data);
                }
                else {
                    circularDeque.enqueue_back(data);
                }
            }
            else if(action == 'd') {

                if(circularDeque.isEmpty()){
                    if(i < (lines - 1)) {
                        out.write("empty\n");
                    }
                    else {
                        out.write("empty");
                    }
                    return;
                }

                int retVal = -1;
                char side = curLine.charAt(2);
                if(side == 'f'){
                    retVal = circularDeque.dequeue_front();
                    if (i < (lines - 1)){
                        out.write(retVal + "\n");
                    }
                    else {
                        out.write(retVal);
                    }
                }
                else {
                    retVal = circularDeque.dequeue_back();
                    if (i < (lines - 1)){
                        out.write(retVal + "\n");
                    }
                    else {
                        out.write(retVal);
                    }
                }
            }
            else if(action == 's') {
                if (i < (lines - 1)) {
                    out.print(circularDeque.getSize() + " " + circularDeque.getFront() + " " + circularDeque.getRear() + "\n");
                }
                else {
                    out.print(circularDeque.getSize() + " " + circularDeque.getFront() + " " + circularDeque.getRear());
                }
            }
        }
    }

    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("ERROR: Not enough Parameters");
            System.exit(0);
        }

        Scanner in=null;
        PrintWriter out=null;

        try {
            in = new Scanner(new File(args[0]));
            out= new PrintWriter(new File(args[1]));
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }

        int ProblemNo=in.nextInt();

        if(ProblemNo==1){
            //Problem 1: Checking of your implemented stack
            StackChecking(in,out);
        }
        else if(ProblemNo==2){
            //Problem 2: Word searching probme
            WordSearch(in, out);
        }
        else if(ProblemNo==3){
            //Problem 3: Circular Dequeue Implementation checking
            CircularQueue(in, out);
        }
        else{
            System.out.println("Invalid Input");
            System.exit(0);
        }

        in.close();
        out.close();
    }
}