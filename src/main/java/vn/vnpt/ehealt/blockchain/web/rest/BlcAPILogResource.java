package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.BlcAPILog;
import vn.vnpt.ehealt.blockchain.service.BlcAPILogService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.BlcAPILogCriteria;
import vn.vnpt.ehealt.blockchain.service.BlcAPILogQueryService;

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
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.BlcAPILog}.
 */
@RestController
@RequestMapping("/api")
public class BlcAPILogResource {

    private final Logger log = LoggerFactory.getLogger(BlcAPILogResource.class);

    private static final String ENTITY_NAME = "blcAPILog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlcAPILogService blcAPILogService;

    private final BlcAPILogQueryService blcAPILogQueryService;

    public BlcAPILogResource(BlcAPILogService blcAPILogService, BlcAPILogQueryService blcAPILogQueryService) {
        this.blcAPILogService = blcAPILogService;
        this.blcAPILogQueryService = blcAPILogQueryService;
    }

    /**
     * {@code POST  /blc-api-logs} : Create a new blcAPILog.
     *
     * @param blcAPILog the blcAPILog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blcAPILog, or with status {@code 400 (Bad Request)} if the blcAPILog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blc-api-logs")
    public ResponseEntity<BlcAPILog> createBlcAPILog(@Valid @RequestBody BlcAPILog blcAPILog) throws URISyntaxException {
        log.debug("REST request to save BlcAPILog : {}", blcAPILog);
        if (blcAPILog.getId() != null) {
            throw new BadRequestAlertException("A new blcAPILog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlcAPILog result = blcAPILogService.save(blcAPILog);
        return ResponseEntity.created(new URI("/api/blc-api-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /blc-api-logs} : Updates an existing blcAPILog.
     *
     * @param blcAPILog the blcAPILog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blcAPILog,
     * or with status {@code 400 (Bad Request)} if the blcAPILog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blcAPILog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blc-api-logs")
    public ResponseEntity<BlcAPILog> updateBlcAPILog(@Valid @RequestBody BlcAPILog blcAPILog) throws URISyntaxException {
        log.debug("REST request to update BlcAPILog : {}", blcAPILog);
        if (blcAPILog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlcAPILog result = blcAPILogService.save(blcAPILog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, blcAPILog.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /blc-api-logs} : get all the blcAPILogs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blcAPILogs in body.
     */
    @GetMapping("/blc-api-logs")
    public ResponseEntity<List<BlcAPILog>> getAllBlcAPILogs(BlcAPILogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BlcAPILogs by criteria: {}", criteria);
        Page<BlcAPILog> page = blcAPILogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /blc-api-logs/count} : count all the blcAPILogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/blc-api-logs/count")
    public ResponseEntity<Long> countBlcAPILogs(BlcAPILogCriteria criteria) {
        log.debug("REST request to count BlcAPILogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(blcAPILogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /blc-api-logs/:id} : get the "id" blcAPILog.
     *
     * @param id the id of the blcAPILog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blcAPILog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blc-api-logs/{id}")
    public ResponseEntity<BlcAPILog> getBlcAPILog(@PathVariable Long id) {
        log.debug("REST request to get BlcAPILog : {}", id);
        Optional<BlcAPILog> blcAPILog = blcAPILogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blcAPILog);
    }

    /**
     * {@code DELETE  /blc-api-logs/:id} : delete the "id" blcAPILog.
     *
     * @param id the id of the blcAPILog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blc-api-logs/{id}")
    public ResponseEntity<Void> deleteBlcAPILog(@PathVariable Long id) {
        log.debug("REST request to delete BlcAPILog : {}", id);
        blcAPILogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
