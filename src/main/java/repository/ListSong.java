package repository;

public class ListSong {

    private String filename;

    public ListSong() {
        this.filename = "src\\main\\java\\repository\\audio\\scenery\\";
    }

    public String listSong(String nameScenery) {
        return filename +=
                switch (nameScenery) {
                    case "cais" -> "cais.wav";
                    case "farol" -> "farol.wav";
                    case "praia" -> "praia.wav";
                    case "vila" -> "vila.wav";
                    case "templo" -> "templo.wav";
                    default -> "";
                };
    }
}

