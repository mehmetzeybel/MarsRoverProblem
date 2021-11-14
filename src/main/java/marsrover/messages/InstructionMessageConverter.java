package marsrover.messages;

import marsrover.exceptions.MessageConverterException;
import marsrover.models.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mehmet on 13.11.2021.
 */
public class InstructionMessageConverter extends MessageConverter<List<Movement>>{

    private static final String INSTRUCTION_MESSAGE= "Instruction Message";
    private static final String DESCRIPTION_IS_NOT_VALID= "Instruction is not valid.Please check input message";

    /**
     * Convert String to movement list
     * @param message input message
     * @return Movement List
     * @throws MessageConverterException
     */
    @Override
    public List<Movement> convertMessage(String message) throws MessageConverterException {
        List<Movement> movementList = new ArrayList<>();
        for(int i = 0; i<message.length();i++){

            if(validateMessageLength(message,1,0)){
                throw new MessageConverterException(message,INSTRUCTION_MESSAGE,VALIDATION_LENGTH_MESSAGE);
            }
            Optional<Movement> movement = Movement.findByDescription(message.charAt(i));

            if(!movement.isPresent()){
                throw new MessageConverterException(message,INSTRUCTION_MESSAGE,DESCRIPTION_IS_NOT_VALID);
            }
            movementList.add(movement.get());
        }
        return movementList;
    }
}
