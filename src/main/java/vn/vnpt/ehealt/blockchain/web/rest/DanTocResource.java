package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.DanToc;
import vn.vnpt.ehealt.blockchain.service.DanTocService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.DanTocCriteria;
import vn.vnpt.ehealt.blockchain.service.DanTocQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.DanToc}.
 */
@RestController
@RequestMapping("/api")
public class DanTocResource {

    private final Logger log = LoggerFactory.getLogger(DanTocResource.class);

    private static final String ENTITY_NAME = "danToc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DanTocService danTocService;

    private final DanTocQueryService danTocQueryService;

    public DanTocResource(DanTocService danTocService, DanTocQueryService danTocQueryService) {
        this.danTocService = danTocService;
        this.danTocQueryService = danTocQueryService;
    }

    /**
     * {@code POST  /dan-tocs} : Create a new danToc.
     *
     * @param danToc the danToc to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new danToc, or with status {@code 400 (Bad Request)} if the danToc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dan-tocs")
    public ResponseEntity<DanToc> createDanToc(@RequestBody DanToc danToc) throws URISyntaxException {
        log.debug("REST request to save DanToc : {}", danToc);
        if (danToc.getId() != null) {
            throw new BadRequestAlertException("A new danToc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DanToc result = danTocService.save(danToc);
        return ResponseEntity.created(new URI("/api/dan-tocs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dan-tocs} : Updates an existing danToc.
     *
     * @param danToc the danToc to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated danToc,
     * or with status {@code 400 (Bad Request)} if the danToc is not valid,
     * or with status {@code 500 (Internal Server Error)} if the danToc couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dan-tocs")
    public ResponseEntity<DanToc> updateDanToc(@RequestBody DanToc danToc) throws URISyntaxException {
        log.debug("REST request to update DanToc : {}", danToc);
        if (danToc.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DanToc result = danTocService.save(danToc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, danToc.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dan-tocs} : get all the danTocs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of danTocs in body.
     */
    @GetMapping("/dan-tocs")
    public ResponseEntity<List<DanToc>> getAllDanTocs(DanTocCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DanTocs by criteria: {}", criteria);
        Page<DanToc> page = danTocQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /dan-tocs/count} : count all the danTocs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/dan-tocs/count")
    public ResponseEntity<Long> countDanTocs(DanTocCriteria criteria) {
        log.debug("REST request to count DanTocs by criteria: {}", criteria);
        return ResponseEntity.ok().body(danTocQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dan-tocs/:id} : get the "id" danToc.
     *
     * @param id the id of the danToc to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the danToc, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dan-tocs/{id}")
    public ResponseEntity<DanToc> getDanToc(@PathVariable Long id) {
        log.debug("REST request to get DanToc : {}", id);
        Optional<DanToc> danToc = danTocService.findOne(id);
        return ResponseUtil.wrapOrNotFound(danToc);
    }

    /**
     * {@code DELETE  /dan-tocs/:id} : delete the "id" danToc.
     *
     * @param id the id of the danToc to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dan-tocs/{id}")
    public ResponseEntity<Void> deleteDanToc(@PathVariable Long id) {
        log.debug("REST request to delete DanToc : {}", id);
        danTocService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
