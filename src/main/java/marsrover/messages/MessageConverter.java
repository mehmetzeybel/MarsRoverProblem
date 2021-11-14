package marsrover.messages;

import marsrover.exceptions.MessageConverterException;

/**
 * Created by mehmet on 12.11.2021.
 */
public abstract class MessageConverter<T> {

    public final static String VALIDATION_LENGTH_MESSAGE = "INPUT MESSAGE IS NOT FIT";

    public boolean validateMessageLength(String message,Integer minLength,Integer maxLength){
        return !(message.isEmpty() || (maxLength != null && message.length() > maxLength ) || message.length() < minLength);
    }
    public abstract T convertMessage(String message) throws MessageConverterException;
}
