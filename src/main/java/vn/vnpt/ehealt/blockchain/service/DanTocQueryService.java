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

import vn.vnpt.ehealt.blockchain.domain.DanToc;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.DanTocRepository;
import vn.vnpt.ehealt.blockchain.service.dto.DanTocCriteria;

/**
 * Service for executing complex queries for {@link DanToc} entities in the database.
 * The main input is a {@link DanTocCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DanToc} or a {@link Page} of {@link DanToc} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DanTocQueryService extends QueryService<DanToc> {

    private final Logger log = LoggerFactory.getLogger(DanTocQueryService.class);

    private final DanTocRepository danTocRepository;

    public DanTocQueryService(DanTocRepository danTocRepository) {
        this.danTocRepository = danTocRepository;
    }

    /**
     * Return a {@link List} of {@link DanToc} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DanToc> findByCriteria(DanTocCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DanToc> specification = createSpecification(criteria);
        return danTocRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link DanToc} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DanToc> findByCriteria(DanTocCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DanToc> specification = createSpecification(criteria);
        return danTocRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DanTocCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DanToc> specification = createSpecification(criteria);
        return danTocRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<DanToc> createSpecification(DanTocCriteria criteria) {
        Specification<DanToc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DanToc_.id));
            }
            if (criteria.getMaDanToc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaDanToc(), DanToc_.maDanToc));
            }
            if (criteria.getTenDanToc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDanToc(), DanToc_.tenDanToc));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), DanToc_.ghiChu));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), DanToc_.status));
            }
            if (criteria.getBlcGiayChungSinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcGiayChungSinhId(),
                    root -> root.join(DanToc_.blcGiayChungSinh, JoinType.LEFT).get(BlcGiayChungSinh_.id)));
            }
        }
        return specification;
    }
}
