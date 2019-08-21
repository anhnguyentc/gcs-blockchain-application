package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.BenhVien;
import vn.vnpt.ehealt.blockchain.repository.BenhVienRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BenhVien}.
 */
@Service
@Transactional
public class BenhVienService {

    private final Logger log = LoggerFactory.getLogger(BenhVienService.class);

    private final BenhVienRepository benhVienRepository;

    public BenhVienService(BenhVienRepository benhVienRepository) {
        this.benhVienRepository = benhVienRepository;
    }

    /**
     * Save a benhVien.
     *
     * @param benhVien the entity to save.
     * @return the persisted entity.
     */
    public BenhVien save(BenhVien benhVien) {
        log.debug("Request to save BenhVien : {}", benhVien);
        return benhVienRepository.save(benhVien);
    }

    /**
     * Get all the benhViens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BenhVien> findAll(Pageable pageable) {
        log.debug("Request to get all BenhViens");
        return benhVienRepository.findAll(pageable);
    }


    /**
     * Get one benhVien by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BenhVien> findOne(Long id) {
        log.debug("Request to get BenhVien : {}", id);
        return benhVienRepository.findById(id);
    }

    /**
     * Delete the benhVien by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BenhVien : {}", id);
        benhVienRepository.deleteById(id);
    }
}
