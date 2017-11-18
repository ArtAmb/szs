package psk.pip.project.szs.repository.patient;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.patient.ReferralType;

public interface ReferralTypeRepository extends CrudRepository<ReferralType, Long> {
}
