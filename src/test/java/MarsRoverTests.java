import marsrover.MarsRover;
import marsrover.controller.RobotController;
import marsrover.exceptions.MarsOverBoundException;
import marsrover.exceptions.MessageConverterException;
import marsrover.messages.InstructionMessageConverter;
import marsrover.messages.MarsMessageConverter;
import marsrover.messages.RobotInitializeMessageConverter;
import marsrover.models.Face;
import marsrover.models.Mars;
import marsrover.models.Movement;
import marsrover.models.Robot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by mehmet on 13.11.2021.
 */
public class MarsRoverTests {

    private MarsMessageConverter marsMessageConverter = new MarsMessageConverter();
    private InstructionMessageConverter instructionMessageConverter =new InstructionMessageConverter();
    private RobotInitializeMessageConverter robotInitializeMessageConverter = new RobotInitializeMessageConverter();

    @Test
    public void When_Create_New_Mars(){
        String marsCreateMessage = "5 5";
        Mars mars = marsMessageConverter.convertMessage(marsCreateMessage);
        Mars controlMars = new Mars(5,5);
        assertEquals(mars.getX(),controlMars.getX());
        assertEquals(mars.getY(),controlMars.getY());
    }
    @Test
    public void When_Create_New_Mars_With_Invalid(){
        String marsCreateMessage = "-3 5";
        Exception exception= assertThrows(MessageConverterException.class,()->marsMessageConverter.convertMessage(marsCreateMessage));
        assertEquals(exception.getMessage(),"Input Message : "+marsCreateMessage+",MessageType : Mars Message,Message : INPUT MESSAGE IS NOT FIT");
    }
    @Test
    public void When_Init_Robot_Position_Invalid(){
        String robotInitMessage = "1 2 3 N";
        assertThrows(MessageConverterException.class,()->robotInitializeMessageConverter.convertMessage(robotInitMessage));
    }

    @Test
    public void When_Init_Robot_Position_Invalid_Face(){
        String robotInitMessage = "1 2 P";
        assertThrows(MessageConverterException.class,()->robotInitializeMessageConverter.convertMessage(robotInitMessage));
    }

    @Test
    public void When_Init_Robot_Position(){
        String robotInitMessage = "1 2 N";
        Robot robot = robotInitializeMessageConverter.convertMessage(robotInitMessage);
        assertEquals(robot.getX(),1);
        assertEquals(robot.getY(),2);
        assertEquals(robot.getFace(), Face.N);
    }

    @Test
    public void When_Instruction_Message_Set(){
        String instructionMessage = "LMLM";
        List<Movement> expectedMovementList = new ArrayList<>();
        expectedMovementList.add(Movement.LEFT);
        expectedMovementList.add(Movement.MOVE);
        expectedMovementList.add(Movement.LEFT);
        expectedMovementList.add(Movement.MOVE);
        List<Movement> movementList=instructionMessageConverter.convertMessage(instructionMessage);
        assertEquals(movementList,expectedMovementList);
    }
    @Test
    public void When_Start_Instruction(){
        String[] inputMessage = {"5 5","1 2 N","LMLMLMLMM"};
        MarsRover marsRover = new MarsRover();
        List<Robot> robotList = marsRover.startProcesses(inputMessage);
        assertEquals(1,robotList.size());
        assertEquals("1 3 N",robotList.get(0).toString());
    }

    @Test
    public void When_Robot_MarsOverBound(){
        Mars mars = new Mars(2,2);
        Robot robot = new Robot(2,2,Face.E);
        assertThrows(MarsOverBoundException.class,()->RobotController.setNewPosition(robot,Movement.MOVE,mars));
    }
    @Test
    public void When_Multiple_Robot_Start_Instruction(){
        String[] inputMessage = {"5 5","1 2 N","LMLMLMLMM","3 3 E","MMRMMRMRRM"};
        MarsRover marsRover = new MarsRover();
        List<Robot> robotList = marsRover.startProcesses(inputMessage);
        assertEquals(2,robotList.size());
        assertEquals("1 3 N",robotList.get(0).toString());
        assertEquals("5 1 E",robotList.get(1).toString());
    }
}
