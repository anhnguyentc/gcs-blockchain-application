package vn.vnpt.ehealt.blockchain.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A BlcGiayKhamSucKhoe.
 */
@Entity
@Table(name = "blc_giay_kham_suc_khoe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BlcGiayKhamSucKhoe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "so_giay_ksk")
    private String soGiayKSK;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "tuoi")
    private Integer tuoi;

    @Column(name = "so_cmnd")
    private String soCMND;

    @Column(name = "ngay_cap")
    private LocalDate ngayCap;

    @Column(name = "noi_cap")
    private String noiCap;

    @Column(name = "noi_o")
    private String noiO;

    @Column(name = "id_benh_vien")
    private String idBenhVien;

    @Column(name = "ten_benh_vien")
    private String tenBenhVien;

    @Column(name = "ngay_kham")
    private String ngayKham;

    @Column(name = "hang_bang_lai")
    private String hangBangLai;

    @Column(name = "ket_luan")
    private String ketLuan;

    @Column(name = "bac_sy_ket_luan")
    private String bacSyKetLuan;

    @Column(name = "pdf_giay_ksk")
    private String pdfGiayKSK;

    @Column(name = "status")
    private Integer status;

    @OneToOne(mappedBy = "blcGiayKhamSucKhoe")
    @JsonIgnore
    private DonViHanhChinh donViHanhChinh;

    @OneToOne(mappedBy = "blcGiayKhamSucKhoe")
    @JsonIgnore
    private BenhVien benhVien;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public BlcGiayKhamSucKhoe uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSoGiayKSK() {
        return soGiayKSK;
    }

    public BlcGiayKhamSucKhoe soGiayKSK(String soGiayKSK) {
        this.soGiayKSK = soGiayKSK;
        return this;
    }

    public void setSoGiayKSK(String soGiayKSK) {
        this.soGiayKSK = soGiayKSK;
    }

    public String getHoTen() {
        return hoTen;
    }

    public BlcGiayKhamSucKhoe hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public BlcGiayKhamSucKhoe gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getTuoi() {
        return tuoi;
    }

    public BlcGiayKhamSucKhoe tuoi(Integer tuoi) {
        this.tuoi = tuoi;
        return this;
    }

    public void setTuoi(Integer tuoi) {
        this.tuoi = tuoi;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public BlcGiayKhamSucKhoe soCMND(String soCMND) {
        this.soCMND = soCMND;
        return this;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public LocalDate getNgayCap() {
        return ngayCap;
    }

    public BlcGiayKhamSucKhoe ngayCap(LocalDate ngayCap) {
        this.ngayCap = ngayCap;
        return this;
    }

    public void setNgayCap(LocalDate ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public BlcGiayKhamSucKhoe noiCap(String noiCap) {
        this.noiCap = noiCap;
        return this;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getNoiO() {
        return noiO;
    }

    public BlcGiayKhamSucKhoe noiO(String noiO) {
        this.noiO = noiO;
        return this;
    }

    public void setNoiO(String noiO) {
        this.noiO = noiO;
    }

    public String getIdBenhVien() {
        return idBenhVien;
    }

    public BlcGiayKhamSucKhoe idBenhVien(String idBenhVien) {
        this.idBenhVien = idBenhVien;
        return this;
    }

    public void setIdBenhVien(String idBenhVien) {
        this.idBenhVien = idBenhVien;
    }

    public String getTenBenhVien() {
        return tenBenhVien;
    }

    public BlcGiayKhamSucKhoe tenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
        return this;
    }

    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public BlcGiayKhamSucKhoe ngayKham(String ngayKham) {
        this.ngayKham = ngayKham;
        return this;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getHangBangLai() {
        return hangBangLai;
    }

    public BlcGiayKhamSucKhoe hangBangLai(String hangBangLai) {
        this.hangBangLai = hangBangLai;
        return this;
    }

    public void setHangBangLai(String hangBangLai) {
        this.hangBangLai = hangBangLai;
    }

    public String getKetLuan() {
        return ketLuan;
    }

    public BlcGiayKhamSucKhoe ketLuan(String ketLuan) {
        this.ketLuan = ketLuan;
        return this;
    }

    public void setKetLuan(String ketLuan) {
        this.ketLuan = ketLuan;
    }

    public String getBacSyKetLuan() {
        return bacSyKetLuan;
    }

    public BlcGiayKhamSucKhoe bacSyKetLuan(String bacSyKetLuan) {
        this.bacSyKetLuan = bacSyKetLuan;
        return this;
    }

    public void setBacSyKetLuan(String bacSyKetLuan) {
        this.bacSyKetLuan = bacSyKetLuan;
    }

    public String getPdfGiayKSK() {
        return pdfGiayKSK;
    }

    public BlcGiayKhamSucKhoe pdfGiayKSK(String pdfGiayKSK) {
        this.pdfGiayKSK = pdfGiayKSK;
        return this;
    }

    public void setPdfGiayKSK(String pdfGiayKSK) {
        this.pdfGiayKSK = pdfGiayKSK;
    }

    public Integer getStatus() {
        return status;
    }

    public BlcGiayKhamSucKhoe status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DonViHanhChinh getDonViHanhChinh() {
        return donViHanhChinh;
    }

    public BlcGiayKhamSucKhoe donViHanhChinh(DonViHanhChinh donViHanhChinh) {
        this.donViHanhChinh = donViHanhChinh;
        return this;
    }

    public void setDonViHanhChinh(DonViHanhChinh donViHanhChinh) {
        this.donViHanhChinh = donViHanhChinh;
    }

    public BenhVien getBenhVien() {
        return benhVien;
    }

    public BlcGiayKhamSucKhoe benhVien(BenhVien benhVien) {
        this.benhVien = benhVien;
        return this;
    }

    public void setBenhVien(BenhVien benhVien) {
        this.benhVien = benhVien;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlcGiayKhamSucKhoe)) {
            return false;
        }
        return id != null && id.equals(((BlcGiayKhamSucKhoe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BlcGiayKhamSucKhoe{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", soGiayKSK='" + getSoGiayKSK() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", tuoi=" + getTuoi() +
            ", soCMND='" + getSoCMND() + "'" +
            ", ngayCap='" + getNgayCap() + "'" +
            ", noiCap='" + getNoiCap() + "'" +
            ", noiO='" + getNoiO() + "'" +
            ", idBenhVien='" + getIdBenhVien() + "'" +
            ", tenBenhVien='" + getTenBenhVien() + "'" +
            ", ngayKham='" + getNgayKham() + "'" +
            ", hangBangLai='" + getHangBangLai() + "'" +
            ", ketLuan='" + getKetLuan() + "'" +
            ", bacSyKetLuan='" + getBacSyKetLuan() + "'" +
            ", pdfGiayKSK='" + getPdfGiayKSK() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
