package psk.pip.project.szs.entity.administration;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.services.administration.employee.EmployeeType;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	private String name;
	private String surname;
	@Enumerated(EnumType.STRING)
	private EmployeeType type;
	@ManyToOne
	private EmployeeRank rank;
	private BigDecimal salary;
	@ManyToOne
	private DoctorSpecialization specialization;
	
	 //@Builder.Default 
	 private boolean inTeam;
	 

	private Employee(final Builder builder) {
		this.id = builder.id;
		this.user = builder.user;
		this.name = builder.name;
		this.surname = builder.surname;
		this.type = builder.type;
		this.rank = builder.rank;
		this.salary = builder.salary;
		this.specialization = builder.specialization;
		this.inTeam =builder.inTeam;
	}
	
	public static Builder builder(){
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private User user;
		private String name;
		private String surname;
		private EmployeeType type;
		private EmployeeRank rank;
		private BigDecimal salary;
		private DoctorSpecialization specialization;
		private boolean inTeam;

		Builder() {}
		
		
		public Builder id(Long id)
        {
            this.id = id;
            return this;
        }
		public Builder user(User user)
        {
            this.user = user;
            return this;
        }
		public Builder name(String name)
        {
            this.name = name;
            return this;
        }
		public Builder surname(String surname)
        {
            this.surname = surname;
            return this;
        }
		public Builder type(EmployeeType type)
        {
            this.type = type;
            return this;
        }
		public Builder rank(EmployeeRank rank)
        {
            this.rank = rank;
            return this;
        }
		public Builder salary(BigDecimal salary)
        {
            this.salary = salary;
            return this;
        }
		
		public Builder specialization(DoctorSpecialization specialization)
        {
            this.specialization = specialization;
            return this;
        }
		
		public Builder inTeam(boolean inTeam)
        {
            this.inTeam = false;
            return this;
        }
		
		public Employee build(){
			 return new Employee(this);
		}
		
		
		

	}

}
