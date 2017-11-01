package psk.pip.project.szs.example.serivce.dto;

import lombok.Data;

@Data
public class PodanieLekuPierdolyDTO {
	private Long idPacjent;
	private String cosTest;// pierdoly Ktore Wykilka Klient I Z Ktorych
							// Zbudujemy PodanieLeku;
}
