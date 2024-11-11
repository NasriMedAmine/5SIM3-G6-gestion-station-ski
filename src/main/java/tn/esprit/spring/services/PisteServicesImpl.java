package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.util.List;
@AllArgsConstructor
@Service
public class PisteServicesImpl implements  IPisteServices{

    private IPisteRepository pisteRepository;
    private static final Logger logger = LogManager.getLogger(PisteServicesImpl.class);

    @Override
    public List<Piste> retrieveAllPistes() {
        logger.info("--------------------------------------------------------------");
        logger.info("--------------------------------------------------------------");

        logger.info("Retrieving all pistes");
        logger.info("--------------------------------------------------------------");
        logger.info("--------------------------------------------------------------");

        return pisteRepository.findAll();
    }

    @Override
    public Piste addPiste(Piste piste) {
        logger.info("--------------------------------------------------------------");

        logger.info("--------------------------------------------------------------");

        logger.info("Adding piste: {}", piste);
        logger.info("--------------------------------------------------------------");
        logger.info("--------------------------------------------------------------");

        return pisteRepository.save(piste);
        
    }

    @Override
    public void removePiste(Long numPiste) {
        logger.info("--------------------------------------------------------------");
        logger.info("--------------------------------------------------------------");
        logger.info("Removing piste with ID: {}", numPiste);
        logger.info("--------------------------------------------------------------");
        logger.info("--------------------------------------------------------------");
        pisteRepository.deleteById(numPiste);
    }

    @Override
    public Piste retrievePiste(Long numPiste) {
        logger.info("--------------------------------------------------------------");
        logger.info("--------------------------------------------------------------");
        logger.info("Retrieving piste with ID: {}", numPiste);
        logger.info("--------------------------------------------------------------");
        logger.info("--------------------------------------------------------------");
        return pisteRepository.findById(numPiste).orElse(null);
    }
}
