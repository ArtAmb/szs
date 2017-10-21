package psk.pip.project.szs.dto.registration;

import lombok.Data;

@Data
public class ModifyUserDTO {

	private String oldPassword;
	private String newPassword;

}
