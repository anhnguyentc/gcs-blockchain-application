package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh;
import vn.vnpt.ehealt.blockchain.service.DonViHanhChinhService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.DonViHanhChinhCriteria;
import vn.vnpt.ehealt.blockchain.service.DonViHanhChinhQueryService;

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
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh}.
 */
@RestController
@RequestMapping("/api")
public class DonViHanhChinhResource {

    private final Logger log = LoggerFactory.getLogger(DonViHanhChinhResource.class);

    private static final String ENTITY_NAME = "donViHanhChinh";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DonViHanhChinhService donViHanhChinhService;

    private final DonViHanhChinhQueryService donViHanhChinhQueryService;

    public DonViHanhChinhResource(DonViHanhChinhService donViHanhChinhService, DonViHanhChinhQueryService donViHanhChinhQueryService) {
        this.donViHanhChinhService = donViHanhChinhService;
        this.donViHanhChinhQueryService = donViHanhChinhQueryService;
    }

    /**
     * {@code POST  /don-vi-hanh-chinhs} : Create a new donViHanhChinh.
     *
     * @param donViHanhChinh the donViHanhChinh to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new donViHanhChinh, or with status {@code 400 (Bad Request)} if the donViHanhChinh has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/don-vi-hanh-chinhs")
    public ResponseEntity<DonViHanhChinh> createDonViHanhChinh(@Valid @RequestBody DonViHanhChinh donViHanhChinh) throws URISyntaxException {
        log.debug("REST request to save DonViHanhChinh : {}", donViHanhChinh);
        if (donViHanhChinh.getId() != null) {
            throw new BadRequestAlertException("A new donViHanhChinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonViHanhChinh result = donViHanhChinhService.save(donViHanhChinh);
        return ResponseEntity.created(new URI("/api/don-vi-hanh-chinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /don-vi-hanh-chinhs} : Updates an existing donViHanhChinh.
     *
     * @param donViHanhChinh the donViHanhChinh to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated donViHanhChinh,
     * or with status {@code 400 (Bad Request)} if the donViHanhChinh is not valid,
     * or with status {@code 500 (Internal Server Error)} if the donViHanhChinh couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/don-vi-hanh-chinhs")
    public ResponseEntity<DonViHanhChinh> updateDonViHanhChinh(@Valid @RequestBody DonViHanhChinh donViHanhChinh) throws URISyntaxException {
        log.debug("REST request to update DonViHanhChinh : {}", donViHanhChinh);
        if (donViHanhChinh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DonViHanhChinh result = donViHanhChinhService.save(donViHanhChinh);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, donViHanhChinh.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /don-vi-hanh-chinhs} : get all the donViHanhChinhs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of donViHanhChinhs in body.
     */
    @GetMapping("/don-vi-hanh-chinhs")
    public ResponseEntity<List<DonViHanhChinh>> getAllDonViHanhChinhs(DonViHanhChinhCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DonViHanhChinhs by criteria: {}", criteria);
        Page<DonViHanhChinh> page = donViHanhChinhQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /don-vi-hanh-chinhs/count} : count all the donViHanhChinhs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/don-vi-hanh-chinhs/count")
    public ResponseEntity<Long> countDonViHanhChinhs(DonViHanhChinhCriteria criteria) {
        log.debug("REST request to count DonViHanhChinhs by criteria: {}", criteria);
        return ResponseEntity.ok().body(donViHanhChinhQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /don-vi-hanh-chinhs/:id} : get the "id" donViHanhChinh.
     *
     * @param id the id of the donViHanhChinh to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the donViHanhChinh, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/don-vi-hanh-chinhs/{id}")
    public ResponseEntity<DonViHanhChinh> getDonViHanhChinh(@PathVariable Long id) {
        log.debug("REST request to get DonViHanhChinh : {}", id);
        Optional<DonViHanhChinh> donViHanhChinh = donViHanhChinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(donViHanhChinh);
    }

    /**
     * {@code DELETE  /don-vi-hanh-chinhs/:id} : delete the "id" donViHanhChinh.
     *
     * @param id the id of the donViHanhChinh to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/don-vi-hanh-chinhs/{id}")
    public ResponseEntity<Void> deleteDonViHanhChinh(@PathVariable Long id) {
        log.debug("REST request to delete DonViHanhChinh : {}", id);
        donViHanhChinhService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
