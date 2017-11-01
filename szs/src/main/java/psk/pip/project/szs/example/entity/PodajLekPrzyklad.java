package psk.pip.project.szs.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data // lombok <3
@Entity // to nam zapewnia automatycznego create table w bazie
public class PodajLekPrzyklad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String cos;

}
