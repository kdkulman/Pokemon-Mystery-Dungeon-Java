package Views;

//Singleton

//Singleton
public class Message {
    private static Message instance;
    private static String myMessage;
    private static String myPreviousMessage;
    private static boolean isVisible;

    private Message(){
        myMessage = "";
        myPreviousMessage = "";
        isVisible = false;
    }

    public static void setMessage(final String THE_MESSAGE) {
        if (THE_MESSAGE.equals("")) {
            isVisible = false;
        } else {
            isVisible = true;
            myPreviousMessage = myMessage;
            myMessage = THE_MESSAGE;
        }
    }

    public static void setIsVisible(final boolean visible){
        isVisible = visible;
    }

    public static boolean getVisible(){
        return isVisible;
    }

    public static String getMessage() {
        return myMessage;
    }

    public static Message getInstance(){
        if (instance == null) return new Message();
        return instance;
    }

    public static String getPreviousMessage() {
        return myPreviousMessage;
    }

}
