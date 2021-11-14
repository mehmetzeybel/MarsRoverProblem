package marsrover.messages;

import marsrover.exceptions.MessageConverterException;
import marsrover.models.Mars;

/**
 * Created by mehmet on 12.11.2021.
 */
public class MarsMessageConverter extends MessageConverter<Mars>{
    private static final String MARS_MESSAGE = "Mars Message";
    private static final String INVALID_MARS_POSITION = "Invalid Mars Position";

    @Override
    public Mars convertMessage(String message) throws MessageConverterException {
        if(!validateMessageLength(message,3,3)){
            throw new MessageConverterException(message,MARS_MESSAGE,VALIDATION_LENGTH_MESSAGE);
        }
        try {
            int x = Character.getNumericValue(message.charAt(0));
            int y = Character.getNumericValue(message.charAt(2));

            if(validateMarsPostion(x,y)){
                throw new MessageConverterException(message,MARS_MESSAGE,INVALID_MARS_POSITION);
            }
            return new Mars(x,y);
        }catch (MessageConverterException e){
            throw e;
        }catch (Exception e){
            throw new MessageConverterException(message,MARS_MESSAGE,e.getMessage());
        }


    }

    private boolean validateMarsPostion(int x, int y){
        return x<0||y<0;
    }

}
