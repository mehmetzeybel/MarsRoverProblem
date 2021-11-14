package marsrover.exceptions;

/**
 * Created by mehmet on 13.11.2021.
 * Throws when string message is not valid for Robot operations
 */
public class MessageConverterException extends RuntimeException{
    public MessageConverterException(String inputMessage,String inputMessageType,String message){
        super("Input Message : " + inputMessage + ","+"MessageType : "+ inputMessageType + "," + "Message : "+ message);
    }
}
