package psk.pip.project.szs.repository.storage;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.storage.HospitalRoom;

public interface RoomRepository extends CrudRepository<HospitalRoom, Long> {

}
