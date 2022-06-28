package ted.talks.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {

	@NonNull
	private String errorMessage;

}
