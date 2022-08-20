package CharacterSelect;

public class CharacterSelectQuestion {

    private String myQuestion;
    private String myAnswer1;
    private String myAnswer2;
    private String myAnswer3;
    private String myAnswer4;
    private Points myAnswer1Points;
    private Points myAnswer2Points;
    private Points myAnswer3Points;
    private Points myAnswer4Points;

    public CharacterSelectQuestion(final String theQuestion, final String theAnswer1, final String theAnswer2,
                                   final String theAnswer3, final String theAnswer4, final Points theAnswer1Points,
                                   final Points theAnswer2Points, final Points theAnswer3Points, final Points theAnswer4Points){
        this.myQuestion = theQuestion;
        this.myAnswer1 = theAnswer1;
        this.myAnswer2 = theAnswer2;
        this.myAnswer3 = theAnswer3;
        this.myAnswer4 = theAnswer4;
        this.myAnswer1Points = theAnswer1Points;
        this.myAnswer2Points = theAnswer2Points;
        this.myAnswer3Points = theAnswer3Points;
        this.myAnswer4Points = theAnswer4Points;
    }

    public enum Points{
        JIRACHI,
        GALLADE,
        SNORLAX,
        MAGIKARP
    }
    public String getMyQuestion() {
        return myQuestion;
    }

    public String getMyAnswer1() {
        return myAnswer1;
    }

    public String getMyAnswer2() {
        return myAnswer2;
    }

    public String getMyAnswer3() {
        return myAnswer3;
    }

    public String getMyAnswer4() {
        return myAnswer4;
    }

    public Points getMyAnswer1Points() {
        return myAnswer1Points;
    }

    public Points getMyAnswer2Points() {
        return myAnswer2Points;
    }

    public Points getMyAnswer3Points() {
        return myAnswer3Points;
    }

    public Points getMyAnswer4Points() {
        return myAnswer4Points;
    }



}
