import javax.swing.*;
import java.util.Objects;

public class CreateImageItem {

    public ImageIcon selectImage(String name) {
        return switch (name) {
            case "chave" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/chave.png")));
            case "escada" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/escada.png")));
            case "faca" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/faca.png")));
            case "frasco" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/frasco.png")));
            case "livro" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/livro.png")));
            case "madeira" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/madeira.png")));
            case "madeiras" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/madeiras.png")));
            case "mapa" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/mapa.png")));
            case "martelo" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/martelo.png")));
            case "mochila" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/mochila.png")));
            case "pa" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/pa.png")));
            case "papel" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/papel.png")));
            case "pederneira" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/pederneira.png")));
            case "pregos" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/pregos.png")));
            case "tesouro" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/tesouro.png")));
            case "tocha" -> new ImageIcon(Objects.requireNonNull(getClass().getResource("item/tocha.png")));
            default -> null;
        };
    }
}
