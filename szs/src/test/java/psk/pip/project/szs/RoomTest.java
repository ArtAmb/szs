package psk.pip.project.szs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.medicine.DrugName;
import psk.pip.project.szs.entity.medicine.Unit;
import psk.pip.project.szs.entity.storage.HospitalRoom;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomTest {

	@Test
	public void testRoomMethods() {
		DrugName name = DrugName.builder().id(1l).name("testLek").build();
		DrugName name2 = DrugName.builder().id(1l).name("testLek2").build();
		Unit unit = Unit.builder().name("testUnit").id(1l).build();

		int amount = 100;
		int greaterAmount = 200;
		int smallerAmount = 13;
		Drug drug = Drug.builder().dosage(10).name(name).unit(unit).amount(amount).build();
		Drug sameDrugWithSmallerAmount = Drug.builder().dosage(10).name(name).unit(unit).amount(smallerAmount).build();
		Drug sameDrugWithGreaterAmount = Drug.builder().dosage(10).name(name).unit(unit).amount(greaterAmount).build();
		Drug otherDrugToAdd = Drug.builder().dosage(10).name(name2).unit(unit).amount(greaterAmount).build();

		HospitalRoom room = HospitalRoom.builder().roomName("testRoom").build();

		room.addDrug(drug);
		assertThat(room.getDrugs().size()).as("Try to add new drug to empty room").isEqualTo(1);

		room.addDrug(sameDrugWithSmallerAmount);
		assertThat(room.getDrugs().size()).as("Try to add again same drug").isEqualTo(1);
		assertThat(room.getDrugs().iterator().next().getAmount())
				.as("Try to add again same drug-> check if amount is OK").isEqualTo(amount + smallerAmount);

		try {
			room.deleteDrugs(otherDrugToAdd);
			failBecauseExceptionWasNotThrown(RuntimeException.class);
		} catch (RuntimeException e) {
			assertThat(e).hasMessageContaining(otherDrugToAdd.toString());
		}

		room.addDrug(otherDrugToAdd);
		assertThat(room.getDrugs().size()).as("Try to other drug").isEqualTo(2);

		room.deleteDrugs(sameDrugWithSmallerAmount);
		assertThat(room.getDrugs().size()).as("Try to delete existing drug").isEqualTo(2);
		assertThat(room.getDrugs().iterator().next().getAmount()).as("Try to delete existing drug - > is amount ok")
				.isEqualTo(amount);
		try {
			room.deleteDrugs(sameDrugWithGreaterAmount);
			failBecauseExceptionWasNotThrown(RuntimeException.class);
		} catch (RuntimeException e) {
			assertThat(e).hasMessageContaining(sameDrugWithGreaterAmount.toString());
		}
	}
}
