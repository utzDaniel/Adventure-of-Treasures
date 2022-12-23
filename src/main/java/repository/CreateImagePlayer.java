package repository;

import javax.swing.*;

public class CreateImagePlayer {

    public ImageIcon selectImage(String direction) {
        String filename = "src\\main\\java\\repository\\player\\";
        return switch (direction) {
            case "oeste" -> new ImageIcon(filename+"paradoParaEsquerda.png");
            case "norte" -> new ImageIcon(filename+"paradoParaCima.png");
            case "leste" -> new ImageIcon(filename+"paradoParaDireita.png");
            case "sul" -> new ImageIcon(filename+"paradoParaBaixo.png");
            default -> null;//TODO criar uma exception
        };
    }
}
