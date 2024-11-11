package tn.esprit.spring;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;

import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Color;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PisteServicesImplMockitoTest {
 
    private Piste pisteSaved; // The entity you are going to test with


    private Long pisteIdDeleted; // The ID to test with


    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;


    @BeforeEach
    public void setUp() {
        // Initialize the Piste entity with appropriate values
        pisteSaved = new Piste(1L, "Piste1", Color.BLACK, 200, 200);
        pisteIdDeleted = 1L;

        MockitoAnnotations.openMocks(this); // Initialize mocks

 
 
 
    }
    @Test
    @Order(0)
    public void testRetrieveAllPistes() {
        // Arrange
        List<Piste> mockPistes = new ArrayList<>();
        Piste piste1 = new Piste(1L,"Piste1",Color.BLACK,200,200);
        mockPistes.add(piste1); // Create a mock Piste object

        when(pisteRepository.findAll()).thenReturn(mockPistes); // Mock the repository's behavior

        // Act
        List<Piste> pistes = pisteServices.retrieveAllPistes();

        // Assert
        assertEquals(1, pistes.size(), "The size of the returned list should be 1.");
        assertEquals("Piste1", pistes.get(0).getNamePiste(), "The name of the first piste should be 'Piste1'.");
    }


    @Test
    @Order(1)
    public void testAddPiste() {
        // Arrange: Define the behavior of the mocked repository
        when(pisteRepository.save(pisteSaved)).thenReturn(pisteSaved);

        // Act: Call the method you want to test
        Piste result = pisteServices.addPiste(pisteSaved);

        // Assert: Verify the result and interactions
        assertEquals(pisteSaved, result); // Check if the returned piste is the same as the one we saved
    }





    @Test
    @Order(2)
    public void testRemovePiste() {
        // Arrange: Define the behavior of the mocked repository
        doNothing().when(pisteRepository).deleteById(pisteIdDeleted);

        // Act & Assert: Check that no exception is thrown when removing a piste
        assertDoesNotThrow(() -> {
            pisteServices.removePiste(pisteIdDeleted);
        });

        // Verify that the deleteById method was called with the correct ID
        verify(pisteRepository).deleteById(pisteIdDeleted);


    }



    @Test
    @Order(3)
    public void testRetrievePiste_Exists() {
        // Arrange: Create a new Piste and mock the repository behavior
        Piste piste = new Piste(1L, "Piste1", Color.BLACK, 200, 200);
        when(pisteRepository.findById(1L)).thenReturn(java.util.Optional.of(piste));

        // Act: Retrieve the piste by its ID
        Piste retrievedPiste = pisteServices.retrievePiste(1L);

        // Assert: Check that the retrieved piste is the same as the saved piste
        assertEquals(piste.getNumPiste(), retrievedPiste.getNumPiste());
        assertEquals(piste.getNamePiste(), retrievedPiste.getNamePiste());
    }

    @Test
    @Order(4)
    public void testRetrievePiste_NotExists() {
        // Arrange: Mock the repository to return an empty Optional
        when(pisteRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        // Act: Attempt to retrieve a non-existing Piste
        Piste retrievedPiste = pisteServices.retrievePiste(99999L); // Assuming 999L does not exist

        // Assert: Check that the result is null
        assertNull(retrievedPiste);

    }

    
}
