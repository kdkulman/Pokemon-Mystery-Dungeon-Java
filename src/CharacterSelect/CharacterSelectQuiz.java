package CharacterSelect;

import DungeonCharacter.Hero.*;
import TileObjects.TileObject;

import java.io.IOException;

//Singleton class
//Model for CharacterSelectView
public class CharacterSelectQuiz {

    private static CharacterSelectQuiz instance;


    private static final int NUMBER_OF_QUESTIONS = 9;
    private static int currentQuestionIndex;

    public static boolean isQuizCompleted() {
        return isQuizCompleted;
    }

    private static boolean isQuizCompleted = false;
    private static int pointsForJirachi;
    private static int pointsForGallade;
    private static int pointsForSnorlax;
    private static int pointsForMagikarp;
    private Hero player;
    private static CharacterSelectQuestion[] arrayOfQuestions;

    private CharacterSelectQuiz(){

        createQuiz();
    }

    public static CharacterSelectQuestion getCurrentQuestion() {
        return arrayOfQuestions[currentQuestionIndex];
    }

    public Hero getPlayer() {
        return player;
    }

    public void addCharacterPoints(final CharacterSelect.CharacterSelectQuestion.Points points) throws IOException {
        if (points == CharacterSelectQuestion.Points.JIRACHI){
            pointsForJirachi++;
        } else if (points == CharacterSelectQuestion.Points.GALLADE){
            pointsForGallade++;
        } else if (points == CharacterSelectQuestion.Points.SNORLAX){
            pointsForSnorlax++;
        } else if (points == CharacterSelectQuestion.Points.MAGIKARP){
            pointsForMagikarp++;
        }
        currentQuestionIndex++;
        if(currentQuestionIndex >= NUMBER_OF_QUESTIONS) {
            isQuizCompleted = true;
            decideHero();
        }

    }

    private void decideHero() throws IOException {
        int max = 0;
        System.out.println(pointsForMagikarp + " points for magikarp");

        if (pointsForMagikarp > max) {
            max = pointsForMagikarp;
            player = new Magikarp();
        }
        System.out.println(pointsForSnorlax + " points for snorlax");

        if (pointsForSnorlax > max) {
            max = pointsForSnorlax;
            player = new Snorlax();
        }
        System.out.println(pointsForGallade + " points for gallade");

        if (pointsForGallade > max) {
            max = pointsForGallade;
            player = new Gallade();
        }
        System.out.println(pointsForJirachi + " points for jirachi");
        if (pointsForJirachi > max) {
            max = pointsForJirachi;
            player = new Jirachi();
        }
    }

    public static CharacterSelectQuiz getInstance() throws IOException {
        if (instance == null){
            instance = new CharacterSelectQuiz();
        }
        return instance;
    }

    private static void createQuiz(){
        arrayOfQuestions = new CharacterSelectQuestion[NUMBER_OF_QUESTIONS];

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
                "Kick their ass", "Send thoughts and prayers", "Try to help but get beat up",
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
        arrayOfQuestions[0] = question1;
        arrayOfQuestions[1] = question2;
        arrayOfQuestions[2] = question3;
        arrayOfQuestions[3] = question4;
        arrayOfQuestions[4] = question5;
        arrayOfQuestions[5] = question6;
        arrayOfQuestions[6] = question7;
        arrayOfQuestions[7] = question8;
        arrayOfQuestions[8] = question9;


    }


}
