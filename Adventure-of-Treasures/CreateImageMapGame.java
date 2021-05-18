import javax.swing.*;
import java.util.Objects;

public class CreateImageMapGame {

    public ImageIcon selectImage(String name) {
        return switch (name) {
            case "cais" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/cais.png")));
            case "farol" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/farol.png")));
            case "dentro do farol" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/dentro-do-farol.png")));
            case "praia" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/praia.png")));
            case "floresta" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/floresta.png")));
            case "vila" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/vila.png")));
            case "alojamento" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/alojamento.png")));
            case "templo" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/temploA.png")));
            case "topo do templo" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/topo-do-templo.png")));
            case "porao do templo" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/porao.png")));
            case "enfermaria" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/enfermaria.png")));
            case "barco" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/barco.png")));
            case "mapa" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/praiaM.png")));
            case "escada" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("cenario/temploF.png")));
            default -> null;
        };
    }
}
