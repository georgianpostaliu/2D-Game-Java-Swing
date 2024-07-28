package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Uranium extends SuperObject{

    public OBJ_Uranium () {

        name = "Uranium";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/uranium.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
