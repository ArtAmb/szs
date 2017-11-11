package psk.pip.project.szs.example.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.example.entity.PodajLekPrzyklad;
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
	
	@GetMapping(value = "/test/akcje/podajLek/{id}") // w {} podajemy nazwe parametru, ktory bedzie w metodzie, pamietamy ze musimy ten parametr oznaczy jeszcze adnotacja path variable
	public PodajLekPrzyklad podajLek(@PathVariable Long id) { // PathVariable daje mozliwosc przekazywania parametrow w url-u -> bedzie wykorzystywane dla get, del, i roznych updatów
		return akcjaMedycznaSerwis.getPodajLek(id);
	}

}
