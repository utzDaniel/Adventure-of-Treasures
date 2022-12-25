package repository;

import exception.CreateImageException;

import javax.swing.*;

public class CreateImagePlayer {

    public ImageIcon selectImage(String direction) {
        String filename = "src\\main\\java\\repository\\player\\" +
                switch (direction) {
                    case "oeste" -> "paradoParaEsquerda.png";
                    case "norte" -> "paradoParaCima.png";
                    case "leste" -> "paradoParaDireita.png";
                    case "sul" -> "paradoParaBaixo.png";
                    default -> throw new CreateImageException("Imagem do player não encontrada");
                };
        return new ImageIcon(filename);
    }
}

