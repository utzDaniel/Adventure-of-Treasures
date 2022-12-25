package repository;

import exception.CreateImageException;

import javax.swing.*;

public class CreateImageMapGame {

    public ImageIcon selectImage(String name) {
        String filename = "src\\main\\java\\repository\\map\\cenario\\" +
                switch (name) {
                    case "cais" -> "cais.png";
                    case "farol" -> "farol.png";
                    case "dentro do farol" -> "dentro-do-farol.png";
                    case "praia" -> "praia.png";
                    case "floresta" -> "floresta.png";
                    case "vila" -> "vila.png";
                    case "alojamento" -> "alojamento.png";
                    case "templo" -> "temploA.png";
                    case "topo do templo" -> "topo-do-templo.png";
                    case "porao do templo" -> "porao.png";
                    case "enfermaria" -> "enfermaria.png";
                    case "barco" -> "barco.png";
                    case "mapa" -> "praiaM.png";
                    case "escada" -> "temploF.png";
                    default -> throw new CreateImageException("Imagem do map não encontrada");
                };
        return new ImageIcon(filename);
    }
}
