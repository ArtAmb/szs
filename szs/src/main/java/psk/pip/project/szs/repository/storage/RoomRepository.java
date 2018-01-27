package psk.pip.project.szs.repository.storage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import psk.pip.project.szs.entity.storage.HospitalRoom;

public interface RoomRepository extends JpaRepository<HospitalRoom, Long> {
	List<HospitalRoom> findAll();

	@Query(value = "select r from HospitalRoom r where (r.roomName like %?1% or r.id like %?1%) and r.id IS NOT 1")
	List<HospitalRoom> findByQuery(String query);
}
