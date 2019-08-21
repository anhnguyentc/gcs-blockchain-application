package vn.vnpt.ehealt.blockchain.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DonViHanhChinh.
 */
@Entity
@Table(name = "blc_don_vi_hanh_chinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DonViHanhChinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ma_don_vi_hanh_chinh", nullable = false)
    private String maDonViHanhChinh;

    @Column(name = "ten_don_vi_hanh_chinh")
    private String tenDonViHanhChinh;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(unique = true)
    private BlcGiayChungSinh blcGiayChungSinh;

    @OneToOne
    @JoinColumn(unique = true)
    private BlcGiayKhamSucKhoe blcGiayKhamSucKhoe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDonViHanhChinh() {
        return maDonViHanhChinh;
    }

    public DonViHanhChinh maDonViHanhChinh(String maDonViHanhChinh) {
        this.maDonViHanhChinh = maDonViHanhChinh;
        return this;
    }

    public void setMaDonViHanhChinh(String maDonViHanhChinh) {
        this.maDonViHanhChinh = maDonViHanhChinh;
    }

    public String getTenDonViHanhChinh() {
        return tenDonViHanhChinh;
    }

    public DonViHanhChinh tenDonViHanhChinh(String tenDonViHanhChinh) {
        this.tenDonViHanhChinh = tenDonViHanhChinh;
        return this;
    }

    public void setTenDonViHanhChinh(String tenDonViHanhChinh) {
        this.tenDonViHanhChinh = tenDonViHanhChinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public DonViHanhChinh ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getStatus() {
        return status;
    }

    public DonViHanhChinh status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BlcGiayChungSinh getBlcGiayChungSinh() {
        return blcGiayChungSinh;
    }

    public DonViHanhChinh blcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
        this.blcGiayChungSinh = blcGiayChungSinh;
        return this;
    }

    public void setBlcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
        this.blcGiayChungSinh = blcGiayChungSinh;
    }

    public BlcGiayKhamSucKhoe getBlcGiayKhamSucKhoe() {
        return blcGiayKhamSucKhoe;
    }

    public DonViHanhChinh blcGiayKhamSucKhoe(BlcGiayKhamSucKhoe blcGiayKhamSucKhoe) {
        this.blcGiayKhamSucKhoe = blcGiayKhamSucKhoe;
        return this;
    }

    public void setBlcGiayKhamSucKhoe(BlcGiayKhamSucKhoe blcGiayKhamSucKhoe) {
        this.blcGiayKhamSucKhoe = blcGiayKhamSucKhoe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DonViHanhChinh)) {
            return false;
        }
        return id != null && id.equals(((DonViHanhChinh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DonViHanhChinh{" +
            "id=" + getId() +
            ", maDonViHanhChinh='" + getMaDonViHanhChinh() + "'" +
            ", tenDonViHanhChinh='" + getTenDonViHanhChinh() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
