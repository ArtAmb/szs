package psk.pip.project.szs.example.repository;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.example.entity.PacjentPrzyklad;

public interface PacjetTestRepository extends CrudRepository<PacjentPrzyklad, Long> { // to ze jest to interfejs który dziedziczy
																					// po crudreposotyory i który jest jeszcze
																					// pusty wynika z uzycia SprignData

}
