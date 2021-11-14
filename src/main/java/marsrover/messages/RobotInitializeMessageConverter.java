package marsrover.messages;

import marsrover.controller.FaceCalculator;
import marsrover.exceptions.MessageConverterException;
import marsrover.models.Face;
import marsrover.models.Robot;

/**
 * Created by mehmet on 13.11.2021.
 */
public class RobotInitializeMessageConverter extends MessageConverter<Robot> {
    private static final String ROBOT_MESSAGE = "ROBOT MESSAGE ";

    @Override
    public Robot convertMessage(String message) throws MessageConverterException {
        if(!validateMessageLength(message,5,5)){
            throw new MessageConverterException(message,ROBOT_MESSAGE,VALIDATION_LENGTH_MESSAGE);
        }
        try {
            int x = Character.getNumericValue(message.charAt(0));
            int y = Character.getNumericValue(message.charAt(2));
            Face face = FaceCalculator.getFaceWithDescription(message.charAt(4));

            return new Robot(x,y,face);
        }catch (Exception e){
            throw new MessageConverterException(message,ROBOT_MESSAGE,e.getMessage());
        }
    }
}
