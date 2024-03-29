package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquipable;
import backend.service.interfaces.IRemovable;
import backend.service.interfaces.ISpecialization;
import backend.service.interfaces.IUsable;
import backend.service.interfaces.IBackup;
import backend.service.memento.SpecializationCompositeMemento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SpecializationComposite implements ISpecialization, IBackup<SpecializationCompositeMemento> {

    private final List<ISpecialization> specializations;

    public SpecializationComposite() {
        this.specializations = new ArrayList<>();
    }

    public void add(ISpecialization specialization) {
        this.specializations.add(specialization);
    }

    public Optional<ISpecialization> getSpecialization(TypeItem type) {
        return this.specializations.stream().filter(s -> s.isType(type)).findFirst();
    }

    @Override
    public boolean isRemove() {
        return this.specializations.stream().allMatch(IRemovable::isRemove);
    }

    @Override
    public boolean isType(TypeItem type) {
        return this.specializations.stream().anyMatch(s -> s.isType(type));
    }

    @Override
    public String toString() {
        return """
                [
                    "%s"
                ]
                """.formatted(this.specializations.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

    @Override
    public SpecializationCompositeMemento save() {
        Boolean equip = null;
        var equipable = getSpecialization(TypeItem.EQUIPABLE);
        if (equipable.isPresent()) {
            equip = ((IEquipable) equipable.get()).isEquip();
        }

        Boolean enabled = null;
        var usable = getSpecialization(TypeItem.USABLE);
        if (usable.isPresent()) {
            enabled = ((IUsable) usable.get()).isEnabled();
        }

        return new SpecializationCompositeMemento(equip, enabled);
    }

    @Override
    public void restore(SpecializationCompositeMemento memento) {
        getSpecialization(TypeItem.EQUIPABLE)
                .ifPresent(s -> ((IEquipable) s).setEquip(memento.equip()));

        getSpecialization(TypeItem.USABLE)
                .ifPresent(s -> ((IUsable) s).setEnabled(memento.enabled()));
    }
}
