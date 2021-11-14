package marsrover;

import marsrover.controller.RobotController;
import marsrover.messages.InstructionMessageConverter;
import marsrover.messages.MarsMessageConverter;
import marsrover.messages.RobotInitializeMessageConverter;
import marsrover.models.Mars;
import marsrover.models.Movement;
import marsrover.models.Robot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehmet on 11.11.2021.
 */
public class MarsRover {


    /**
     * Apply instructions of robots with given message list
     * @param messages instruction as String Array
     * @return all robots last positions
     */
    public List<Robot> startProcesses(String[] messages){


        MarsMessageConverter marsMessageConverter = new MarsMessageConverter();
        RobotInitializeMessageConverter robotInitializeMessageConverter = new RobotInitializeMessageConverter();
        InstructionMessageConverter instructionMessageConverter = new InstructionMessageConverter();

        //Set Mars boundaries from first line of string input
        Mars mars = marsMessageConverter.convertMessage(messages[0]);
        List<Robot> allRobotPositions = new ArrayList<>();
        for(int i = 1;i<messages.length;i++){

            //Set Robot initial position from string
            Robot robot = robotInitializeMessageConverter.convertMessage(messages[i]);

            //Set Movement instructionList from next line
            ++i;
            List<Movement> movementList = instructionMessageConverter.convertMessage(messages[i]);
            for(int move=0;move<movementList.size();move++){
                RobotController.setNewPosition(robot,movementList.get(move),mars);
            }
            allRobotPositions.add(robot);
        }
        return allRobotPositions;
    }

    public static void main(String args[]) {
        final String[] testMessage = {"5 5","1 2 N","LMLMLMLMM","3 3 E","MMRMMRMRRM"};
        MarsRover marsRover = new MarsRover();

        for(Robot robot : marsRover.startProcesses(testMessage)){
            System.out.println(robot.toString());
        }

    }
}
