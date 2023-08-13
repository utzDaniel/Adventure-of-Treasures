package frontend.model.view;

import backend.controller.interfaces.ISpecialization;

public record Specialization(String text, boolean enabled) implements ISpecialization {

}
