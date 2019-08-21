package vn.vnpt.ehealt.blockchain.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import vn.vnpt.ehealt.blockchain.domain.AdmUserTokenLogin;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.AdmUserTokenLoginRepository;
import vn.vnpt.ehealt.blockchain.service.dto.AdmUserTokenLoginCriteria;

/**
 * Service for executing complex queries for {@link AdmUserTokenLogin} entities in the database.
 * The main input is a {@link AdmUserTokenLoginCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AdmUserTokenLogin} or a {@link Page} of {@link AdmUserTokenLogin} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AdmUserTokenLoginQueryService extends QueryService<AdmUserTokenLogin> {

    private final Logger log = LoggerFactory.getLogger(AdmUserTokenLoginQueryService.class);

    private final AdmUserTokenLoginRepository admUserTokenLoginRepository;

    public AdmUserTokenLoginQueryService(AdmUserTokenLoginRepository admUserTokenLoginRepository) {
        this.admUserTokenLoginRepository = admUserTokenLoginRepository;
    }

    /**
     * Return a {@link List} of {@link AdmUserTokenLogin} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdmUserTokenLogin> findByCriteria(AdmUserTokenLoginCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AdmUserTokenLogin> specification = createSpecification(criteria);
        return admUserTokenLoginRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link AdmUserTokenLogin} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdmUserTokenLogin> findByCriteria(AdmUserTokenLoginCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AdmUserTokenLogin> specification = createSpecification(criteria);
        return admUserTokenLoginRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AdmUserTokenLoginCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AdmUserTokenLogin> specification = createSpecification(criteria);
        return admUserTokenLoginRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<AdmUserTokenLogin> createSpecification(AdmUserTokenLoginCriteria criteria) {
        Specification<AdmUserTokenLogin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AdmUserTokenLogin_.id));
            }
            if (criteria.getUserName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserName(), AdmUserTokenLogin_.userName));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), AdmUserTokenLogin_.createdDate));
            }
            if (criteria.getExpirationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExpirationDate(), AdmUserTokenLogin_.expirationDate));
            }
            if (criteria.getLoginIP() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLoginIP(), AdmUserTokenLogin_.loginIP));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActive(), AdmUserTokenLogin_.active));
            }
            if (criteria.getHeThong() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHeThong(), AdmUserTokenLogin_.heThong));
            }
            if (criteria.getTokenType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTokenType(), AdmUserTokenLogin_.tokenType));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), AdmUserTokenLogin_.status));
            }
        }
        return specification;
    }
}
