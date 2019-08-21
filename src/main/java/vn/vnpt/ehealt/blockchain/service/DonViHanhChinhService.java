package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh;
import vn.vnpt.ehealt.blockchain.repository.DonViHanhChinhRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DonViHanhChinh}.
 */
@Service
@Transactional
public class DonViHanhChinhService {

    private final Logger log = LoggerFactory.getLogger(DonViHanhChinhService.class);

    private final DonViHanhChinhRepository donViHanhChinhRepository;

    public DonViHanhChinhService(DonViHanhChinhRepository donViHanhChinhRepository) {
        this.donViHanhChinhRepository = donViHanhChinhRepository;
    }

    /**
     * Save a donViHanhChinh.
     *
     * @param donViHanhChinh the entity to save.
     * @return the persisted entity.
     */
    public DonViHanhChinh save(DonViHanhChinh donViHanhChinh) {
        log.debug("Request to save DonViHanhChinh : {}", donViHanhChinh);
        return donViHanhChinhRepository.save(donViHanhChinh);
    }

    /**
     * Get all the donViHanhChinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DonViHanhChinh> findAll(Pageable pageable) {
        log.debug("Request to get all DonViHanhChinhs");
        return donViHanhChinhRepository.findAll(pageable);
    }


    /**
     * Get one donViHanhChinh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DonViHanhChinh> findOne(Long id) {
        log.debug("Request to get DonViHanhChinh : {}", id);
        return donViHanhChinhRepository.findById(id);
    }

    /**
     * Delete the donViHanhChinh by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DonViHanhChinh : {}", id);
        donViHanhChinhRepository.deleteById(id);
    }
}
