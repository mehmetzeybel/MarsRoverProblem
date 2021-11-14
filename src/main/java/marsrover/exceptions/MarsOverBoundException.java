package marsrover.exceptions;

import marsrover.models.Mars;
import marsrover.models.Robot;

/**
 * Created by mehmet on 13.11.2021.
 * Throws when new robot position out of the mars
 */
public class MarsOverBoundException extends Exception {

    public MarsOverBoundException(Robot robot, Mars mars){
        super("Mars OverBound exceed : " + mars + " Robot Position : " + robot);
    }

}
