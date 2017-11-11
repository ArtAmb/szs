package psk.pip.project.szs.example.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.example.serivce.AkcjaMedycznaSerwis;
import psk.pip.project.szs.example.serivce.dto.PodanieLekuPierdolyDTO;

@RestController // informujemy aplikacje ze ta klasa jest kontrolerem
public class AkcjaMedycznaKontroler {

	@Autowired // dzieki temu nie musimy pisac = new AkcjaMedycznaSerwis(); jest
				// to pewien standard pisania w springu
	private AkcjaMedycznaSerwis akcjaMedycznaSerwis;

	@PostMapping(value = "/test/akcje/podajLek") // w totroialu pisalem www.szs.pl tutaj to
													// bedzie localhost:8080, nie piszemy tego
	public void podajLek(@RequestBody PodanieLekuPierdolyDTO dto) { // RequestBody daje mozliwosc przesalania postem obiektow
		akcjaMedycznaSerwis.podajLek(dto);
	}

}
