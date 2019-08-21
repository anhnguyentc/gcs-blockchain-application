package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.service.BlcGiayChungSinhService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.BlcGiayChungSinhCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcGiayChungSinhQueryService;

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
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh}.
 */
@RestController
@RequestMapping("/api")
public class BlcGiayChungSinhResource {

    private final Logger log = LoggerFactory.getLogger(BlcGiayChungSinhResource.class);

    private static final String ENTITY_NAME = "blcGiayChungSinh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlcGiayChungSinhService blcGiayChungSinhService;

    private final BlcGiayChungSinhQueryService blcGiayChungSinhQueryService;

    public BlcGiayChungSinhResource(BlcGiayChungSinhService blcGiayChungSinhService, BlcGiayChungSinhQueryService blcGiayChungSinhQueryService) {
        this.blcGiayChungSinhService = blcGiayChungSinhService;
        this.blcGiayChungSinhQueryService = blcGiayChungSinhQueryService;
    }

    /**
     * {@code POST  /blc-giay-chung-sinhs} : Create a new blcGiayChungSinh.
     *
     * @param blcGiayChungSinh the blcGiayChungSinh to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blcGiayChungSinh, or with status {@code 400 (Bad Request)} if the blcGiayChungSinh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blc-giay-chung-sinhs")
    public ResponseEntity<BlcGiayChungSinh> createBlcGiayChungSinh(@Valid @RequestBody BlcGiayChungSinh blcGiayChungSinh) throws URISyntaxException {
        log.debug("REST request to save BlcGiayChungSinh : {}", blcGiayChungSinh);
        if (blcGiayChungSinh.getId() != null) {
            throw new BadRequestAlertException("A new blcGiayChungSinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlcGiayChungSinh result = blcGiayChungSinhService.save(blcGiayChungSinh);
        return ResponseEntity.created(new URI("/api/blc-giay-chung-sinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /blc-giay-chung-sinhs} : Updates an existing blcGiayChungSinh.
     *
     * @param blcGiayChungSinh the blcGiayChungSinh to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blcGiayChungSinh,
     * or with status {@code 400 (Bad Request)} if the blcGiayChungSinh is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blcGiayChungSinh couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blc-giay-chung-sinhs")
    public ResponseEntity<BlcGiayChungSinh> updateBlcGiayChungSinh(@Valid @RequestBody BlcGiayChungSinh blcGiayChungSinh) throws URISyntaxException {
        log.debug("REST request to update BlcGiayChungSinh : {}", blcGiayChungSinh);
        if (blcGiayChungSinh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlcGiayChungSinh result = blcGiayChungSinhService.save(blcGiayChungSinh);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, blcGiayChungSinh.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /blc-giay-chung-sinhs} : get all the blcGiayChungSinhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blcGiayChungSinhs in body.
     */
    @GetMapping("/blc-giay-chung-sinhs")
    public ResponseEntity<List<BlcGiayChungSinh>> getAllBlcGiayChungSinhs(BlcGiayChungSinhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BlcGiayChungSinhs by criteria: {}", criteria);
        Page<BlcGiayChungSinh> page = blcGiayChungSinhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /blc-giay-chung-sinhs/count} : count all the blcGiayChungSinhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/blc-giay-chung-sinhs/count")
    public ResponseEntity<Long> countBlcGiayChungSinhs(BlcGiayChungSinhCriteria criteria) {
        log.debug("REST request to count BlcGiayChungSinhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(blcGiayChungSinhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /blc-giay-chung-sinhs/:id} : get the "id" blcGiayChungSinh.
     *
     * @param id the id of the blcGiayChungSinh to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blcGiayChungSinh, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blc-giay-chung-sinhs/{id}")
    public ResponseEntity<BlcGiayChungSinh> getBlcGiayChungSinh(@PathVariable Long id) {
        log.debug("REST request to get BlcGiayChungSinh : {}", id);
        Optional<BlcGiayChungSinh> blcGiayChungSinh = blcGiayChungSinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blcGiayChungSinh);
    }

    /**
     * {@code DELETE  /blc-giay-chung-sinhs/:id} : delete the "id" blcGiayChungSinh.
     *
     * @param id the id of the blcGiayChungSinh to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blc-giay-chung-sinhs/{id}")
    public ResponseEntity<Void> deleteBlcGiayChungSinh(@PathVariable Long id) {
        log.debug("REST request to delete BlcGiayChungSinh : {}", id);
        blcGiayChungSinhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
