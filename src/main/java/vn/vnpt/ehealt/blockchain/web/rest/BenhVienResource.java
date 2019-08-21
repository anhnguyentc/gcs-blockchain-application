package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.BenhVien;
import vn.vnpt.ehealt.blockchain.service.BenhVienService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.BenhVienCriteria;
import vn.vnpt.ehealt.blockchain.service.BenhVienQueryService;

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
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.BenhVien}.
 */
@RestController
@RequestMapping("/api")
public class BenhVienResource {

    private final Logger log = LoggerFactory.getLogger(BenhVienResource.class);

    private static final String ENTITY_NAME = "benhVien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BenhVienService benhVienService;

    private final BenhVienQueryService benhVienQueryService;

    public BenhVienResource(BenhVienService benhVienService, BenhVienQueryService benhVienQueryService) {
        this.benhVienService = benhVienService;
        this.benhVienQueryService = benhVienQueryService;
    }

    /**
     * {@code POST  /benh-viens} : Create a new benhVien.
     *
     * @param benhVien the benhVien to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new benhVien, or with status {@code 400 (Bad Request)} if the benhVien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/benh-viens")
    public ResponseEntity<BenhVien> createBenhVien(@Valid @RequestBody BenhVien benhVien) throws URISyntaxException {
        log.debug("REST request to save BenhVien : {}", benhVien);
        if (benhVien.getId() != null) {
            throw new BadRequestAlertException("A new benhVien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BenhVien result = benhVienService.save(benhVien);
        return ResponseEntity.created(new URI("/api/benh-viens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /benh-viens} : Updates an existing benhVien.
     *
     * @param benhVien the benhVien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated benhVien,
     * or with status {@code 400 (Bad Request)} if the benhVien is not valid,
     * or with status {@code 500 (Internal Server Error)} if the benhVien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/benh-viens")
    public ResponseEntity<BenhVien> updateBenhVien(@Valid @RequestBody BenhVien benhVien) throws URISyntaxException {
        log.debug("REST request to update BenhVien : {}", benhVien);
        if (benhVien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BenhVien result = benhVienService.save(benhVien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, benhVien.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /benh-viens} : get all the benhViens.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of benhViens in body.
     */
    @GetMapping("/benh-viens")
    public ResponseEntity<List<BenhVien>> getAllBenhViens(BenhVienCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BenhViens by criteria: {}", criteria);
        Page<BenhVien> page = benhVienQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /benh-viens/count} : count all the benhViens.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/benh-viens/count")
    public ResponseEntity<Long> countBenhViens(BenhVienCriteria criteria) {
        log.debug("REST request to count BenhViens by criteria: {}", criteria);
        return ResponseEntity.ok().body(benhVienQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /benh-viens/:id} : get the "id" benhVien.
     *
     * @param id the id of the benhVien to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the benhVien, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/benh-viens/{id}")
    public ResponseEntity<BenhVien> getBenhVien(@PathVariable Long id) {
        log.debug("REST request to get BenhVien : {}", id);
        Optional<BenhVien> benhVien = benhVienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(benhVien);
    }

    /**
     * {@code DELETE  /benh-viens/:id} : delete the "id" benhVien.
     *
     * @param id the id of the benhVien to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/benh-viens/{id}")
    public ResponseEntity<Void> deleteBenhVien(@PathVariable Long id) {
        log.debug("REST request to delete BenhVien : {}", id);
        benhVienService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
