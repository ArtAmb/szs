package psk.pip.project.szs.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psk.pip.project.szs.example.entity.PacjentPrzyklad;
import psk.pip.project.szs.example.repository.PacjetTestRepository;

@Component
public class KlasaInicjujacaBaze {

	@Autowired
	PacjetTestRepository repo;

	@PostConstruct
	void initTestDataBase() {

		repo.save(new PacjentPrzyklad("Magiczny Magik"));
		repo.save(new PacjentPrzyklad("Tajemnicza Tajemnica"));
		repo.save(new PacjentPrzyklad("Totemiczny totem"));
		repo.save(new PacjentPrzyklad("Lowelastyczny lowelas"));
		repo.save(new PacjentPrzyklad("Dinozaur"));
	}
}
