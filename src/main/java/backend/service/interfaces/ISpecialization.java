package backend.service.interfaces;

import backend.service.enums.TypeItem;

public interface ISpecialization {


    boolean isType(TypeItem type);

    boolean isRemovable();
}
