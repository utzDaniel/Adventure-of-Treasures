package backend.service.dto.response;

import backend.controller.interfaces.IActionResponse;
import backend.controller.interfaces.IComponentInfo;

import java.util.List;

public record ActionResponse(List<IComponentInfo> components, String song) implements IActionResponse {

}
