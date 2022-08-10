package Views;

//Singleton

public class Message {
    private static String myMessage;
    private static boolean visible;


    public static void setMessage(final String THE_MESSAGE) {
        myMessage = THE_MESSAGE;
    }

    public static String getMessage() {
        return myMessage;
    }
    public static boolean getVisible() {
        return visible;
    }

    public static void setVisible(boolean visible) {
        Message.visible = visible;
    }

}
