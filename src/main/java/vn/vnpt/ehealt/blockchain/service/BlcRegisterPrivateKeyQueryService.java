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

import vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.BlcRegisterPrivateKeyRepository;
import vn.vnpt.ehealt.blockchain.service.dto.BlcRegisterPrivateKeyCriteria;

/**
 * Service for executing complex queries for {@link BlcRegisterPrivateKey} entities in the database.
 * The main input is a {@link BlcRegisterPrivateKeyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BlcRegisterPrivateKey} or a {@link Page} of {@link BlcRegisterPrivateKey} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BlcRegisterPrivateKeyQueryService extends QueryService<BlcRegisterPrivateKey> {

    private final Logger log = LoggerFactory.getLogger(BlcRegisterPrivateKeyQueryService.class);

    private final BlcRegisterPrivateKeyRepository blcRegisterPrivateKeyRepository;

    public BlcRegisterPrivateKeyQueryService(BlcRegisterPrivateKeyRepository blcRegisterPrivateKeyRepository) {
        this.blcRegisterPrivateKeyRepository = blcRegisterPrivateKeyRepository;
    }

    /**
     * Return a {@link List} of {@link BlcRegisterPrivateKey} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BlcRegisterPrivateKey> findByCriteria(BlcRegisterPrivateKeyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BlcRegisterPrivateKey> specification = createSpecification(criteria);
        return blcRegisterPrivateKeyRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BlcRegisterPrivateKey} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcRegisterPrivateKey> findByCriteria(BlcRegisterPrivateKeyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BlcRegisterPrivateKey> specification = createSpecification(criteria);
        return blcRegisterPrivateKeyRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BlcRegisterPrivateKeyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BlcRegisterPrivateKey> specification = createSpecification(criteria);
        return blcRegisterPrivateKeyRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<BlcRegisterPrivateKey> createSpecification(BlcRegisterPrivateKeyCriteria criteria) {
        Specification<BlcRegisterPrivateKey> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BlcRegisterPrivateKey_.id));
            }
            if (criteria.getIdBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdBenhVien(), BlcRegisterPrivateKey_.idBenhVien));
            }
            if (criteria.getTenBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenBenhVien(), BlcRegisterPrivateKey_.tenBenhVien));
            }
            if (criteria.getUuidAcccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuidAcccount(), BlcRegisterPrivateKey_.uuidAcccount));
            }
            if (criteria.getUuidGCSDB() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuidGCSDB(), BlcRegisterPrivateKey_.uuidGCSDB));
            }
            if (criteria.getIdUserCreate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdUserCreate(), BlcRegisterPrivateKey_.idUserCreate));
            }
            if (criteria.getCodeSoft() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeSoft(), BlcRegisterPrivateKey_.codeSoft));
            }
            if (criteria.getPublicKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPublicKey(), BlcRegisterPrivateKey_.publicKey));
            }
            if (criteria.getAddressKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddressKey(), BlcRegisterPrivateKey_.addressKey));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedDate(), BlcRegisterPrivateKey_.createdDate));
            }
            if (criteria.getModifiedDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifiedDate(), BlcRegisterPrivateKey_.modifiedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), BlcRegisterPrivateKey_.status));
            }
            if (criteria.getBlcGiayChungSinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcGiayChungSinhId(),
                    root -> root.join(BlcRegisterPrivateKey_.blcGiayChungSinh, JoinType.LEFT).get(BlcGiayChungSinh_.id)));
            }
        }
        return specification;
    }
}
