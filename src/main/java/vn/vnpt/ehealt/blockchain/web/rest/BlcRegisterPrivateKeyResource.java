package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey;
import vn.vnpt.ehealt.blockchain.service.BlcRegisterPrivateKeyService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.BlcRegisterPrivateKeyCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcRegisterPrivateKeyQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey}.
 */
@RestController
@RequestMapping("/api")
public class BlcRegisterPrivateKeyResource {

    private final Logger log = LoggerFactory.getLogger(BlcRegisterPrivateKeyResource.class);

    private static final String ENTITY_NAME = "blcRegisterPrivateKey";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlcRegisterPrivateKeyService blcRegisterPrivateKeyService;

    private final BlcRegisterPrivateKeyQueryService blcRegisterPrivateKeyQueryService;

    public BlcRegisterPrivateKeyResource(BlcRegisterPrivateKeyService blcRegisterPrivateKeyService, BlcRegisterPrivateKeyQueryService blcRegisterPrivateKeyQueryService) {
        this.blcRegisterPrivateKeyService = blcRegisterPrivateKeyService;
        this.blcRegisterPrivateKeyQueryService = blcRegisterPrivateKeyQueryService;
    }

    /**
     * {@code POST  /blc-register-private-keys} : Create a new blcRegisterPrivateKey.
     *
     * @param blcRegisterPrivateKey the blcRegisterPrivateKey to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blcRegisterPrivateKey, or with status {@code 400 (Bad Request)} if the blcRegisterPrivateKey has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blc-register-private-keys")
    public ResponseEntity<BlcRegisterPrivateKey> createBlcRegisterPrivateKey(@Valid @RequestBody BlcRegisterPrivateKey blcRegisterPrivateKey) throws URISyntaxException {
        log.debug("REST request to save BlcRegisterPrivateKey : {}", blcRegisterPrivateKey);
        if (blcRegisterPrivateKey.getId() != null) {
            throw new BadRequestAlertException("A new blcRegisterPrivateKey cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlcRegisterPrivateKey result = blcRegisterPrivateKeyService.save(blcRegisterPrivateKey);
        return ResponseEntity.created(new URI("/api/blc-register-private-keys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /blc-register-private-keys} : Updates an existing blcRegisterPrivateKey.
     *
     * @param blcRegisterPrivateKey the blcRegisterPrivateKey to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blcRegisterPrivateKey,
     * or with status {@code 400 (Bad Request)} if the blcRegisterPrivateKey is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blcRegisterPrivateKey couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blc-register-private-keys")
    public ResponseEntity<BlcRegisterPrivateKey> updateBlcRegisterPrivateKey(@Valid @RequestBody BlcRegisterPrivateKey blcRegisterPrivateKey) throws URISyntaxException {
        log.debug("REST request to update BlcRegisterPrivateKey : {}", blcRegisterPrivateKey);
        if (blcRegisterPrivateKey.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlcRegisterPrivateKey result = blcRegisterPrivateKeyService.save(blcRegisterPrivateKey);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, blcRegisterPrivateKey.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /blc-register-private-keys} : get all the blcRegisterPrivateKeys.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blcRegisterPrivateKeys in body.
     */
    @GetMapping("/blc-register-private-keys")
    public ResponseEntity<List<BlcRegisterPrivateKey>> getAllBlcRegisterPrivateKeys(BlcRegisterPrivateKeyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BlcRegisterPrivateKeys by criteria: {}", criteria);
        Page<BlcRegisterPrivateKey> page = blcRegisterPrivateKeyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /blc-register-private-keys/count} : count all the blcRegisterPrivateKeys.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/blc-register-private-keys/count")
    public ResponseEntity<Long> countBlcRegisterPrivateKeys(BlcRegisterPrivateKeyCriteria criteria) {
        log.debug("REST request to count BlcRegisterPrivateKeys by criteria: {}", criteria);
        return ResponseEntity.ok().body(blcRegisterPrivateKeyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /blc-register-private-keys/:id} : get the "id" blcRegisterPrivateKey.
     *
     * @param id the id of the blcRegisterPrivateKey to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blcRegisterPrivateKey, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blc-register-private-keys/{id}")
    public ResponseEntity<BlcRegisterPrivateKey> getBlcRegisterPrivateKey(@PathVariable Long id) {
        log.debug("REST request to get BlcRegisterPrivateKey : {}", id);
        Optional<BlcRegisterPrivateKey> blcRegisterPrivateKey = blcRegisterPrivateKeyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blcRegisterPrivateKey);
    }

    /**
     * {@code DELETE  /blc-register-private-keys/:id} : delete the "id" blcRegisterPrivateKey.
     *
     * @param id the id of the blcRegisterPrivateKey to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blc-register-private-keys/{id}")
    public ResponseEntity<Void> deleteBlcRegisterPrivateKey(@PathVariable Long id) {
        log.debug("REST request to delete BlcRegisterPrivateKey : {}", id);
        blcRegisterPrivateKeyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
