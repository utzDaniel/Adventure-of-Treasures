package repository;

import exception.CreateImageException;

import javax.swing.*;

public class CreateImageItem {

    public ImageIcon selectImage(String name) {
        String filename = "src\\main\\java\\repository\\item\\";
        return switch (name) {
            case "chave" -> new ImageIcon(filename+"chave.png");
            case "escada" -> new ImageIcon(filename+"escada.png");
            case "faca" -> new ImageIcon(filename+"faca.png");
            case "frasco" -> new ImageIcon(filename+"frasco.png");
            case "livro" -> new ImageIcon(filename+"livro.png");
            case "madeira" -> new ImageIcon(filename+"madeira.png");
            case "madeiras" -> new ImageIcon(filename+"madeiras.png");
            case "mapa" -> new ImageIcon(filename+"mapa.png");
            case "martelo" -> new ImageIcon(filename+"martelo.png");
            case "mochila" -> new ImageIcon(filename+"mochila.png");
            case "pa" -> new ImageIcon(filename+"pa.png");
            case "papel" -> new ImageIcon(filename+"papel.png");
            case "pederneira" -> new ImageIcon(filename+"pederneira.png");
            case "pregos" -> new ImageIcon(filename+"pregos.png");
            case "tesouro" -> new ImageIcon(filename+"tesouro.png");
            case "tocha" -> new ImageIcon(filename+"tocha.png");
            default -> throw new CreateImageException("Imagem do item não encontrada");
        };
    }
}
