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

import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import vn.vnpt.ehealt.blockchain.domain.*; // for static metamodels
import vn.vnpt.ehealt.blockchain.repository.BlcGiayChungSinhRepository;
import vn.vnpt.ehealt.blockchain.service.dto.BlcGiayChungSinhCriteria;

/**
 * Service for executing complex queries for {@link BlcGiayChungSinh} entities in the database.
 * The main input is a {@link BlcGiayChungSinhCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BlcGiayChungSinh} or a {@link Page} of {@link BlcGiayChungSinh} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BlcGiayChungSinhQueryService extends QueryService<BlcGiayChungSinh> {

    private final Logger log = LoggerFactory.getLogger(BlcGiayChungSinhQueryService.class);

    private final BlcGiayChungSinhRepository blcGiayChungSinhRepository;

    public BlcGiayChungSinhQueryService(BlcGiayChungSinhRepository blcGiayChungSinhRepository) {
        this.blcGiayChungSinhRepository = blcGiayChungSinhRepository;
    }

    /**
     * Return a {@link List} of {@link BlcGiayChungSinh} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BlcGiayChungSinh> findByCriteria(BlcGiayChungSinhCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BlcGiayChungSinh> specification = createSpecification(criteria);
        return blcGiayChungSinhRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BlcGiayChungSinh} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BlcGiayChungSinh> findByCriteria(BlcGiayChungSinhCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BlcGiayChungSinh> specification = createSpecification(criteria);
        return blcGiayChungSinhRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BlcGiayChungSinhCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BlcGiayChungSinh> specification = createSpecification(criteria);
        return blcGiayChungSinhRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<BlcGiayChungSinh> createSpecification(BlcGiayChungSinhCriteria criteria) {
        Specification<BlcGiayChungSinh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BlcGiayChungSinh_.id));
            }
            if (criteria.getUuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUuid(), BlcGiayChungSinh_.uuid));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedDate(), BlcGiayChungSinh_.createdDate));
            }
            if (criteria.getTenMeNguoiNuoiDuong() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenMeNguoiNuoiDuong(), BlcGiayChungSinh_.tenMeNguoiNuoiDuong));
            }
            if (criteria.getNamSinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNamSinh(), BlcGiayChungSinh_.namSinh));
            }
            if (criteria.getDiaChiThuongTru() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChiThuongTru(), BlcGiayChungSinh_.diaChiThuongTru));
            }
            if (criteria.getSoCMNDPassport() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoCMNDPassport(), BlcGiayChungSinh_.soCMNDPassport));
            }
            if (criteria.getDanToc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDanToc(), BlcGiayChungSinh_.danToc));
            }
            if (criteria.getGioSinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGioSinh(), BlcGiayChungSinh_.gioSinh));
            }
            if (criteria.getNgayThangNam() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNgayThangNam(), BlcGiayChungSinh_.ngayThangNam));
            }
            if (criteria.getNoiSinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiSinh(), BlcGiayChungSinh_.noiSinh));
            }
            if (criteria.getSoLanSinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoLanSinh(), BlcGiayChungSinh_.soLanSinh));
            }
            if (criteria.getSoConHienSong() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoConHienSong(), BlcGiayChungSinh_.soConHienSong));
            }
            if (criteria.getSoConTrongLanSinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoConTrongLanSinh(), BlcGiayChungSinh_.soConTrongLanSinh));
            }
            if (criteria.getGioiTinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGioiTinh(), BlcGiayChungSinh_.gioiTinh));
            }
            if (criteria.getCanNang() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCanNang(), BlcGiayChungSinh_.canNang));
            }
            if (criteria.getHienTrang() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHienTrang(), BlcGiayChungSinh_.hienTrang));
            }
            if (criteria.getNguoiDoDe() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNguoiDoDe(), BlcGiayChungSinh_.nguoiDoDe));
            }
            if (criteria.getTenDuDinh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenDuDinh(), BlcGiayChungSinh_.tenDuDinh));
            }
            if (criteria.getQrCodeImage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQrCodeImage(), BlcGiayChungSinh_.qrCodeImage));
            }
            if (criteria.getStatusApp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusApp(), BlcGiayChungSinh_.statusApp));
            }
            if (criteria.getUserApprove() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserApprove(), BlcGiayChungSinh_.userApprove));
            }
            if (criteria.getUserCreate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserCreate(), BlcGiayChungSinh_.userCreate));
            }
            if (criteria.getAddressGCS() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddressGCS(), BlcGiayChungSinh_.addressGCS));
            }
            if (criteria.getTypeGCS() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeGCS(), BlcGiayChungSinh_.typeGCS));
            }
            if (criteria.getSo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSo(), BlcGiayChungSinh_.so));
            }
            if (criteria.getQuyenSo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQuyenSo(), BlcGiayChungSinh_.quyenSo));
            }
            if (criteria.getEmailNDK() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailNDK(), BlcGiayChungSinh_.emailNDK));
            }
            if (criteria.getModifiedDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifiedDate(), BlcGiayChungSinh_.modifiedDate));
            }
            if (criteria.getIdBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdBenhVien(), BlcGiayChungSinh_.idBenhVien));
            }
            if (criteria.getTenBenhVien() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenBenhVien(), BlcGiayChungSinh_.tenBenhVien));
            }
            if (criteria.getIdUserCreate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdUserCreate(), BlcGiayChungSinh_.idUserCreate));
            }
            if (criteria.getPublicKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPublicKey(), BlcGiayChungSinh_.publicKey));
            }
            if (criteria.getCodeSoft() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeSoft(), BlcGiayChungSinh_.codeSoft));
            }
            if (criteria.getNgayThangNamCap() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNgayThangNamCap(), BlcGiayChungSinh_.ngayThangNamCap));
            }
            if (criteria.getNoiCap() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNoiCap(), BlcGiayChungSinh_.noiCap));
            }
            if (criteria.getHoTenCha() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHoTenCha(), BlcGiayChungSinh_.hoTenCha));
            }
            if (criteria.getPdfFile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPdfFile(), BlcGiayChungSinh_.pdfFile));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), BlcGiayChungSinh_.status));
            }
            if (criteria.getBlcAPILogId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcAPILogId(),
                    root -> root.join(BlcGiayChungSinh_.blcAPILog, JoinType.LEFT).get(BlcAPILog_.id)));
            }
            if (criteria.getBlcRegisterPrivateKeyId() != null) {
                specification = specification.and(buildSpecification(criteria.getBlcRegisterPrivateKeyId(),
                    root -> root.join(BlcGiayChungSinh_.blcRegisterPrivateKeys, JoinType.LEFT).get(BlcRegisterPrivateKey_.id)));
            }
            if (criteria.getDanTocId() != null) {
                specification = specification.and(buildSpecification(criteria.getDanTocId(),
                    root -> root.join(BlcGiayChungSinh_.danToc, JoinType.LEFT).get(DanToc_.id)));
            }
            if (criteria.getDonViHanhChinhId() != null) {
                specification = specification.and(buildSpecification(criteria.getDonViHanhChinhId(),
                    root -> root.join(BlcGiayChungSinh_.donViHanhChinh, JoinType.LEFT).get(DonViHanhChinh_.id)));
            }
            if (criteria.getBenhVienId() != null) {
                specification = specification.and(buildSpecification(criteria.getBenhVienId(),
                    root -> root.join(BlcGiayChungSinh_.benhVien, JoinType.LEFT).get(BenhVien_.id)));
            }
        }
        return specification;
    }
}
