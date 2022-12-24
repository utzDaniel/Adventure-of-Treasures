package repository;

import exception.CreateImageException;

import javax.swing.*;

public class CreateImageMapGame {

    public ImageIcon selectImage(String name) {
        String filename = "src\\main\\java\\repository\\map\\cenario\\";
        return switch (name) {
            case "cais" -> new ImageIcon(filename+"cais.png");
            case "farol" -> new ImageIcon(filename+"farol.png");
            case "dentro do farol" -> new ImageIcon(filename+"dentro-do-farol.png");
            case "praia" -> new ImageIcon(filename+"praia.png");
            case "floresta" -> new ImageIcon(filename+"floresta.png");
            case "vila" -> new ImageIcon(filename+"vila.png");
            case "alojamento" -> new ImageIcon(filename+"alojamento.png");
            case "templo" -> new ImageIcon(filename+"temploA.png");
            case "topo do templo" -> new ImageIcon(filename+"topo-do-templo.png");
            case "porao do templo" -> new ImageIcon(filename+"porao.png");
            case "enfermaria" -> new ImageIcon(filename+"enfermaria.png");
            case "barco" -> new ImageIcon(filename+"barco.png");
            case "mapa" -> new ImageIcon(filename+"praiaM.png");
            case "escada" -> new ImageIcon(filename+"temploF.png");
            default -> throw new CreateImageException("Imagem do map não encontrada");
        };
    }
}
