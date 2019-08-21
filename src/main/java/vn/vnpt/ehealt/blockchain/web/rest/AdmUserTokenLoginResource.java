package vn.vnpt.ehealt.blockchain.web.rest;

import vn.vnpt.ehealt.blockchain.domain.AdmUserTokenLogin;
import vn.vnpt.ehealt.blockchain.service.AdmUserTokenLoginService;
import vn.vnpt.ehealt.blockchain.web.rest.errors.BadRequestAlertException;
import vn.vnpt.ehealt.blockchain.service.dto.AdmUserTokenLoginCriteria;
import vn.vnpt.ehealt.blockchain.service.AdmUserTokenLoginQueryService;

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
 * REST controller for managing {@link vn.vnpt.ehealt.blockchain.domain.AdmUserTokenLogin}.
 */
@RestController
@RequestMapping("/api")
public class AdmUserTokenLoginResource {

    private final Logger log = LoggerFactory.getLogger(AdmUserTokenLoginResource.class);

    private static final String ENTITY_NAME = "admUserTokenLogin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdmUserTokenLoginService admUserTokenLoginService;

    private final AdmUserTokenLoginQueryService admUserTokenLoginQueryService;

    public AdmUserTokenLoginResource(AdmUserTokenLoginService admUserTokenLoginService, AdmUserTokenLoginQueryService admUserTokenLoginQueryService) {
        this.admUserTokenLoginService = admUserTokenLoginService;
        this.admUserTokenLoginQueryService = admUserTokenLoginQueryService;
    }

    /**
     * {@code POST  /adm-user-token-logins} : Create a new admUserTokenLogin.
     *
     * @param admUserTokenLogin the admUserTokenLogin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admUserTokenLogin, or with status {@code 400 (Bad Request)} if the admUserTokenLogin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adm-user-token-logins")
    public ResponseEntity<AdmUserTokenLogin> createAdmUserTokenLogin(@Valid @RequestBody AdmUserTokenLogin admUserTokenLogin) throws URISyntaxException {
        log.debug("REST request to save AdmUserTokenLogin : {}", admUserTokenLogin);
        if (admUserTokenLogin.getId() != null) {
            throw new BadRequestAlertException("A new admUserTokenLogin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdmUserTokenLogin result = admUserTokenLoginService.save(admUserTokenLogin);
        return ResponseEntity.created(new URI("/api/adm-user-token-logins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adm-user-token-logins} : Updates an existing admUserTokenLogin.
     *
     * @param admUserTokenLogin the admUserTokenLogin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admUserTokenLogin,
     * or with status {@code 400 (Bad Request)} if the admUserTokenLogin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admUserTokenLogin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adm-user-token-logins")
    public ResponseEntity<AdmUserTokenLogin> updateAdmUserTokenLogin(@Valid @RequestBody AdmUserTokenLogin admUserTokenLogin) throws URISyntaxException {
        log.debug("REST request to update AdmUserTokenLogin : {}", admUserTokenLogin);
        if (admUserTokenLogin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdmUserTokenLogin result = admUserTokenLoginService.save(admUserTokenLogin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, admUserTokenLogin.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adm-user-token-logins} : get all the admUserTokenLogins.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admUserTokenLogins in body.
     */
    @GetMapping("/adm-user-token-logins")
    public ResponseEntity<List<AdmUserTokenLogin>> getAllAdmUserTokenLogins(AdmUserTokenLoginCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AdmUserTokenLogins by criteria: {}", criteria);
        Page<AdmUserTokenLogin> page = admUserTokenLoginQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /adm-user-token-logins/count} : count all the admUserTokenLogins.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/adm-user-token-logins/count")
    public ResponseEntity<Long> countAdmUserTokenLogins(AdmUserTokenLoginCriteria criteria) {
        log.debug("REST request to count AdmUserTokenLogins by criteria: {}", criteria);
        return ResponseEntity.ok().body(admUserTokenLoginQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /adm-user-token-logins/:id} : get the "id" admUserTokenLogin.
     *
     * @param id the id of the admUserTokenLogin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admUserTokenLogin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adm-user-token-logins/{id}")
    public ResponseEntity<AdmUserTokenLogin> getAdmUserTokenLogin(@PathVariable Long id) {
        log.debug("REST request to get AdmUserTokenLogin : {}", id);
        Optional<AdmUserTokenLogin> admUserTokenLogin = admUserTokenLoginService.findOne(id);
        return ResponseUtil.wrapOrNotFound(admUserTokenLogin);
    }

    /**
     * {@code DELETE  /adm-user-token-logins/:id} : delete the "id" admUserTokenLogin.
     *
     * @param id the id of the admUserTokenLogin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adm-user-token-logins/{id}")
    public ResponseEntity<Void> deleteAdmUserTokenLogin(@PathVariable Long id) {
        log.debug("REST request to delete AdmUserTokenLogin : {}", id);
        admUserTokenLoginService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
