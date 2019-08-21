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

import vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.BlcGiayKhamSucKhoeRepository;
import vn.vnpt.ehealt.blockchain.service.dto.BlcGiayKhamSucKhoeCriteria;

/**
 * Service for executing complex queries for {@link BlcGiayKhamSucKhoe} entities in the database.
 * The main input is a {@link BlcGiayKhamSucKhoeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BlcGiayKhamSucKhoe} or a {@link Page} of {@link BlcGiayKhamSucKhoe} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BlcGiayKhamSucKhoeQueryService extends QueryService<BlcGiayKhamSucKhoe> {

    private final Logger log = LoggerFactory.getLogger(BlcGiayKhamSucKhoeQueryService.class);

    private final BlcGiayKhamSucKhoeRepository blcGiayKhamSucKhoeRepository;

    public BlcGiayKhamSucKhoeQueryService(BlcGiayKhamSucKhoeRepository blcGiayKhamSucKhoeRepository) {
        this.blcGiayKhamSucKhoeRepository = blcGiayKhamSucKhoeRepository;
    }

    /**
     * Return a {@link List} of {@link BlcGiayKhamSucKhoe} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BlcGiayKhamSucKhoe> findByCriteria(BlcGiayKhamSucKhoeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BlcGiayKhamSucKhoe> specification = createSpecification(criteria);
        return blcGiayKhamSucKhoeRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BlcGiayKhamSucKhoe} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcGiayKhamSucKhoe> findByCriteria(BlcGiayKhamSucKhoeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BlcGiayKhamSucKhoe> specification = createSpecification(criteria);
        return blcGiayKhamSucKhoeRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BlcGiayKhamSucKhoeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BlcGiayKhamSucKhoe> specification = createSpecification(criteria);
        return blcGiayKhamSucKhoeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<BlcGiayKhamSucKhoe> createSpecification(BlcGiayKhamSucKhoeCriteria criteria) {
        Specification<BlcGiayKhamSucKhoe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BlcGiayKhamSucKhoe_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), BlcGiayKhamSucKhoe_.uuid));
            }
            if (criteria.getSoGiayKSK() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoGiayKSK(), BlcGiayKhamSucKhoe_.soGiayKSK));
            }
            if (criteria.getHoTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHoTen(), BlcGiayKhamSucKhoe_.hoTen));
            }
            if (criteria.getGioiTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGioiTinh(), BlcGiayKhamSucKhoe_.gioiTinh));
            }
            if (criteria.getTuoi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTuoi(), BlcGiayKhamSucKhoe_.tuoi));
            }
            if (criteria.getSoCMND() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoCMND(), BlcGiayKhamSucKhoe_.soCMND));
            }
            if (criteria.getNgayCap() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgayCap(), BlcGiayKhamSucKhoe_.ngayCap));
            }
            if (criteria.getNoiCap() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiCap(), BlcGiayKhamSucKhoe_.noiCap));
            }
            if (criteria.getNoiO() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiO(), BlcGiayKhamSucKhoe_.noiO));
            }
            if (criteria.getIdBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdBenhVien(), BlcGiayKhamSucKhoe_.idBenhVien));
            }
            if (criteria.getTenBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenBenhVien(), BlcGiayKhamSucKhoe_.tenBenhVien));
            }
            if (criteria.getNgayKham() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNgayKham(), BlcGiayKhamSucKhoe_.ngayKham));
            }
            if (criteria.getHangBangLai() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHangBangLai(), BlcGiayKhamSucKhoe_.hangBangLai));
            }
            if (criteria.getKetLuan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKetLuan(), BlcGiayKhamSucKhoe_.ketLuan));
            }
            if (criteria.getBacSyKetLuan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBacSyKetLuan(), BlcGiayKhamSucKhoe_.bacSyKetLuan));
            }
            if (criteria.getPdfGiayKSK() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPdfGiayKSK(), BlcGiayKhamSucKhoe_.pdfGiayKSK));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), BlcGiayKhamSucKhoe_.status));
            }
            if (criteria.getDonViHanhChinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getDonViHanhChinhId(),
                    root -> root.join(BlcGiayKhamSucKhoe_.donViHanhChinh, JoinType.LEFT).get(DonViHanhChinh_.id)));
            }
            if (criteria.getBenhVienId() != null) {
                specification = specification.and(buildSpecification(criteria.getBenhVienId(),
                    root -> root.join(BlcGiayKhamSucKhoe_.benhVien, JoinType.LEFT).get(BenhVien_.id)));
            }
        }
        return specification;
    }
}
