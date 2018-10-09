/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Chris Classie>
 * <CSC2859>
 * <16355>
 * DID NOT WORK WITH PARTNER
 * Slip days used: <0>
 * Git URL:
 * Fall 2018
 */


package assignment3;
import java.util.*;
import java.io.*;




public class Main {

    //variables
    public static ArrayList<String> inputs;
    public static ArrayList<String> words;
    public static Set<String> dict;
    public static ArrayList<String> repeat;
    public static boolean exists;
    public static Queue<ArrayList<String>> queue;
    public static ArrayList<String> DFS;

    public static void main(String[] args) throws Exception {

        Scanner kb;	// input Scanner for commands
        PrintStream ps;	// output file, for student testing and grading only
        // If arguments are specified, read/write from/to files instead of Std IO.
        if (args.length != 0) {
            kb = new Scanner(new File(args[0]));
            ps = new PrintStream(new File(args[1]));
            System.setOut(ps);			// redirect output to ps
        } else {
            kb = new Scanner(System.in);// default input from Stdin
            ps = System.out;			// default output to Stdout
        }
        initialize();


        //parse
        ArrayList<String> input = parse(kb);
        ArrayList<String> DFSOutput = getWordLadderDFS(input.get(0), input.get(1));
        ArrayList<String> BFSOutput = getWordLadderBFS(input.get(0), input.get(1));

        //print BFS or DFS ladder
        if (BFSOutput.size() > DFSOutput.size()){
            printLadder(DFSOutput);
        }
        else {
            printLadder(BFSOutput);
        }

    }

    public static void initialize() {
        // initialize your static variables or constants here.
        // We will call this method before running our JUNIT tests.  So call it
        // only once at the start of main.
        words = new ArrayList<String>();
        inputs = new ArrayList<String>();
        dict = makeDictionary();
        repeat = new ArrayList<String>();
        exists = false;
        queue = new LinkedList<ArrayList<String>>();
        DFS = new ArrayList<String>();
    }

    /**
     * @param keyboard Scanner connected to System.in
     * @return ArrayList of Strings containing start word and end word.
     * If command is /quit, return empty ArrayList.
     */
    public static ArrayList<String> parse(Scanner keyboard) {

        String input = keyboard.nextLine();
        Scanner words = new Scanner(input);

        //clears any prior input
        inputs.clear();


        //input two words from the keyboard, if it contains quit, then exit
        if(input.contains("/quit")){
            System.exit(0);
        }

        inputs.add(words.next());
        inputs.add(words.next());
        words.close();

        return inputs;

    }
    /**
    //DFS way of producing the word ladder
    * @param start = beginning of word
     * @param end = end of words
     * @return DFS which is the found word letter
     */


    public static ArrayList<String> getWordLadderDFS(String start, String end) {

        // Returned list should be ordered start to end.  Include start and end.
        // If ladder is empty, return list with just start and end.

        //erase all data before starting
        erase();


        words.add(start.toLowerCase());
        words.add(end.toLowerCase());
        start = start.toUpperCase();
        end = end.toUpperCase();

        Set<String> dict = makeDictionary();


        // check to see if words are in dict
        if(!dict.contains(start) || !dict.contains(end)){
            return null;
        }

        //check if lengths are different
        if(start.length() != end.length()){
            return null;
        }
        //check if words are neighbors
        else if (Neighbors(start, end)){
            return words;
        }

        //add starting word to the front
        ArrayList<String> front = new ArrayList<String>();
        front.add(start);

        repeat.add(start);

        //recursive
       HelperForDFS(start, end, front);

       //optimizes the word ladder
        DFS = Simple(DFS);
       //DFS = Simplified((DFS);

       //returns the found word
        return DFS;
    }


    /**
     * Recurive helper function
     * @param start = start word
     * @param end = end word
     * @param front is the front of the ArrayList
     */


    public static void HelperForDFS(String start, String end, ArrayList<String> front){

        //checks if a valid ladder exists
        if (exists) {
            return;
        }

        //the last word is the end word
        if(front.get(front.size() - 1).equals(end)){
            exists = true;
            DFS = new ArrayList<String>(front);
            return;
        }

        //iterates until dictionary has nothing left
        for(Iterator<String> i = dict.iterator(); i.hasNext();) {



            String next = i.next();


            //if there is a one letter difference or used words contained
            if (Neighbors(next, front.get(front.size() - 1)) && !front.contains(next) && !repeat.contains(next)) {

                //new ladder, adds word to already used list, recursive call with new front
                front.add(next);
                repeat.add(next);
                HelperForDFS(start, end, front);


                if (exists) {
                    return;
                }


                //remove last element from the front
                front.remove(front.size() - 1);

            }

        }

    }


    /**
     * BFS word ladder
     * @param start = start word
     * @param end = end word
     * @return a foudn word ladder, return next elemnt of the queue
     */

