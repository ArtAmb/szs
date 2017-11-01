package psk.pip.project.szs.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PacjentPrzyklad {

	@Id // oznaczamy ze to pole jest kluczem głównym
	@GeneratedValue(strategy = GenerationType.AUTO) // pamietamy zeby nie
													// ustaiwac przy zapisanie
													// do bazy wartosci klucza,
													// dzieki tej adnotacji
													// aplikacaj zadba za na o
													// to
	private Long id;
	private String pacjetDane;

	public PacjentPrzyklad(String npDane) {
		pacjetDane = npDane;
	}
}
