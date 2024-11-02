package tn.esprit.spring;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PisteServicesImplTest {


    @Autowired
    private PisteServicesImpl pisteServices;

     @Autowired
    private IPisteRepository pisteRepository; // Autowiring the repositor
    
    @BeforeEach
    public void setUp() {
        pisteServices = new PisteServicesImpl(pisteRepository); // Initialize the service with the repository
    }

    @Test
    @Order(0)
    public void testRetrieveAllPistes() {
        // Act
        List<Piste> pistes = pisteServices.retrieveAllPistes();

        // Assert
        assertNotNull(pistes, "The list of pistes should not be null.");
        assertTrue(pistes.size() > 0, "The list of pistes should contain at least one piste.");
    }


    @Test
    @Order(1)
    public void testAddPiste() {
        Piste piste = new Piste(1L, "Piste1", Color.BLACK, 200, 200);
        

        Piste createdPiste = pisteServices.addPiste(piste);
        assertNotNull(createdPiste);
        assertNotNull(createdPiste.getNumPiste()); // Ensure the ID is generated


    }


    @Test
    @Order(2)
    public void testRemovePiste() {
        // Arrange: Create and save a new Piste
        Piste piste = new Piste(null, "Piste1", Color.BLACK, 200, 200);
        Piste savedPiste = pisteRepository.save(piste); // Save the piste

        // Act: Remove the piste by its ID
        pisteServices.removePiste(savedPiste.getNumPiste());

        // Assert: Check that the piste no longer exists
        assertThrows(Exception.class, () -> {
            pisteRepository.findById(savedPiste.getNumPiste()).orElseThrow(() -> new Exception("Piste not found"));
        });


    }



    @Test
    @Order(3)
    public void testRetrievePiste_Exists() {
        // Arrange: Create and save a new Piste
        Piste piste = new Piste(5L, "Piste1", Color.BLACK, 200, 200);
        Piste savedPiste = pisteRepository.save(piste);

        // Act: Retrieve the piste by its ID
        Piste retrievedPiste = pisteServices.retrievePiste(savedPiste.getNumPiste());

        // Assert: Check that the retrieved piste is the same as the saved piste
        assertEquals(savedPiste.getNumPiste(), retrievedPiste.getNumPiste());
        assertEquals(savedPiste.getNamePiste(), retrievedPiste.getNamePiste());
    }


    @Test
    @Order(4)
    public void testRetrievePiste_NotExists() {
        // Act: Attempt to retrieve a non-existing Piste
        Piste retrievedPiste = pisteServices.retrievePiste(9999999L); // Assuming 999L does not exist

        // Assert: Check that the result is null
        assertNull(retrievedPiste);
    }


}
