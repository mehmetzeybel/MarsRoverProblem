package marsrover.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mehmet on 11.11.2021.
 * Robot have one point on two axis and one face
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Robot {
    private int x;
    private int y;
    private Face face;

    @Override
    public String toString(){
        return x + " " + y + " " + face;
    }
}
