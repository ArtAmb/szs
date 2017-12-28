package psk.pip.project.szs.entity.medicine;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Measurement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String value;
	private String unit; // TODO <- osobna tabela do konfiguracji

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "measurement")
	private Collection<Measurement> elements;
}
