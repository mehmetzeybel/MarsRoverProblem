package marsrover.controller;

import lombok.SneakyThrows;
import marsrover.exceptions.MarsOverBoundException;
import marsrover.models.Face;
import marsrover.models.Mars;
import marsrover.models.Movement;
import marsrover.models.Robot;

/**
 * Created by mehmet on 11.11.2021.
 * Set robot operation
 */
public class RobotController {

    private final static int POS_ROTATION=90;
    private final static int NEG_ROTATION=270;

    /**
     * Set new expected position with face on mars
     * @param robot
     * @param movement
     * @param mars
     */
    @SneakyThrows
    public static void setNewPosition(Robot robot, Movement movement, Mars mars){
        Integer currentRotation = robot.getFace().getRotation();

        //Set next position with face
        if(movement.equals(Movement.MOVE)){
            if(robot.getFace().equals(Face.N)){
                robot.setY(robot.getY()+1);
            }else if(robot.getFace().equals(Face.E)){
                robot.setX(robot.getX()+1);
            }else if(robot.getFace().equals(Face.W)){
                robot.setX(robot.getX()-1);
            }else if(robot.getFace().equals(Face.S)){
                robot.setY(robot.getY()-1);
            }
            if(!validateRobotInMars(robot,mars)){
                throw new MarsOverBoundException(robot,mars);
            }
        }else if(movement.equals(Movement.LEFT)){
            //Turn robot face with positive orientation 90 degree
            robot.setFace(FaceCalculator.getFaceWithRotation(currentRotation+POS_ROTATION));
        }else if(movement.equals(Movement.RIGHT)){
            //Turn robot face with negative orientation -90(270) degree
            robot.setFace(FaceCalculator.getFaceWithRotation(currentRotation+NEG_ROTATION));
        }

    }

    /**
     * Validate robot mars position
     * @param robot : Expected new position
     * @param mars : Current mars location
     * @return true on new position is valid false on new position not valid
     */
    private static boolean validateRobotInMars(Robot robot,Mars mars){
        return !(robot.getX() < 0 ||
                robot.getY() < 0 ||
                robot.getX() > mars.getX() ||
                robot.getY() > mars.getY());
    }
}
