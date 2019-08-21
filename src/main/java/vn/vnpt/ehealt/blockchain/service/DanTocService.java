package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.DanToc;
import vn.vnpt.ehealt.blockchain.repository.DanTocRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DanToc}.
 */
@Service
@Transactional
public class DanTocService {

    private final Logger log = LoggerFactory.getLogger(DanTocService.class);

    private final DanTocRepository danTocRepository;

    public DanTocService(DanTocRepository danTocRepository) {
        this.danTocRepository = danTocRepository;
    }

    /**
     * Save a danToc.
     *
     * @param danToc the entity to save.
     * @return the persisted entity.
     */
    public DanToc save(DanToc danToc) {
        log.debug("Request to save DanToc : {}", danToc);
        return danTocRepository.save(danToc);
    }

    /**
     * Get all the danTocs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanToc> findAll(Pageable pageable) {
        log.debug("Request to get all DanTocs");
        return danTocRepository.findAll(pageable);
    }


    /**
     * Get one danToc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanToc> findOne(Long id) {
        log.debug("Request to get DanToc : {}", id);
        return danTocRepository.findById(id);
    }

    /**
     * Delete the danToc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanToc : {}", id);
        danTocRepository.deleteById(id);
    }
}
