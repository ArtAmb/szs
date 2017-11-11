package psk.pip.project.szs.dto.administration;

import java.util.Collection;

import lombok.Data;

@Data
public class TeamDTO {
	private Long idTeam;
	private Collection<Long> memberIds; 
}
