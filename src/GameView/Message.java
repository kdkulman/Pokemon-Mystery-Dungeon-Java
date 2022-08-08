package GameView;

public class Message {
    private static String myMessage;

    public static void setMessage(final String THE_MESSAGE) {
        myMessage = THE_MESSAGE;
    }

    public static String getMessage() {
        return myMessage;
    }
}
