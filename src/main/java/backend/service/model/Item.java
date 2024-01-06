package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.ISpecialization;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class Item {

    private final int id;
    private final String name;
    private final String description;
    private final int weight;
    private final String image;
    private final Set<ISpecialization> specializations;
    private ICoordinate coordinate;

    Item(int id, String name, String description, int weight, String image, Set<ISpecialization> specializations, ICoordinate coordinate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.image = image;
        this.specializations = specializations;
        this.coordinate = coordinate;
    }

    public String getImage() {
        return this.image;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean isType(TypeItem type) {
        for (ISpecialization specialization : this.specializations) {
            if (specialization.isType(type)) return true;
        }
        return false;
    }

    public Optional<ISpecialization> getSpecialization(TypeItem type) {
        return this.specializations.stream().filter(s -> s.isType(type)).findFirst();
    }

    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setCoordinate(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
    }

    public boolean isRemovable() {
        var remove = true;
        for (ISpecialization specialization : this.specializations) {
            remove = remove && specialization.isRemovable();
        }
        return remove;
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
                    "specializations": "%s"
                }
                """.formatted(this.id, this.name, this.description, this.weight, this.coordinate.toString(),
                this.image, this.specializations.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

}
