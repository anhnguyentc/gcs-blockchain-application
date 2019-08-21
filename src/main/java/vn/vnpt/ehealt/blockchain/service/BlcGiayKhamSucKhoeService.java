package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe;
import vn.vnpt.ehealt.blockchain.repository.BlcGiayKhamSucKhoeRepository;
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
 * Service Implementation for managing {@link BlcGiayKhamSucKhoe}.
 */
@Service
@Transactional
public class BlcGiayKhamSucKhoeService {

    private final Logger log = LoggerFactory.getLogger(BlcGiayKhamSucKhoeService.class);

    private final BlcGiayKhamSucKhoeRepository blcGiayKhamSucKhoeRepository;

    public BlcGiayKhamSucKhoeService(BlcGiayKhamSucKhoeRepository blcGiayKhamSucKhoeRepository) {
        this.blcGiayKhamSucKhoeRepository = blcGiayKhamSucKhoeRepository;
    }

    /**
     * Save a blcGiayKhamSucKhoe.
     *
     * @param blcGiayKhamSucKhoe the entity to save.
     * @return the persisted entity.
     */
    public BlcGiayKhamSucKhoe save(BlcGiayKhamSucKhoe blcGiayKhamSucKhoe) {
        log.debug("Request to save BlcGiayKhamSucKhoe : {}", blcGiayKhamSucKhoe);
        return blcGiayKhamSucKhoeRepository.save(blcGiayKhamSucKhoe);
    }

    /**
     * Get all the blcGiayKhamSucKhoes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcGiayKhamSucKhoe> findAll(Pageable pageable) {
        log.debug("Request to get all BlcGiayKhamSucKhoes");
        return blcGiayKhamSucKhoeRepository.findAll(pageable);
    }



    /**
    *  Get all the blcGiayKhamSucKhoes where DonViHanhChinh is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BlcGiayKhamSucKhoe> findAllWhereDonViHanhChinhIsNull() {
        log.debug("Request to get all blcGiayKhamSucKhoes where DonViHanhChinh is null");
        return StreamSupport
            .stream(blcGiayKhamSucKhoeRepository.findAll().spliterator(), false)
            .filter(blcGiayKhamSucKhoe -> blcGiayKhamSucKhoe.getDonViHanhChinh() == null)
            .collect(Collectors.toList());
    }


    /**
    *  Get all the blcGiayKhamSucKhoes where BenhVien is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BlcGiayKhamSucKhoe> findAllWhereBenhVienIsNull() {
        log.debug("Request to get all blcGiayKhamSucKhoes where BenhVien is null");
        return StreamSupport
            .stream(blcGiayKhamSucKhoeRepository.findAll().spliterator(), false)
            .filter(blcGiayKhamSucKhoe -> blcGiayKhamSucKhoe.getBenhVien() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one blcGiayKhamSucKhoe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BlcGiayKhamSucKhoe> findOne(Long id) {
        log.debug("Request to get BlcGiayKhamSucKhoe : {}", id);
        return blcGiayKhamSucKhoeRepository.findById(id);
    }

    /**
     * Delete the blcGiayKhamSucKhoe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BlcGiayKhamSucKhoe : {}", id);
        blcGiayKhamSucKhoeRepository.deleteById(id);
    }
}
