package Views;

//Singleton

//Singleton
public class Message {
    private static Message myInstance;
    private static String myMessage;
    private static String myPreviousMessage;
    private static boolean myIsVisible;

    private Message(){
        myMessage = "";
        myPreviousMessage = "";
        myIsVisible = false;
    }

    public static void setMessage(final String theMessage) {
        if (theMessage.equals("")) {
            myIsVisible = false;
        } else {
            myIsVisible = true;
            myPreviousMessage = myMessage;
            myMessage = theMessage;
        }
    }

    public static void setMyIsVisible(final boolean theVisible){
        myIsVisible = theVisible;
    }

    public static boolean getVisible(){
        return myIsVisible;
    }

    public static String getMessage() {
        return myMessage;
    }

    public static Message getMyInstance(){
        if (myInstance == null) return new Message();
        return myInstance;
    }

    public static String getPreviousMessage() {
        return myPreviousMessage;
    }

}
