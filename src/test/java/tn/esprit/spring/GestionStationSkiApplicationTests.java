package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GestionStationSkiApplicationTests {

    @Autowired
    IPisteRepository pisteRepository;

    @Test
    void contextLoads() {
        // This test checks if the Spring context loads successfully
    }

    @Test
    @Order(0)
    public void addPisteTest() {
        Piste piste = new Piste();
        piste.setLength(5);  

        // Save the piste entity and assert it's not null
        Piste savedPiste = pisteRepository.save(piste);
        Assertions.assertNotNull(savedPiste);
        Assertions.assertNotNull(savedPiste.getNumPiste());  // Ensure the ID is generated
    }
}
