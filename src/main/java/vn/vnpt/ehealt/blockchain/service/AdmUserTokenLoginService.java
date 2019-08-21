package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.AdmUserTokenLogin;
import vn.vnpt.ehealt.blockchain.repository.AdmUserTokenLoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdmUserTokenLogin}.
 */
@Service
@Transactional
public class AdmUserTokenLoginService {

    private final Logger log = LoggerFactory.getLogger(AdmUserTokenLoginService.class);

    private final AdmUserTokenLoginRepository admUserTokenLoginRepository;

    public AdmUserTokenLoginService(AdmUserTokenLoginRepository admUserTokenLoginRepository) {
        this.admUserTokenLoginRepository = admUserTokenLoginRepository;
    }

    /**
     * Save a admUserTokenLogin.
     *
     * @param admUserTokenLogin the entity to save.
     * @return the persisted entity.
     */
    public AdmUserTokenLogin save(AdmUserTokenLogin admUserTokenLogin) {
        log.debug("Request to save AdmUserTokenLogin : {}", admUserTokenLogin);
        return admUserTokenLoginRepository.save(admUserTokenLogin);
    }

    /**
     * Get all the admUserTokenLogins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AdmUserTokenLogin> findAll(Pageable pageable) {
        log.debug("Request to get all AdmUserTokenLogins");
        return admUserTokenLoginRepository.findAll(pageable);
    }


    /**
     * Get one admUserTokenLogin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdmUserTokenLogin> findOne(Long id) {
        log.debug("Request to get AdmUserTokenLogin : {}", id);
        return admUserTokenLoginRepository.findById(id);
    }

    /**
     * Delete the admUserTokenLogin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdmUserTokenLogin : {}", id);
        admUserTokenLoginRepository.deleteById(id);
    }
}
