package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;




@ExtendWith(SpringExtension.class)
@SpringBootTest
class GestionStationSkiApplicationTests {

	IPisteRepository pisteRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Order(0)
	public void addPisteTest(){
		Piste piste = new Piste();

		piste.setLength(5);

		piste = pisteRepository.save(piste);

		Assertions.assertNotNull(piste);






	}

}
