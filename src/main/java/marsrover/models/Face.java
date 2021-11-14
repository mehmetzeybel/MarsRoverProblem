package marsrover.models;

/**
 * Created by mehmet on 11.11.2021.
 * Robot face with description and rotation values
 */

public enum Face {

    N('N',90),
    E('E',0),
    W('W',180),
    S('S',270);

    private Character description;
    private Integer rotation;

    Face(Character description, Integer rotation){
        this.description = description;
        this.rotation = rotation;
    }

    public Integer getRotation(){
        return rotation;
    }

    public Character getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return String.valueOf(description);
    }


}
