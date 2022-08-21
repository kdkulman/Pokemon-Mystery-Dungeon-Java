package CharacterSelect;

import DungeonCharacter.Hero.*;

import java.io.IOException;

//Singleton class
//Model for CharacterSelectView
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class CharacterSelectQuiz {

    private static CharacterSelectQuiz myInstance;
    private static final int NUMBER_OF_QUESTIONS = 9;
    private static int myCurrentQuestionIndex;
    private static boolean myIsQuizCompleted = false;
    private static int myPointsForJirachi;
    private static int myPointsForGallade;
    private static int myPointsForSnorlax;
    private static int myPointsForMagikarp;
    private static CharacterSelectQuestion[] myArrayOfQuestions;
    private Hero myPlayer;

    /**
     * Private constructor
     */
    private CharacterSelectQuiz(){
        createQuiz();
    }

    /**
     *
     * @return current question from array
     */
    public static CharacterSelectQuestion getCurrentQuestion() {
        return myArrayOfQuestions[myCurrentQuestionIndex];
    }

    /**
     *
     * @return myPlayer field
     */
    public Hero getMyPlayer() {
        return myPlayer;
    }

    /**
     *
     * @param thePoints
     * @throws IOException
     */
    public void addCharacterPoints(final CharacterSelect.CharacterSelectQuestion.Points thePoints) throws IOException {
        if (thePoints == CharacterSelectQuestion.Points.JIRACHI){
            myPointsForJirachi++;
        } else if (thePoints == CharacterSelectQuestion.Points.GALLADE){
            myPointsForGallade++;
        } else if (thePoints == CharacterSelectQuestion.Points.SNORLAX){
            myPointsForSnorlax++;
        } else if (thePoints == CharacterSelectQuestion.Points.MAGIKARP){
            myPointsForMagikarp++;
        }
        myCurrentQuestionIndex++;
        if(myCurrentQuestionIndex >= NUMBER_OF_QUESTIONS) {
            myIsQuizCompleted = true;
            decideHero();
        }

    }

    /**
     * Method that decides which hero the player will be based on points
     *
     * @throws IOException
     */
    private void decideHero() throws IOException {
        int max = 0;
        System.out.println(myPointsForMagikarp + " points for magikarp");

        if (myPointsForMagikarp > max) {
            max = myPointsForMagikarp;
            myPlayer = new Magikarp();
        }
        System.out.println(myPointsForSnorlax + " points for snorlax");

        if (myPointsForSnorlax > max) {
            max = myPointsForSnorlax;
            myPlayer = new Snorlax();
        }
        System.out.println(myPointsForGallade + " points for gallade");

        if (myPointsForGallade > max) {
            max = myPointsForGallade;
            myPlayer = new Gallade();
        }
        System.out.println(myPointsForJirachi + " points for jirachi");
        if (myPointsForJirachi > max) {
            max = myPointsForJirachi;
            myPlayer = new Jirachi();
        }
    }

    /**
     * method that returns current quiz instance or creates new quiz if none
     * @return
     * @throws IOException
     */
    public static CharacterSelectQuiz getMyInstance() throws IOException {
        if (myInstance == null){
            myInstance = new CharacterSelectQuiz();
        }
        return myInstance;
    }
    /**
     *
     * @return myIsQuizCompleted field
     */
    public static boolean isQuizCompleted() {
        return myIsQuizCompleted;
    }
    /**
     *
     * @return myCurrentQuestionIndex field
     */
    private static void createQuiz(){
        myArrayOfQuestions = new CharacterSelectQuestion[NUMBER_OF_QUESTIONS];

        CharacterSelectQuestion question1 = new CharacterSelectQuestion(
                "Do you think you're built different?", "Yes", "No",
                null, null, CharacterSelectQuestion.Points.JIRACHI,
                CharacterSelectQuestion.Points.MAGIKARP, null, null);

        CharacterSelectQuestion question2 = new CharacterSelectQuestion(
                "What is your favorite programming language?", "Java", "Python",
                "Javascript", "Other", CharacterSelectQuestion.Points.MAGIKARP,
                CharacterSelectQuestion.Points.SNORLAX, CharacterSelectQuestion.Points.JIRACHI,
                CharacterSelectQuestion.Points.GALLADE);

        CharacterSelectQuestion question3 = new CharacterSelectQuestion(
                "You see someone being bullied, what do you do?", "Don't get involved",
                "Kick their ass", "Send thoughts and prayers", "Try to help but get beat myUp",
                CharacterSelectQuestion.Points.SNORLAX, CharacterSelectQuestion.Points.GALLADE,
                CharacterSelectQuestion.Points.JIRACHI, CharacterSelectQuestion.Points.MAGIKARP
        );

        CharacterSelectQuestion question4 = new CharacterSelectQuestion(
                "Is it better to be respected than loved?", "Yes", "No",
                null, null, CharacterSelectQuestion.Points.GALLADE,
                CharacterSelectQuestion.Points.SNORLAX, null, null
        );
        CharacterSelectQuestion question5 = new CharacterSelectQuestion(
                "What happens after death?", "I ascend to the afterlife",
                "More dissapointment", "The simulation ends",
                "Too lazy to care", CharacterSelectQuestion.Points.JIRACHI,
                CharacterSelectQuestion.Points.MAGIKARP, CharacterSelectQuestion.Points.GALLADE,
                CharacterSelectQuestion.Points.SNORLAX
        );
      CharacterSelectQuestion question6 = new CharacterSelectQuestion(
              "What did you think of Morbius?", "Greatest movie of all time",
              "He's my dad", "Inspired me to drink blood",
              "Cringe", CharacterSelectQuestion.Points.JIRACHI, CharacterSelectQuestion.Points.GALLADE,
              CharacterSelectQuestion.Points.SNORLAX, CharacterSelectQuestion.Points.MAGIKARP
      );
      CharacterSelectQuestion question7 = new CharacterSelectQuestion(
                "What do you do with your money?", "Spend it!",
                "Save it!", "Spend half, save half",
                "I don't have money", CharacterSelectQuestion.Points.GALLADE, CharacterSelectQuestion.Points.JIRACHI,
                CharacterSelectQuestion.Points.SNORLAX, CharacterSelectQuestion.Points.MAGIKARP
      );
        CharacterSelectQuestion question8 = new CharacterSelectQuestion(
                "Do you state your opinion even if people don't like it?", "Yes",
                "No", "Empty head",
                "Too lazy to socialize", CharacterSelectQuestion.Points.GALLADE, CharacterSelectQuestion.Points.JIRACHI,
                CharacterSelectQuestion.Points.MAGIKARP, CharacterSelectQuestion.Points.SNORLAX
        );
        CharacterSelectQuestion question9 = new CharacterSelectQuestion(
                "Does the end justify the means?", "Yes",
                "No", "If the end benefits all, yes",
                "Violence is wrong", CharacterSelectQuestion.Points.JIRACHI, CharacterSelectQuestion.Points.SNORLAX,
                CharacterSelectQuestion.Points.GALLADE, CharacterSelectQuestion.Points.MAGIKARP
        );
        myArrayOfQuestions[0] = question1;
        myArrayOfQuestions[1] = question2;
        myArrayOfQuestions[2] = question3;
        myArrayOfQuestions[3] = question4;
        myArrayOfQuestions[4] = question5;
        myArrayOfQuestions[5] = question6;
        myArrayOfQuestions[6] = question7;
        myArrayOfQuestions[7] = question8;
        myArrayOfQuestions[8] = question9;
    }
}
