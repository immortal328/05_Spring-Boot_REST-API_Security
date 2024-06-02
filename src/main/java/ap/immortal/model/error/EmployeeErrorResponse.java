package ap.immortal.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeErrorResponse {
	private Integer status;
	private String message;
	private Long timeStamp;

}
