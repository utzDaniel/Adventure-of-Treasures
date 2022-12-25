package repository;

import exception.CreateImageException;

import javax.swing.*;

public class CreateImageInventory {

    public ImageIcon selectImage(String name) {
        String filename = "src\\main\\java\\repository\\inventario\\" +
                switch (name) {
                    case "icons" -> "icons.png";
                    case "top" -> "top.png";
                    case "player" -> "player.png";
                    case "usar" -> "usar.png";
                    case "equipar" -> "equipar.png";
                    case "combinar" -> "combinar.png";
                    case "remover" -> "remover.png";
                    default -> throw new CreateImageException("Imagem do inventario não encontrada");
                };
        return new ImageIcon(filename);
    }
}

