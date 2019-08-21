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

import vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.DonViHanhChinhRepository;
import vn.vnpt.ehealt.blockchain.service.dto.DonViHanhChinhCriteria;

/**
 * Service for executing complex queries for {@link DonViHanhChinh} entities in the database.
 * The main input is a {@link DonViHanhChinhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DonViHanhChinh} or a {@link Page} of {@link DonViHanhChinh} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DonViHanhChinhQueryService extends QueryService<DonViHanhChinh> {

    private final Logger log = LoggerFactory.getLogger(DonViHanhChinhQueryService.class);

    private final DonViHanhChinhRepository donViHanhChinhRepository;

    public DonViHanhChinhQueryService(DonViHanhChinhRepository donViHanhChinhRepository) {
        this.donViHanhChinhRepository = donViHanhChinhRepository;
    }

    /**
     * Return a {@link List} of {@link DonViHanhChinh} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DonViHanhChinh> findByCriteria(DonViHanhChinhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DonViHanhChinh> specification = createSpecification(criteria);
        return donViHanhChinhRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link DonViHanhChinh} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DonViHanhChinh> findByCriteria(DonViHanhChinhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DonViHanhChinh> specification = createSpecification(criteria);
        return donViHanhChinhRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DonViHanhChinhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DonViHanhChinh> specification = createSpecification(criteria);
        return donViHanhChinhRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<DonViHanhChinh> createSpecification(DonViHanhChinhCriteria criteria) {
        Specification<DonViHanhChinh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DonViHanhChinh_.id));
            }
            if (criteria.getMaDonViHanhChinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaDonViHanhChinh(), DonViHanhChinh_.maDonViHanhChinh));
            }
            if (criteria.getTenDonViHanhChinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDonViHanhChinh(), DonViHanhChinh_.tenDonViHanhChinh));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), DonViHanhChinh_.ghiChu));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), DonViHanhChinh_.status));
            }
            if (criteria.getBlcGiayChungSinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcGiayChungSinhId(),
                    root -> root.join(DonViHanhChinh_.blcGiayChungSinh, JoinType.LEFT).get(BlcGiayChungSinh_.id)));
            }
            if (criteria.getBlcGiayKhamSucKhoeId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcGiayKhamSucKhoeId(),
                    root -> root.join(DonViHanhChinh_.blcGiayKhamSucKhoe, JoinType.LEFT).get(BlcGiayKhamSucKhoe_.id)));
            }
        }
        return specification;
    }
}
