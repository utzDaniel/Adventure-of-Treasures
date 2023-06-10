package backend.controller.domain;

import backend.controller.interfaces.ICoordinate;
import backend.exception.ItemUsableException;
import backend.service.component.Equip;
import backend.service.component.Unequip;
import backend.service.component.Use;
import backend.service.interfaces.*;
import backend.service.model.Player;

import javax.swing.*;
import java.util.List;

public final class ItemDomain{

    private String name;
    private String description;
    private int weight;
    private ICoordinate coordinate;
    private ImageIcon icon;
    private boolean removable;
    private boolean visible;

    private int combine;
    private String effect;

    private String mapGame;
    private boolean equipped;

    private String localUse;

    private String specialization;

    private ItemDomain() {
    }

    public ItemDomain(String name, String description, int weight, ICoordinate coordinate, ImageIcon icon,
                      boolean removable, boolean visible, int combine, String effect, String mapGame, boolean equipped,
                      String localUse, String specialization) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.coordinate = coordinate;
        this.icon = icon;
        this.removable = removable;
        this.visible = visible;
        this.combine = combine;
        this.effect = effect;
        this.mapGame = mapGame;
        this.equipped = equipped;
        this.localUse = localUse;
        this.specialization = specialization;
    }

//    @Override
//    public ICoordinate getLocation() {
//        return ICoordinate.getInstance(this.coordinate);
//    }
//
//    @Override
//    public void setLocation(ICoordinate coordinate) {
//        this.coordinate = ICoordinate.getInstance(coordinate);
//    }
//
//    @Override
//    public ImageIcon getIcon() {
//        return this.icon;
//    }
//
//    @Override
//    public String getName() {
//        return this.name;
//    }
//
//    @Override
//    public String getDescription() {
//        return this.description;
//    }
//
//    @Override
//    public int getWeight() {
//        return this.weight;
//    }
//
//    @Override
//    public boolean isVisible() {
//        return this.visible;
//    }
//
//    @Override
//    public boolean isRemovable() {
//        return this.removable;
//    }
//
//    @Override
//    public boolean isInvisible() {
//        return !this.visible;
//    }
//
//    @Override
//    public void setVisible(boolean visible) {
//        this.visible = visible;
//    }
//
//    @Override
//    public String getEffect() {
//        return this.effect;
//    }
//
//    @Override
//    public boolean action() {
//        if (this.specialization.contains("ItemUsable"))
//            return this.use();
//        else if (this.specialization.contains("ItemEquipable")) {
//            return this.isEquipped() ? this.unequip() : this.equip();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean action(List<ICombinableDomain> itensCombination) {
//        if (this.specialization.contains("ItemCombinable"))
//            return this.combination(itensCombination);
//        return action();
//    }
//
//    @Override
//    public boolean isEquipped() {
//        return this.equipped;
//    }
//
//    @Override
//    public boolean equip() {
//        this.equipped = new Equip(this).run();
//        return this.equipped;
//    }
//
//    @Override
//    public boolean unequip() {
//        this.equipped = !new Unequip(this).run();
//        return !this.equipped;
//    }
//
//    @Override
//    public int getCombine() {
//        return this.combine;
//    }
//
//    @Override
//    public String getMapGame() {
//        return this.mapGame;
//    }
//
//    @Override
//    public String getLocalUse() {
//        return this.localUse;
//    }
//
//    @Override
//    public boolean use() {
//        try {
//            new Use(this).run();
//            Player.getInstance().getInventory().removeItem(this);
//            return true;
//        } catch (ItemUsableException e) {
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
//
//    @Override
//    public String getSpecialization() {
//        return this.specialization;
//    }
}