    public static ArrayList<String> getWordLadderBFS(String start, String end) {

        //erase all data
        erase();


        words.add(start.toLowerCase());
        words.add(end.toLowerCase());
        start = start.toUpperCase();
        end = end.toUpperCase();

        Set<String> dict = makeDictionary();


        // check to see if words are in dict
        if(!dict.contains(start) || !dict.contains(end)){
            return null;
        }


        if(start.length() != end.length()){
            return null;
        }
        else if (Neighbors(start, end)){
            return words;
        }

        //removes the start word from the dictionary
        dict.remove(start);


        //add the start word into a new array list
        ArrayList<String> firstWord = new ArrayList<String>();
        firstWord.add(start);


        //store the arraylist to be searched
        queue.add(firstWord);

        //take first item out of the queue
        //check for endoflist
        //check for neighbors
        //call recursive BFS
            // add newlist to queue
        //if word differs by one letter, add it
        // newlist added to queue
        //remove the word thats already been used
        //return the next element of the queue


        while(!queue.isEmpty() && !queue.peek().equals(end)){


            ArrayList<String> queueList = queue.remove();

            //EndOfList(queueList)
            if(queueList.get(queueList.size() - 1).equals(end)){
                return queueList;
            }

            //HelperForBFS(start, end, front);

            for(Iterator<String> i = dict.iterator(); i.hasNext();){

                String next = i.next();

                if(Neighbors(next, queueList.get(queueList.size() - 1))){

                 ArrayList<String> List2 = new ArrayList<String>(queueList);
                 List2.add(next);
                 queue.add(List2);
                 i.remove();

                }
            }

        }

    return queue.peek();

    }

    //could not get to work correctly, will implement later if there's time
  /*
  public static void HelperForBFS(String start, String end, ArrayList<String> front){

        if (exists) {
            return;
        }

        if(front.get(front.size() - 1).equals(end)){
            exists = true;
            //BFSOutput = new ArrayList<String>(found);
            return;
        }

        for(Iterator<String> i = dict.iterator(); i.hasNext();) {

            String next = i.next();

            if (Neighbors(next, front.get(front.size() - 1)){

               ArrayString<String> List2 = new ArrayList<String>(queueList);

               List2.add(next);
               queue.add(List2);


                HelperForBFS(start, end, front);

                if (exists) {
                    return;
                }

                i.remove();

            }

        }

    }
*/


    /**
     * prints ladder
     * @param ladder is arraylist that gets printed
     */

    public static void printLadder(ArrayList<String> ladder) {
        if (ladder == null || ladder.size() == 0) {
            System.out.println("no word ladder can be found between " + words.get(0).toLowerCase() + " and " + words.get(1).toLowerCase());
        }
        else {
            System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(0).toLowerCase() + " and " + ladder.get(ladder.size()-1).toLowerCase());
            for (int i = 0; i < ladder.size(); i++) {
                System.out.println(ladder.get(i).toLowerCase());
            }
        }
    }

    // Other private static methods here


    /**
     *
     * @param Index index of the word to replace with char
     * @param a alpha character
     * @param word String
     * @return  new string with above changes
     */
    public static String WordCheck(int Index, char a, String word){
        word = word.replace(word.charAt(Index), a);
        return word;
    }

    /**
     *
     * @param firstword
     * @param secondword
     * @return true if the difference between words is one or less
     */

    public static boolean Neighbors(String firstword, String secondword){

        int OneDiff = 0;

        for(int i = 0; i < firstword.length(); i++){
            if(firstword.charAt(i) != secondword.charAt(i)){
               OneDiff++;
            }
            if (OneDiff > 1){
                return false;
            }
        }

        return true;
    }


    /**
     * Simplifies and optimizes the word ladder
     * @param ladder
     * @return
     */

    // loops through
    //checks if  x and y are neighbors
    // removes elements between x and y
    public static ArrayList<String> Simple(ArrayList<String> ladder){

        //continues until simplified
        while(!Simplified(ladder)){

            for(int x = 0; x < ladder.size() - 2; x++){
                for(int y = x + 2; y < ladder.size(); y++){
                    if(Neighbors(ladder.get(x), ladder.get(y))){
                        ladder.subList(x + 1, y).clear();
                    }
                }
            }

        }

        return ladder;

    }



    public static boolean Simplified(ArrayList<String> ladder) {
        for (int x = 0; x < ladder.size() - 2; x++) {
            for (int y = x + 2; y < ladder.size(); y++) {
                if (Neighbors(ladder.get(x), ladder.get(y))) {

                    return false;
                }
            }
        }
        return true;
    }


    //erases all of the data
    public static void erase(){
        words = new ArrayList<String>();
        inputs = new ArrayList<String>();
        dict = makeDictionary();
        repeat = new ArrayList<String>();
        exists = false;
        queue = new LinkedList<ArrayList<String>>();
        DFS = new ArrayList<String>();
    }


    /* Do not modify makeDictionary */
    public static Set<String>  makeDictionary () {
        Set<String> words = new HashSet<String>();
        Scanner infile = null;
        try {
            infile = new Scanner (new File("five_letter_words.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary File not Found!");
            e.printStackTrace();
            System.exit(1);
        }
        while (infile.hasNext()) {
            words.add(infile.next().toUpperCase());
        }
        return words;
    }
}
