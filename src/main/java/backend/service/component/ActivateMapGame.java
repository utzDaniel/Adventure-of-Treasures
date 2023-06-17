package backend.service.component;

public final class ActivateMapGame {

    //TODO resolver isso depois - Servico
    public boolean run(String nameItem) {
        boolean activate = false;
//        try {
//            if (nameItem.equals("tocha")) {
//                MapGame templo = RepositoryFactory.getRepositoryMapGame().get("templo");
//                Door openDoor = templo.getDoor(ICoordinate.getInstance(90, 240)).get();
//                openDoor.setOpen(!openDoor.isOpen());
//                activate = true;
//            } else if (nameItem.equals("mapa")) {
//                MapGame praia = RepositoryFactory.getRepositoryMapGame().get("praia");
//                praia.setIcon(new ImageIcon("src/main/resources/map/praiaM.png"));
//            } else if (nameItem.equals("chave")) {
//                MapGame praia = RepositoryFactory.getRepositoryMapGame().get("praia");
//                praia.setIcon(new ImageIcon("src/main/resources/map/praia.png"));
//            } else if (nameItem.equals("escada")) {
//                MapGame templo = RepositoryFactory.getRepositoryMapGame().get("templo");
//                templo.setIcon(new ImageIcon("src/main/resources/map/temploF.png"));
//            }
//        } catch (Exception e) {
//            throw new MoveException("Direção invalida!");
//        }
        return activate;
    }
}
