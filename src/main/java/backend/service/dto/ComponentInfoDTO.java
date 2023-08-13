package backend.service.dto;

import backend.controller.interfaces.IComponentInfo;

import java.awt.*;

public record ComponentInfoDTO(String name, String image, Point point) implements IComponentInfo {
}
