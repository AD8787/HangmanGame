import java.util.*; 

public class JavaArcade {
    public static int attemptsLeft = 6; //Hangman has 6 attempts, so attemptsLeft is set to 6
    
    public static String[] wordBank = { 
        "settled","listing","desert","examination","abilities","holes","avenue","com","emails","appointed",
        "divided","seeds","intellectual","succeed","breathing","sudden","losses","regulation","hospitals",
        "prince","consistently","egg","psychology","resulted","viewing","meets","instantly","tablet","lips",
        "engaging","blow","supplement","lying","gather","popularity","delay","immigration","breaks","upgrade",
        "irish","difficulties","utilize","painful","las","resume","pot","guilty","assigned","pricing",
        "champion","jazz","tons","hosted","combine","ratio","creativity","destroy","dresses","promised",
        "tony","roman","pump","depend","scenario","exam","mystery","apparent","errors","reverse","dramatic",
        "daniel","enormous","jackson","lowest","stability","romantic","dish","rough","surrounded","abroad",
        "democracy","ryan","interviews","tube","pleasant","shoe","blind","mayor","alliance","formation",
        "garage","diamond","affiliate","refused","elections","beats","accommodation","involvement","venue",
        "transaction","dozen","uggs","buyer","physically","masters","fiction","ships","twelve","spa","cry",
        "accomplished","addresses","territory","joseph","experiment","diagnosis","fraud","passage","beside",
        "balanced","passes","wins","silent","quit","responsibilities","hiring","expanded","destruction",
        "nervous","acceptable","hunt","fabulous","actively","drives","aimed","immune","reward","dialogue",
        "begun","gods","republic","un","lock","beneath","essay","highlight","mirror","demonstrated","viewed",
        "persons","fits","virus","silence","curriculum","dc","hiv","worker","piano","thoroughly","inventory",
        "gym","arrested","confused","pdf","evaluate","declared","performances","gathered","opens",
        "conversion","extraordinary","lol","elite","drama"
    }; //word bank of 200 words, all randomly generated from Gemini

    //game variables, including the secret word, the list of letters displayed, and the list of used letters
    public static String secretWord = "";
    public static ArrayList<String> displayLetters = new ArrayList<>();
    public static ArrayList<Character> usedLetters = new ArrayList<>();

    //main method, which handles user input and game logic
    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!");
        Scanner userInput = new Scanner(System.in); //scanner for user input

        secretWord = getRandomWord(); //selects a random word from the word bank
        startGame(); //starts the game by calling the startGame() method

        while (attemptsLeft > 0) { //while loop that continues until the player runs out of attempts
            displayOutline(attemptsLeft); //shows hangman outline based on the number of guesses the player has left through the displayOutline() method

            //prints the displayLetters ArrayList, which shows the letters that have been guessed correctly and underscores for letters that have not been guessed yet
            System.out.println(displayLetters);
            System.out.println("Guess a letter:");
            String userGuess = userInput.nextLine().toLowerCase(); //takes user input for a letter guess (case insensitive)

            //checks to see if the guess is only one letter
            if (userGuess.length() != 1) {
                System.out.println("Guess one letter");
                continue;
            } 
            //checks to see if the letter has already been guessed
            else if (usedLetters.contains(userGuess.charAt(0))) {
                System.out.println("You already guessed that letter");
                continue;
            } 
            //if there are no problems with the guess, it adds the letter to the usedLetters ArrayList
            else {
                usedLetters.add(userGuess.charAt(0));
            }

            if (secretWord.contains(userGuess)) {
                System.out.println("The letter " + userGuess + " is in the word");
                System.out.println("You have " + attemptsLeft + " attempts left."); //displays # of attempts left
                checkGuess(userGuess); //shows the correct letter in the word by calling the checkGuess() method and removing the "_"
            } 
            else {
                System.out.println("The letter " + userGuess + " is not in the word");
                System.out.println("You have " + attemptsLeft + " attempts left."); 
                attemptsLeft--; //subtracts attempts left by 1 if the guess is wrong
            }

            //breaks the loop if the player has guessed all letters (no more "_")
            if (!displayLetters.contains("_")) {
                System.out.println("You guessed the word: " + secretWord);
                break; 
            }
        }

        //if player runs out of attempts before they guess the word, it shows the correct word and ends the game
        if (attemptsLeft == 0) { 
            System.out.println("You lost, the word was: " + secretWord);
        }

        System.out.println("Thanks for playing Hangman!");
        userInput.close();
    }

    //getter method to return a random word from the word bank using the Random class functionality 
    private static String getRandomWord() {
        Random rand = new Random();
        return wordBank[rand.nextInt(wordBank.length)]; 
    }

    //method to display the hangman outline based on the number of attempts left
    private static void displayOutline(int attemptsLeft) {
        if (attemptsLeft == 6){
            System.out.println("""
        -----
        |   |
            |
            |
            |
            |
        """);
        }
        else if (attemptsLeft == 5){
            System.out.println("""
        +---+
        |   |
        O   |
            |
            |
            |
        """);
        }
        else if (attemptsLeft == 4){
            System.out.println("""
        +---+
        |   |
        O   |
       /|   |
            |
            |
        """);
        }
        else if (attemptsLeft == 3){
            System.out.println("""
        +---+
        |   |
        O   |
       /|\\ |
            |
            |
        """);
        }
        else if (attemptsLeft == 2){
            System.out.println("""
        +---+
        |   |
        O   |
       /|\\ |
        |   |
        |   |
        """);
        }
        else if (attemptsLeft == 1){
            System.out.println("""
        +---+
        |   |
        O   |
       /|\\ |
        |   |
        |   |
       /    |
        """);
        }
        else {
            System.out.println("""
        +---+
        |   |
        O   |
       /|\\ |
        |   |
        |   |
       / \\ |
        """);
        }
    }
    
    private static void startGame(){
        displayLetters.clear(); //clearing ALs for new game
        usedLetters.clear();
        attemptsLeft = 6; //reset attemmptsLeft to 6

        //for loop adds "_" for each character in secret word to displayLetters
        for (int i = 0; i < secretWord.length(); i++) {
            displayLetters.add("_");
        }
    }

    //method to check the user's guess and update displayLetters
    private static void checkGuess(String userGuess){
        for (int i = 0; i < secretWord.length(); i++) {
            if (String.valueOf(secretWord.charAt(i)).equals(userGuess)) { //checks if the character at index i in the secret word matches the user's guess
                displayLetters.set(i, userGuess); //updates displayLetters to show correct letter in correct position
            }
        }
    }
}
