package repository;

import exception.CreateImageException;

import javax.swing.*;

public class CreateImageItem {

    public ImageIcon selectImage(String name) {
        String filename = "src\\main\\java\\repository\\item\\" +
                switch (name) {
                    case "chave" -> "chave.png";
                    case "escada" -> "escada.png";
                    case "faca" -> "faca.png";
                    case "frasco" -> "frasco.png";
                    case "livro" -> "livro.png";
                    case "madeira" -> "madeira.png";
                    case "madeiras" -> "madeiras.png";
                    case "mapa" -> "mapa.png";
                    case "martelo" -> "martelo.png";
                    case "mochila" -> "mochila.png";
                    case "pa" -> "pa.png";
                    case "papel" -> "papel.png";
                    case "pederneira" -> "pederneira.png";
                    case "pregos" -> "pregos.png";
                    case "tesouro" -> "tesouro.png";
                    case "tocha" -> "tocha.png";
                    default -> throw new CreateImageException("Imagem do item não encontrada");
                };
        return new ImageIcon(filename);
    }
}
