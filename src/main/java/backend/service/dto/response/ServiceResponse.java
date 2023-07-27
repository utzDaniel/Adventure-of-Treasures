package backend.service.dto.response;

import backend.controller.enums.TypeMessage;
import backend.controller.interfaces.IServiceResponse;

public record ServiceResponse(TypeMessage message, Object object) implements IServiceResponse {
}
