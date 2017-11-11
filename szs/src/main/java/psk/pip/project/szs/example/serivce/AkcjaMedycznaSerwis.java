package psk.pip.project.szs.example.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.example.entity.PacjentPrzyklad;
import psk.pip.project.szs.example.entity.PodajLekPrzyklad;
import psk.pip.project.szs.example.repository.PacjetTestRepository;
import psk.pip.project.szs.example.repository.PodanieLekTestRepository;
import psk.pip.project.szs.example.serivce.dto.PodanieLekuPierdolyDTO;

@Service // oznaczamy te klase jako serwis, dzięki temu zadziała nam adnotacja
			// autowired
public class AkcjaMedycznaSerwis {

	@Autowired // dba o to aby ten inerfejs jednak mial metody i to nawet
				// dzialajace :D
	private PodanieLekTestRepository podanieLekuRepo;
	@Autowired
	private PacjetTestRepository pacjentRepo;

	public void podajLek(PodanieLekuPierdolyDTO dto) {
		PacjentPrzyklad pacjent = pacjentRepo.findOne(dto.getIdPacjent());
		String wykonujemyLogike = "Pacjent o id == " + pacjent.getId() + " i takich danych " + pacjent.getPacjetDane()
				+ " dostal lek " + dto.getCosTest();
		PodajLekPrzyklad podajLek = new PodajLekPrzyklad();
		podajLek.setCos(wykonujemyLogike);

		podanieLekuRepo.save(podajLek);
	}

	public PodajLekPrzyklad getPodajLek(Long id) {
		return podanieLekuRepo.findOne(id);
	}

}
