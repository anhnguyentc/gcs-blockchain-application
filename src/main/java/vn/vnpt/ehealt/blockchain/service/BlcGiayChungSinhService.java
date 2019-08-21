package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.repository.BlcGiayChungSinhRepository;
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
 * Service Implementation for managing {@link BlcGiayChungSinh}.
 */
@Service
@Transactional
public class BlcGiayChungSinhService {

    private final Logger log = LoggerFactory.getLogger(BlcGiayChungSinhService.class);

    private final BlcGiayChungSinhRepository blcGiayChungSinhRepository;

    public BlcGiayChungSinhService(BlcGiayChungSinhRepository blcGiayChungSinhRepository) {
        this.blcGiayChungSinhRepository = blcGiayChungSinhRepository;
    }

    /**
     * Save a blcGiayChungSinh.
     *
     * @param blcGiayChungSinh the entity to save.
     * @return the persisted entity.
     */
    public BlcGiayChungSinh save(BlcGiayChungSinh blcGiayChungSinh) {
        log.debug("Request to save BlcGiayChungSinh : {}", blcGiayChungSinh);
        return blcGiayChungSinhRepository.save(blcGiayChungSinh);
    }

    /**
     * Get all the blcGiayChungSinhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcGiayChungSinh> findAll(Pageable pageable) {
        log.debug("Request to get all BlcGiayChungSinhs");
        return blcGiayChungSinhRepository.findAll(pageable);
    }



    /**
    *  Get all the blcGiayChungSinhs where DanToc is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BlcGiayChungSinh> findAllWhereDanTocIsNull() {
        log.debug("Request to get all blcGiayChungSinhs where DanToc is null");
        return StreamSupport
            .stream(blcGiayChungSinhRepository.findAll().spliterator(), false)
            .filter(blcGiayChungSinh -> blcGiayChungSinh.getDanToc() == null)
            .collect(Collectors.toList());
    }


    /**
    *  Get all the blcGiayChungSinhs where DonViHanhChinh is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BlcGiayChungSinh> findAllWhereDonViHanhChinhIsNull() {
        log.debug("Request to get all blcGiayChungSinhs where DonViHanhChinh is null");
        return StreamSupport
            .stream(blcGiayChungSinhRepository.findAll().spliterator(), false)
            .filter(blcGiayChungSinh -> blcGiayChungSinh.getDonViHanhChinh() == null)
            .collect(Collectors.toList());
    }


    /**
    *  Get all the blcGiayChungSinhs where BenhVien is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<BlcGiayChungSinh> findAllWhereBenhVienIsNull() {
        log.debug("Request to get all blcGiayChungSinhs where BenhVien is null");
        return StreamSupport
            .stream(blcGiayChungSinhRepository.findAll().spliterator(), false)
            .filter(blcGiayChungSinh -> blcGiayChungSinh.getBenhVien() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one blcGiayChungSinh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BlcGiayChungSinh> findOne(Long id) {
        log.debug("Request to get BlcGiayChungSinh : {}", id);
        return blcGiayChungSinhRepository.findById(id);
    }

    /**
     * Delete the blcGiayChungSinh by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BlcGiayChungSinh : {}", id);
        blcGiayChungSinhRepository.deleteById(id);
    }
}
