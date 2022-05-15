import javax.swing.*;
import java.util.Objects;

public class CreateImageMapGame {

    public ImageIcon selectImage(String name) {
        return switch (name) {
            case "cais" -> new ImageIcon("src/main/java/cenario/cais.png");
            case "farol" -> new ImageIcon("src/main/java/cenario/farol.png");
            case "dentro do farol" -> new ImageIcon("src/main/java/cenario/dentro-do-farol.png");
            case "praia" -> new ImageIcon("src/main/java/cenario/praia.png");
            case "floresta" -> new ImageIcon("src/main/java/cenario/floresta.png");
            case "vila" -> new ImageIcon("src/main/java/cenario/vila.png");
            case "alojamento" -> new ImageIcon("src/main/java/cenario/alojamento.png");
            case "templo" -> new ImageIcon("src/main/java/cenario/temploA.png");
            case "topo do templo" -> new ImageIcon("src/main/java/cenario/topo-do-templo.png");
            case "porao do templo" -> new ImageIcon("src/main/java/cenario/porao.png");
            case "enfermaria" -> new ImageIcon("src/main/java/cenario/enfermaria.png");
            case "barco" -> new ImageIcon("src/main/java/cenario/barco.png");
            case "mapa" -> new ImageIcon("src/main/java/cenario/praiaM.png");
            case "escada" -> new ImageIcon("src/main/java/cenario/temploF.png");
            default -> null;
        };
    }
}
