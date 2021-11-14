package marsrover.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by mehmet on 11.11.2021.
 * Mars position have one position with two axis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class  Mars {
    private int x;
    private int y;
}
