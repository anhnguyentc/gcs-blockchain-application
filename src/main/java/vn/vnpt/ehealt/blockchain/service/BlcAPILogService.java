package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.BlcAPILog;
import vn.vnpt.ehealt.blockchain.repository.BlcAPILogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link BlcAPILog}.
 */
@Service
@Transactional
public class BlcAPILogService {

    private final Logger log = LoggerFactory.getLogger(BlcAPILogService.class);

    private final BlcAPILogRepository blcAPILogRepository;

    public BlcAPILogService(BlcAPILogRepository blcAPILogRepository) {
        this.blcAPILogRepository = blcAPILogRepository;
    }

    /**
     * Save a blcAPILog.
     *
     * @param blcAPILog the entity to save.
     * @return the persisted entity.
     */
    public BlcAPILog save(BlcAPILog blcAPILog) {
        log.debug("Request to save BlcAPILog : {}", blcAPILog);
        return blcAPILogRepository.save(blcAPILog);
    }

    /**
     * Get all the blcAPILogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcAPILog> findAll(Pageable pageable) {
        log.debug("Request to get all BlcAPILogs");
        return blcAPILogRepository.findAll(pageable);
    }



    /**
    *  Get all the blcAPILogs where BlcGiayChungSinh is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BlcAPILog> findAllWhereBlcGiayChungSinhIsNull() {
        log.debug("Request to get all blcAPILogs where BlcGiayChungSinh is null");
        return StreamSupport
            .stream(blcAPILogRepository.findAll().spliterator(), false)
            .filter(blcAPILog -> blcAPILog.getBlcGiayChungSinh() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one blcAPILog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BlcAPILog> findOne(Long id) {
        log.debug("Request to get BlcAPILog : {}", id);
        return blcAPILogRepository.findById(id);
    }

    /**
     * Delete the blcAPILog by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BlcAPILog : {}", id);
        blcAPILogRepository.deleteById(id);
    }
}
