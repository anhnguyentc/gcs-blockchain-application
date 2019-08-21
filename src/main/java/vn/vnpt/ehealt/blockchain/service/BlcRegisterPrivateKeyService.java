package vn.vnpt.ehealt.blockchain.service;

import vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey;
import vn.vnpt.ehealt.blockchain.repository.BlcRegisterPrivateKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BlcRegisterPrivateKey}.
 */
@Service
@Transactional
public class BlcRegisterPrivateKeyService {

    private final Logger log = LoggerFactory.getLogger(BlcRegisterPrivateKeyService.class);

    private final BlcRegisterPrivateKeyRepository blcRegisterPrivateKeyRepository;

    public BlcRegisterPrivateKeyService(BlcRegisterPrivateKeyRepository blcRegisterPrivateKeyRepository) {
        this.blcRegisterPrivateKeyRepository = blcRegisterPrivateKeyRepository;
    }

    /**
     * Save a blcRegisterPrivateKey.
     *
     * @param blcRegisterPrivateKey the entity to save.
     * @return the persisted entity.
     */
    public BlcRegisterPrivateKey save(BlcRegisterPrivateKey blcRegisterPrivateKey) {
        log.debug("Request to save BlcRegisterPrivateKey : {}", blcRegisterPrivateKey);
        return blcRegisterPrivateKeyRepository.save(blcRegisterPrivateKey);
    }

    /**
     * Get all the blcRegisterPrivateKeys.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcRegisterPrivateKey> findAll(Pageable pageable) {
        log.debug("Request to get all BlcRegisterPrivateKeys");
        return blcRegisterPrivateKeyRepository.findAll(pageable);
    }


    /**
     * Get one blcRegisterPrivateKey by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BlcRegisterPrivateKey> findOne(Long id) {
        log.debug("Request to get BlcRegisterPrivateKey : {}", id);
        return blcRegisterPrivateKeyRepository.findById(id);
    }

    /**
     * Delete the blcRegisterPrivateKey by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BlcRegisterPrivateKey : {}", id);
        blcRegisterPrivateKeyRepository.deleteById(id);
    }
}
