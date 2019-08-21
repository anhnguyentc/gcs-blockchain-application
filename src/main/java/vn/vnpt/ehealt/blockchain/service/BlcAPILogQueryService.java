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

import vn.vnpt.ehealt.blockchain.domain.BlcAPILog;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.BlcAPILogRepository;
import vn.vnpt.ehealt.blockchain.service.dto.BlcAPILogCriteria;

/**
 * Service for executing complex queries for {@link BlcAPILog} entities in the database.
 * The main input is a {@link BlcAPILogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BlcAPILog} or a {@link Page} of {@link BlcAPILog} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BlcAPILogQueryService extends QueryService<BlcAPILog> {

    private final Logger log = LoggerFactory.getLogger(BlcAPILogQueryService.class);

    private final BlcAPILogRepository blcAPILogRepository;

    public BlcAPILogQueryService(BlcAPILogRepository blcAPILogRepository) {
        this.blcAPILogRepository = blcAPILogRepository;
    }

    /**
     * Return a {@link List} of {@link BlcAPILog} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BlcAPILog> findByCriteria(BlcAPILogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BlcAPILog> specification = createSpecification(criteria);
        return blcAPILogRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BlcAPILog} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcAPILog> findByCriteria(BlcAPILogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BlcAPILog> specification = createSpecification(criteria);
        return blcAPILogRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BlcAPILogCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BlcAPILog> specification = createSpecification(criteria);
        return blcAPILogRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<BlcAPILog> createSpecification(BlcAPILogCriteria criteria) {
        Specification<BlcAPILog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BlcAPILog_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), BlcAPILog_.uuid));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), BlcAPILog_.status));
            }
            if (criteria.getMessageStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessageStatus(), BlcAPILog_.messageStatus));
            }
            if (criteria.getMethodName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMethodName(), BlcAPILog_.methodName));
            }
            if (criteria.getSoGCS() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGCS(), BlcAPILog_.soGCS));
            }
            if (criteria.getSoSOGCS() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoSOGCS(), BlcAPILog_.soSOGCS));
            }
            if (criteria.getRequestContent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRequestContent(), BlcAPILog_.requestContent));
            }
            if (criteria.getErrorMessage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getErrorMessage(), BlcAPILog_.errorMessage));
            }
            if (criteria.getLoadedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoadedTime(), BlcAPILog_.loadedTime));
            }
            if (criteria.getIpAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIpAddress(), BlcAPILog_.ipAddress));
            }
            if (criteria.getIdBenhvien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdBenhvien(), BlcAPILog_.idBenhvien));
            }
            if (criteria.getTenBenhvien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenBenhvien(), BlcAPILog_.tenBenhvien));
            }
            if (criteria.getProcessTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProcessTime(), BlcAPILog_.processTime));
            }
            if (criteria.getResponseTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResponseTime(), BlcAPILog_.responseTime));
            }
            if (criteria.getBlcGiayChungSinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcGiayChungSinhId(),
                    root -> root.join(BlcAPILog_.blcGiayChungSinh, JoinType.LEFT).get(BlcGiayChungSinh_.id)));
            }
        }
        return specification;
    }
}
