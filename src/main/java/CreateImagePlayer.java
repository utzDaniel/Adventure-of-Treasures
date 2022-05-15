import javax.swing.*;
import java.util.Objects;

public class CreateImagePlayer {

    public ImageIcon selectImage(String direction) {
        return switch (direction) {
            case "oeste" -> new ImageIcon("src/main/java/player/paradoParaEsquerda.png");
            case "norte" -> new ImageIcon("src/main/java/player/paradoParaCima.png");
            case "leste" -> new ImageIcon("src/main/java/player/paradoParaDireita.png");
            case "sul" -> new ImageIcon("src/main/java/player/paradoParaBaixo.png");
            default -> new ImageIcon("src/main/java/player/paradoParaBaixo.png");
        };
    }
}
