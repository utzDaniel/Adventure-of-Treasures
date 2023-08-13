package backend.service.dto;

import backend.controller.interfaces.ISpecialization;

public record SpecializationDTO(String text, boolean enabled) implements ISpecialization {
}
