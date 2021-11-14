package marsrover.controller;

import marsrover.models.Face;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by mehmet on 12.11.2021.
 * Calculate the faces with given values
 */
public class FaceCalculator {

    /**
     * Get Face with given description(E,N,W,S)
     * @param description
     * @return Face
     */
    public static Face getFaceWithDescription(Character description){
        Optional<Face> face = Arrays.stream(Face.values()).filter(element->element.getDescription().equals(description)).findFirst();
        if(face.isPresent()){
            return face.get();
        }else{
            throw new IllegalArgumentException("Face cannot represented like that '" + description +"'");
        }
    }

    /**
     * Get Face with given rotation(0,90,270,360)
     * @param expectedRotation
     * @return Face
     */
    public static Face getFaceWithRotation(Integer expectedRotation){
        Optional<Face> face = Arrays.stream(Face.values()).filter(element->element.getRotation().equals((expectedRotation)%360)).findFirst();
        if(face.isPresent()){
            return face.get();
        }else{
            throw new IllegalArgumentException("Face cannot represented like that '" + expectedRotation +"'");
        }
    }
}
