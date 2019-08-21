package vn.vnpt.ehealt.blockchain.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BenhVien.
 */
@Entity
@Table(name = "blc_benh_vien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BenhVien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ma_benh_vien", nullable = false)
    private String maBenhVien;

    @Column(name = "ten_benh_vien")
    private String tenBenhVien;

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

    public String getMaBenhVien() {
        return maBenhVien;
    }

    public BenhVien maBenhVien(String maBenhVien) {
        this.maBenhVien = maBenhVien;
        return this;
    }

    public void setMaBenhVien(String maBenhVien) {
        this.maBenhVien = maBenhVien;
    }

    public String getTenBenhVien() {
        return tenBenhVien;
    }

    public BenhVien tenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
        return this;
    }

    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public BenhVien ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getStatus() {
        return status;
    }

    public BenhVien status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BlcGiayChungSinh getBlcGiayChungSinh() {
        return blcGiayChungSinh;
    }

    public BenhVien blcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
        this.blcGiayChungSinh = blcGiayChungSinh;
        return this;
    }

    public void setBlcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
        this.blcGiayChungSinh = blcGiayChungSinh;
    }

    public BlcGiayKhamSucKhoe getBlcGiayKhamSucKhoe() {
        return blcGiayKhamSucKhoe;
    }

    public BenhVien blcGiayKhamSucKhoe(BlcGiayKhamSucKhoe blcGiayKhamSucKhoe) {
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
        if (!(o instanceof BenhVien)) {
            return false;
        }
        return id != null && id.equals(((BenhVien) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BenhVien{" +
            "id=" + getId() +
            ", maBenhVien='" + getMaBenhVien() + "'" +
            ", tenBenhVien='" + getTenBenhVien() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
