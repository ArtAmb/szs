package psk.pip.project.szs.dto.administration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugTransferDTO {
	private Long roomId;
	private Long drugId;
	private Long drugsAmountToMove;
}
