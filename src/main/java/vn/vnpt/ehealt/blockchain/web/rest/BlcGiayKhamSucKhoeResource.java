package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe;
import vn.vnpt.ehealt.blockchain.service.BlcGiayKhamSucKhoeService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.BlcGiayKhamSucKhoeCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcGiayKhamSucKhoeQueryService;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe}.
 */
@RestController
@RequestMapping("/api")
public class BlcGiayKhamSucKhoeResource {

    private final Logger log = LoggerFactory.getLogger(BlcGiayKhamSucKhoeResource.class);

    private static final String ENTITY_NAME = "blcGiayKhamSucKhoe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlcGiayKhamSucKhoeService blcGiayKhamSucKhoeService;

    private final BlcGiayKhamSucKhoeQueryService blcGiayKhamSucKhoeQueryService;

    public BlcGiayKhamSucKhoeResource(BlcGiayKhamSucKhoeService blcGiayKhamSucKhoeService, BlcGiayKhamSucKhoeQueryService blcGiayKhamSucKhoeQueryService) {
        this.blcGiayKhamSucKhoeService = blcGiayKhamSucKhoeService;
        this.blcGiayKhamSucKhoeQueryService = blcGiayKhamSucKhoeQueryService;
    }

    /**
     * {@code POST  /blc-giay-kham-suc-khoes} : Create a new blcGiayKhamSucKhoe.
     *
     * @param blcGiayKhamSucKhoe the blcGiayKhamSucKhoe to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blcGiayKhamSucKhoe, or with status {@code 400 (Bad Request)} if the blcGiayKhamSucKhoe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blc-giay-kham-suc-khoes")
    public ResponseEntity<BlcGiayKhamSucKhoe> createBlcGiayKhamSucKhoe(@Valid @RequestBody BlcGiayKhamSucKhoe blcGiayKhamSucKhoe) throws URISyntaxException {
        log.debug("REST request to save BlcGiayKhamSucKhoe : {}", blcGiayKhamSucKhoe);
        if (blcGiayKhamSucKhoe.getId() != null) {
            throw new BadRequestAlertException("A new blcGiayKhamSucKhoe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlcGiayKhamSucKhoe result = blcGiayKhamSucKhoeService.save(blcGiayKhamSucKhoe);
        return ResponseEntity.created(new URI("/api/blc-giay-kham-suc-khoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /blc-giay-kham-suc-khoes} : Updates an existing blcGiayKhamSucKhoe.
     *
     * @param blcGiayKhamSucKhoe the blcGiayKhamSucKhoe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blcGiayKhamSucKhoe,
     * or with status {@code 400 (Bad Request)} if the blcGiayKhamSucKhoe is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blcGiayKhamSucKhoe couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blc-giay-kham-suc-khoes")
    public ResponseEntity<BlcGiayKhamSucKhoe> updateBlcGiayKhamSucKhoe(@Valid @RequestBody BlcGiayKhamSucKhoe blcGiayKhamSucKhoe) throws URISyntaxException {
        log.debug("REST request to update BlcGiayKhamSucKhoe : {}", blcGiayKhamSucKhoe);
        if (blcGiayKhamSucKhoe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlcGiayKhamSucKhoe result = blcGiayKhamSucKhoeService.save(blcGiayKhamSucKhoe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, blcGiayKhamSucKhoe.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /blc-giay-kham-suc-khoes} : get all the blcGiayKhamSucKhoes.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blcGiayKhamSucKhoes in body.
     */
    @GetMapping("/blc-giay-kham-suc-khoes")
    public ResponseEntity<List<BlcGiayKhamSucKhoe>> getAllBlcGiayKhamSucKhoes(BlcGiayKhamSucKhoeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BlcGiayKhamSucKhoes by criteria: {}", criteria);
        Page<BlcGiayKhamSucKhoe> page = blcGiayKhamSucKhoeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /blc-giay-kham-suc-khoes/count} : count all the blcGiayKhamSucKhoes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/blc-giay-kham-suc-khoes/count")
    public ResponseEntity<Long> countBlcGiayKhamSucKhoes(BlcGiayKhamSucKhoeCriteria criteria) {
        log.debug("REST request to count BlcGiayKhamSucKhoes by criteria: {}", criteria);
        return ResponseEntity.ok().body(blcGiayKhamSucKhoeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /blc-giay-kham-suc-khoes/:id} : get the "id" blcGiayKhamSucKhoe.
     *
     * @param id the id of the blcGiayKhamSucKhoe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blcGiayKhamSucKhoe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blc-giay-kham-suc-khoes/{id}")
    public ResponseEntity<BlcGiayKhamSucKhoe> getBlcGiayKhamSucKhoe(@PathVariable Long id) {
        log.debug("REST request to get BlcGiayKhamSucKhoe : {}", id);
        Optional<BlcGiayKhamSucKhoe> blcGiayKhamSucKhoe = blcGiayKhamSucKhoeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blcGiayKhamSucKhoe);
    }

    /**
     * {@code DELETE  /blc-giay-kham-suc-khoes/:id} : delete the "id" blcGiayKhamSucKhoe.
     *
     * @param id the id of the blcGiayKhamSucKhoe to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blc-giay-kham-suc-khoes/{id}")
    public ResponseEntity<Void> deleteBlcGiayKhamSucKhoe(@PathVariable Long id) {
        log.debug("REST request to delete BlcGiayKhamSucKhoe : {}", id);
        blcGiayKhamSucKhoeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
