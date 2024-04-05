package backend.service.model;

import backend.controller.enums.TypeMessage;
import backend.repository.interfaces.IEntity;
import backend.repository.interfaces.IItemEntity;
import backend.service.enums.TypeItem;
import backend.service.interfaces.*;
import backend.service.interfaces.IBackup;
import backend.service.memento.ItemMemento;

import java.util.List;
import java.util.Optional;

public final class Item implements IObservable, IImage, IEntity, IBackup<ItemMemento> {

    private final IItemEntity entity;
    private final SpecializationComposite specialization;
    private final List<IObserver> observers;
    private ICoordinate coordinate;

    public Item(IItemEntity entity, SpecializationComposite specialization, List<IObserver> observers) {
        this.entity = entity;
        this.specialization = specialization;
        this.coordinate = ICoordinate.getInstance(entity.positionX(), entity.positionY());
        this.observers = observers;
    }

    @Override
    public int id() {
        return this.entity.id();
    }

    public int getId() {
        return this.entity.id();
    }

    public String getName() {
        return this.entity.name();
    }

    public String getDescription() {
        return this.entity.description();
    }

    public int getWeight() {
        return this.entity.weight();
    }

    @Override
    public String getImage() {
        return this.entity.image();
    }

    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setCoordinate(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
    }

    public boolean isType(TypeItem type) {
        return this.specialization.isType(type);
    }

    public Optional<ISpecialization> getSpecialization(TypeItem type) {
        return this.specialization.getSpecialization(type);
    }

    public TypeMessage isRemove() {
        if (this.getSpecialization(TypeItem.MISSION).isPresent())
            return TypeMessage.REMOVE_ITEM_ERROR;
        var spec = this.getSpecialization(TypeItem.EQUIPPABLE);
        if (spec.isPresent() && ((IEquippable) spec.get()).isEquip())
            return TypeMessage.REMOVE_ITEM_ERROR_EQUIP;
        return TypeMessage.REMOVE_ITEM;
    }

    @Override
    public void warn() {
        this.observers.forEach(IObserver::update);
    }

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "name": "%s",
                    "description": "%s",
                    "weight": %d,
                    "coordinate": %s,
                    "image": "%s",
                    "specializations": %s
                }
                """.formatted(this.entity.id(), this.entity.name(), this.entity.description(), this.entity.weight(),
                this.coordinate.toString(), this.entity.image(), this.specialization);
    }

    @Override
    public ItemMemento save() {
        var memento = this.specialization.save();
        return new ItemMemento(this.entity.id(), this.coordinate.x(), this.coordinate.y(), memento);
    }

    @Override
    public void restore(ItemMemento memento) {
        this.coordinate = ICoordinate.getInstance(memento.x(), memento.y());
        this.specialization.restore(memento.specializationCompositeMemento());
    }
}
