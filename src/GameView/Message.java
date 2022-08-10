package GameView;

<<<<<<< HEAD
//Singleton
public class Message {
    private static String myMessage;



    private static boolean visible;

=======
public class Message {
    private static String myMessage;

>>>>>>> main
    public static void setMessage(final String THE_MESSAGE) {
        myMessage = THE_MESSAGE;
    }

    public static String getMessage() {
        return myMessage;
    }
<<<<<<< HEAD
    public static boolean getVisible() {
        return visible;
    }

    public static void setVisible(boolean visible) {
        Message.visible = visible;
    }
=======
>>>>>>> main
}
