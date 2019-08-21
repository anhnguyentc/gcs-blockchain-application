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

import vn.vnpt.ehealt.blockchain.domain.BenhVien;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.BenhVienRepository;
import vn.vnpt.ehealt.blockchain.service.dto.BenhVienCriteria;

/**
 * Service for executing complex queries for {@link BenhVien} entities in the database.
 * The main input is a {@link BenhVienCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BenhVien} or a {@link Page} of {@link BenhVien} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BenhVienQueryService extends QueryService<BenhVien> {

    private final Logger log = LoggerFactory.getLogger(BenhVienQueryService.class);

    private final BenhVienRepository benhVienRepository;

    public BenhVienQueryService(BenhVienRepository benhVienRepository) {
        this.benhVienRepository = benhVienRepository;
    }

    /**
     * Return a {@link List} of {@link BenhVien} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BenhVien> findByCriteria(BenhVienCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BenhVien> specification = createSpecification(criteria);
        return benhVienRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BenhVien} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BenhVien> findByCriteria(BenhVienCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BenhVien> specification = createSpecification(criteria);
        return benhVienRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BenhVienCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BenhVien> specification = createSpecification(criteria);
        return benhVienRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<BenhVien> createSpecification(BenhVienCriteria criteria) {
        Specification<BenhVien> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BenhVien_.id));
            }
            if (criteria.getMaBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaBenhVien(), BenhVien_.maBenhVien));
            }
            if (criteria.getTenBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenBenhVien(), BenhVien_.tenBenhVien));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), BenhVien_.ghiChu));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), BenhVien_.status));
            }
            if (criteria.getBlcGiayChungSinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcGiayChungSinhId(),
                    root -> root.join(BenhVien_.blcGiayChungSinh, JoinType.LEFT).get(BlcGiayChungSinh_.id)));
            }
            if (criteria.getBlcGiayKhamSucKhoeId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcGiayKhamSucKhoeId(),
                    root -> root.join(BenhVien_.blcGiayKhamSucKhoe, JoinType.LEFT).get(BlcGiayKhamSucKhoe_.id)));
            }
        }
        return specification;
    }
}
