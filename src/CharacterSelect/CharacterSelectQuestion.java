package CharacterSelect;

public class CharacterSelectQuestion {

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public Points getAnswer1Points() {
        return answer1Points;
    }

    public Points getAnswer2Points() {
        return answer2Points;
    }

    public Points getAnswer3Points() {
        return answer3Points;
    }

    public Points getAnswer4Points() {
        return answer4Points;
    }

    private Points answer1Points;
    private Points answer2Points;
    private Points answer3Points;
    private Points answer4Points;

    public CharacterSelectQuestion(final String question, final String answer1, final String answer2,
                                   final String answer3, final String answer4, final Points answer1Points,
                                   final Points answer2Points, final Points answer3Points, final Points answer4Points){
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer1Points = answer1Points;
        this.answer2Points = answer2Points;
        this.answer3Points = answer3Points;
        this.answer4Points = answer4Points;
    }

    public enum Points{
        JIRACHI,
        GALLADE,
        SNORLAX,
        MAGIKARP
    }


    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }


}
