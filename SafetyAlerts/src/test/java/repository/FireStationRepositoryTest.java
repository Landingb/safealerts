package repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.safetynet.alerts.repository.FireStationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import com.safetynet.alerts.model.FireStation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class FireStationRepositoryTest {

    @Autowired
    FireStationRepository fireStationRepository;

    @Test
    public void saveOneFireStationTest() {

        // GIVEN
        FireStation fireStationTest = new FireStation("Adressadd", "stationadd");

        // WHEN
        FireStation fireStationAddedd = fireStationRepository.addFirestation(fireStationTest);

        // THEN
        assertThat(fireStationAddedd.getAddress()).isEqualTo(fireStationTest.getAddress());
        assertThat(fireStationAddedd.getStation()).isEqualTo(fireStationTest.getStation());

    }

    @Test
    public void updateOneExistingFireStationTest() {

        // GIVEN
        FireStation fireStationTest = new FireStation("adresstest1", "3");
        // WHEN
        FireStation fireStationUpdated = fireStationRepository.updateFirestation(fireStationTest);

        // THEN
        assertThat(fireStationUpdated.getAddress()).isEqualTo(fireStationTest.getAddress());
        assertThat(fireStationUpdated.getStation()).isEqualTo(fireStationTest.getStation());

    }

    @Test
    public void updateOneNotExistingFireStationTest() {

        // GIVEN
        FireStation fireStationTestNotExist = new FireStation("NotExist", "5");
        // WHEN
        FireStation fireStationUpdated = fireStationRepository.updateFirestation(fireStationTestNotExist);

        // THEN
        assertThat(fireStationUpdated).isNull();

    }

    @Test
    public void deleteOneExistingFireStationByAdressTest() {

        // GIVEN
        FireStation fireStationDelete = new FireStation("delete", "");

        // WHEN

        // THEN
        assertThat(fireStationRepository.deleteFirestation(fireStationDelete).size()).isEqualTo(1);

    }

    @Test
    public void deleteOneExistingFireStationByStationTest() {

        // GIVEN
        FireStation fireStationDelete = new FireStation("", "delete");

        // WHEN

        // THEN
        assertThat(fireStationRepository.deleteFirestation(fireStationDelete).size()).isEqualTo(1);

    }

    @Test
    public void deleteOneExistingFireStationByAdressAndStationTest() {

        // GIVEN
        FireStation fireStationDelete = new FireStation("delete2", "delete2");

        // WHEN

        // THEN
        assertThat(fireStationRepository.deleteFirestation(fireStationDelete).size()).isEqualTo(1);

    }

    @Test
    public void getStationByAddressExistingTest() {

        assertThat(fireStationRepository.getStationByAddress("adresstest1")).isEqualTo("1");
    }

    @Test
    public void getStationByAddressNotExistingTest() {

        assertThat(fireStationRepository.getStationByAddress("adresstestnotexist")).isEqualTo("Not Found");
    }

    @Test
    public void getAddressByStationExistingTest() {

        assertThat(fireStationRepository.getadressFireStationByStation("1").size()).isEqualTo(1);
    }

    @Test
    public void getAddressByStationNotExistingTest() {

        assertThat(fireStationRepository.getadressFireStationByStation("5").size()).isEqualTo(0);
    }

}