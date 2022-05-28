public class ListSong {

    private String filename;
    public ListSong() {
        this.filename = "C:\\Users\\Daniel\\Documents\\000 -projeto v3\\Adventure-of-Treasures\\src\\main\\java\\audio\\scenery\\";
    }

    public String listSong(String nameScenery) {
        if(nameScenery.contains("cais")){
            filename += "cais.wav";
        }else if(nameScenery.contains("farol")){
            filename += "farol.wav";
        }else if(nameScenery.contains("praia")){
            filename += "praia.wav";
        }else if(nameScenery.contains("vila")){
            filename += "vila.wav";
        }else if(nameScenery.contains("templo")){
            filename += "templo.wav";
        }else {
            filename = null;
        }
        return filename;
    }
}

