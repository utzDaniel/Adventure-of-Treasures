import javax.swing.*;
import java.util.Objects;

public class CreateImagePlayer {

    public ImageIcon selectImage(String direction) {
        ImageIcon imagemIcon = null;
        if (direction.equals("oeste")) {
            imagemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("player/paradoParaEsquerda.png"))); ;
        } else if (direction.equals("norte")) {
            imagemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("player/paradoParaCima.png")));
        } else if (direction.equals("leste")) {
            imagemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("player/paradoParaDireita.png")));
        } else if (direction.equals("sul")){
            imagemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("player/paradoParaBaixo.png")));
        }
        return imagemIcon;
    }
}
