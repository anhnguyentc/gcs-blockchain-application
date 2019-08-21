package vn.vnpt.ehealt.blockchain.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A BlcGiayChungSinh.
 */
@Entity
@Table(name = "blc_giay_chung_sinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BlcGiayChungSinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "ten_me_nguoi_nuoi_duong")
    private String tenMeNguoiNuoiDuong;

    @Column(name = "nam_sinh")
    private String namSinh;

    @Column(name = "dia_chi_thuong_tru")
    private String diaChiThuongTru;

    @Column(name = "so_cmnd_passport")
    private String soCMNDPassport;

    @Column(name = "dan_toc")
    private String danToc;

    @Column(name = "gio_sinh")
    private String gioSinh;

    @Column(name = "ngay_thang_nam")
    private String ngayThangNam;

    @Column(name = "noi_sinh")
    private String noiSinh;

    @Column(name = "so_lan_sinh")
    private String soLanSinh;

    @Column(name = "so_con_hien_song")
    private String soConHienSong;

    @Column(name = "so_con_trong_lan_sinh")
    private String soConTrongLanSinh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "can_nang")
    private String canNang;

    @Column(name = "hien_trang")
    private String hienTrang;

    @Column(name = "nguoi_do_de")
    private String nguoiDoDe;

    @Column(name = "ten_du_dinh")
    private String tenDuDinh;

    @Column(name = "qr_code_image")
    private String qrCodeImage;

    @Column(name = "status_app")
    private String statusApp;

    @Column(name = "user_approve")
    private String userApprove;

    @Column(name = "user_create")
    private String userCreate;

    @Column(name = "address_gcs")
    private String addressGCS;

    @Column(name = "type_gcs")
    private String typeGCS;

    @Column(name = "so")
    private String so;

    @Column(name = "quyen_so")
    private String quyenSo;

    @Column(name = "email_ndk")
    private String emailNDK;

    @Column(name = "modified_date")
    private String modifiedDate;

    @Column(name = "id_benh_vien")
    private String idBenhVien;

    @Column(name = "ten_benh_vien")
    private String tenBenhVien;

    @Column(name = "id_user_create")
    private String idUserCreate;

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "code_soft")
    private String codeSoft;

    @Column(name = "ngay_thang_nam_cap")
    private String ngayThangNamCap;

    @Column(name = "noi_cap")
    private String noiCap;

    @Column(name = "ho_ten_cha")
    private String hoTenCha;

    @Column(name = "pdf_file")
    private String pdfFile;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(unique = true)
    private BlcAPILog blcAPILog;

    @OneToMany(mappedBy = "blcGiayChungSinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BlcRegisterPrivateKey> blcRegisterPrivateKeys = new HashSet<>();

    @OneToOne(mappedBy = "blcGiayChungSinh")
    @JsonIgnore
    private DanToc danToc;

    @OneToOne(mappedBy = "blcGiayChungSinh")
    @JsonIgnore
    private DonViHanhChinh donViHanhChinh;

    @OneToOne(mappedBy = "blcGiayChungSinh")
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

    public BlcGiayChungSinh uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public BlcGiayChungSinh createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTenMeNguoiNuoiDuong() {
        return tenMeNguoiNuoiDuong;
    }

    public BlcGiayChungSinh tenMeNguoiNuoiDuong(String tenMeNguoiNuoiDuong) {
        this.tenMeNguoiNuoiDuong = tenMeNguoiNuoiDuong;
        return this;
    }

    public void setTenMeNguoiNuoiDuong(String tenMeNguoiNuoiDuong) {
        this.tenMeNguoiNuoiDuong = tenMeNguoiNuoiDuong;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public BlcGiayChungSinh namSinh(String namSinh) {
        this.namSinh = namSinh;
        return this;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChiThuongTru() {
        return diaChiThuongTru;
    }

    public BlcGiayChungSinh diaChiThuongTru(String diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
        return this;
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
    }

    public String getSoCMNDPassport() {
        return soCMNDPassport;
    }

    public BlcGiayChungSinh soCMNDPassport(String soCMNDPassport) {
        this.soCMNDPassport = soCMNDPassport;
        return this;
    }

    public void setSoCMNDPassport(String soCMNDPassport) {
        this.soCMNDPassport = soCMNDPassport;
    }

    public String getDanToc() {
        return danToc;
    }

    public BlcGiayChungSinh danToc(String danToc) {
        this.danToc = danToc;
        return this;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getGioSinh() {
        return gioSinh;
    }

    public BlcGiayChungSinh gioSinh(String gioSinh) {
        this.gioSinh = gioSinh;
        return this;
    }

    public void setGioSinh(String gioSinh) {
        this.gioSinh = gioSinh;
    }

    public String getNgayThangNam() {
        return ngayThangNam;
    }

    public BlcGiayChungSinh ngayThangNam(String ngayThangNam) {
        this.ngayThangNam = ngayThangNam;
        return this;
    }

    public void setNgayThangNam(String ngayThangNam) {
        this.ngayThangNam = ngayThangNam;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public BlcGiayChungSinh noiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
        return this;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getSoLanSinh() {
        return soLanSinh;
    }

    public BlcGiayChungSinh soLanSinh(String soLanSinh) {
        this.soLanSinh = soLanSinh;
        return this;
    }

    public void setSoLanSinh(String soLanSinh) {
        this.soLanSinh = soLanSinh;
    }

    public String getSoConHienSong() {
        return soConHienSong;
    }

    public BlcGiayChungSinh soConHienSong(String soConHienSong) {
        this.soConHienSong = soConHienSong;
        return this;
    }

    public void setSoConHienSong(String soConHienSong) {
        this.soConHienSong = soConHienSong;
    }

    public String getSoConTrongLanSinh() {
        return soConTrongLanSinh;
    }

    public BlcGiayChungSinh soConTrongLanSinh(String soConTrongLanSinh) {
        this.soConTrongLanSinh = soConTrongLanSinh;
        return this;
    }

    public void setSoConTrongLanSinh(String soConTrongLanSinh) {
        this.soConTrongLanSinh = soConTrongLanSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public BlcGiayChungSinh gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCanNang() {
        return canNang;
    }

    public BlcGiayChungSinh canNang(String canNang) {
        this.canNang = canNang;
        return this;
    }

    public void setCanNang(String canNang) {
        this.canNang = canNang;
    }

    public String getHienTrang() {
        return hienTrang;
    }

    public BlcGiayChungSinh hienTrang(String hienTrang) {
        this.hienTrang = hienTrang;
        return this;
    }

    public void setHienTrang(String hienTrang) {
        this.hienTrang = hienTrang;
    }

    public String getNguoiDoDe() {
        return nguoiDoDe;
    }

    public BlcGiayChungSinh nguoiDoDe(String nguoiDoDe) {
        this.nguoiDoDe = nguoiDoDe;
        return this;
    }

    public void setNguoiDoDe(String nguoiDoDe) {
        this.nguoiDoDe = nguoiDoDe;
    }

    public String getTenDuDinh() {
        return tenDuDinh;
    }

    public BlcGiayChungSinh tenDuDinh(String tenDuDinh) {
        this.tenDuDinh = tenDuDinh;
        return this;
    }

    public void setTenDuDinh(String tenDuDinh) {
        this.tenDuDinh = tenDuDinh;
    }

    public String getQrCodeImage() {
        return qrCodeImage;
    }

    public BlcGiayChungSinh qrCodeImage(String qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
        return this;
    }

    public void setQrCodeImage(String qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public String getStatusApp() {
        return statusApp;
    }

    public BlcGiayChungSinh statusApp(String statusApp) {
        this.statusApp = statusApp;
        return this;
    }

    public void setStatusApp(String statusApp) {
        this.statusApp = statusApp;
    }

    public String getUserApprove() {
        return userApprove;
    }

    public BlcGiayChungSinh userApprove(String userApprove) {
        this.userApprove = userApprove;
        return this;
    }

    public void setUserApprove(String userApprove) {
        this.userApprove = userApprove;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public BlcGiayChungSinh userCreate(String userCreate) {
        this.userCreate = userCreate;
        return this;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getAddressGCS() {
        return addressGCS;
    }

    public BlcGiayChungSinh addressGCS(String addressGCS) {
        this.addressGCS = addressGCS;
        return this;
    }

    public void setAddressGCS(String addressGCS) {
        this.addressGCS = addressGCS;
    }

    public String getTypeGCS() {
        return typeGCS;
    }

    public BlcGiayChungSinh typeGCS(String typeGCS) {
        this.typeGCS = typeGCS;
        return this;
    }

    public void setTypeGCS(String typeGCS) {
        this.typeGCS = typeGCS;
    }

    public String getSo() {
        return so;
    }

    public BlcGiayChungSinh so(String so) {
        this.so = so;
        return this;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getQuyenSo() {
        return quyenSo;
    }

    public BlcGiayChungSinh quyenSo(String quyenSo) {
        this.quyenSo = quyenSo;
        return this;
    }

    public void setQuyenSo(String quyenSo) {
        this.quyenSo = quyenSo;
    }

    public String getEmailNDK() {
        return emailNDK;
    }

    public BlcGiayChungSinh emailNDK(String emailNDK) {
        this.emailNDK = emailNDK;
        return this;
    }

    public void setEmailNDK(String emailNDK) {
        this.emailNDK = emailNDK;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public BlcGiayChungSinh modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getIdBenhVien() {
        return idBenhVien;
    }

    public BlcGiayChungSinh idBenhVien(String idBenhVien) {
        this.idBenhVien = idBenhVien;
        return this;
    }

    public void setIdBenhVien(String idBenhVien) {
        this.idBenhVien = idBenhVien;
    }

    public String getTenBenhVien() {
        return tenBenhVien;
    }

    public BlcGiayChungSinh tenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
        return this;
    }

    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public String getIdUserCreate() {
        return idUserCreate;
    }

    public BlcGiayChungSinh idUserCreate(String idUserCreate) {
        this.idUserCreate = idUserCreate;
        return this;
    }

    public void setIdUserCreate(String idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public BlcGiayChungSinh publicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCodeSoft() {
        return codeSoft;
    }

    public BlcGiayChungSinh codeSoft(String codeSoft) {
        this.codeSoft = codeSoft;
        return this;
    }

    public void setCodeSoft(String codeSoft) {
        this.codeSoft = codeSoft;
    }

    public String getNgayThangNamCap() {
        return ngayThangNamCap;
    }

    public BlcGiayChungSinh ngayThangNamCap(String ngayThangNamCap) {
        this.ngayThangNamCap = ngayThangNamCap;
        return this;
    }

    public void setNgayThangNamCap(String ngayThangNamCap) {
        this.ngayThangNamCap = ngayThangNamCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public BlcGiayChungSinh noiCap(String noiCap) {
        this.noiCap = noiCap;
        return this;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getHoTenCha() {
        return hoTenCha;
    }

    public BlcGiayChungSinh hoTenCha(String hoTenCha) {
        this.hoTenCha = hoTenCha;
        return this;
    }

    public void setHoTenCha(String hoTenCha) {
        this.hoTenCha = hoTenCha;
    }

    public String getPdfFile() {
        return pdfFile;
    }

    public BlcGiayChungSinh pdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
        return this;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getStatus() {
        return status;
    }

    public BlcGiayChungSinh status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BlcAPILog getBlcAPILog() {
        return blcAPILog;
    }

    public BlcGiayChungSinh blcAPILog(BlcAPILog blcAPILog) {
        this.blcAPILog = blcAPILog;
        return this;
    }

    public void setBlcAPILog(BlcAPILog blcAPILog) {
        this.blcAPILog = blcAPILog;
    }

    public Set<BlcRegisterPrivateKey> getBlcRegisterPrivateKeys() {
        return blcRegisterPrivateKeys;
    }

    public BlcGiayChungSinh blcRegisterPrivateKeys(Set<BlcRegisterPrivateKey> blcRegisterPrivateKeys) {
        this.blcRegisterPrivateKeys = blcRegisterPrivateKeys;
        return this;
    }

    public BlcGiayChungSinh addBlcRegisterPrivateKey(BlcRegisterPrivateKey blcRegisterPrivateKey) {
        this.blcRegisterPrivateKeys.add(blcRegisterPrivateKey);
        blcRegisterPrivateKey.setBlcGiayChungSinh(this);
        return this;
    }

    public BlcGiayChungSinh removeBlcRegisterPrivateKey(BlcRegisterPrivateKey blcRegisterPrivateKey) {
        this.blcRegisterPrivateKeys.remove(blcRegisterPrivateKey);
        blcRegisterPrivateKey.setBlcGiayChungSinh(null);
        return this;
    }

    public void setBlcRegisterPrivateKeys(Set<BlcRegisterPrivateKey> blcRegisterPrivateKeys) {
        this.blcRegisterPrivateKeys = blcRegisterPrivateKeys;
    }

    public DanToc getDanToc() {
        return danToc;
    }

    public BlcGiayChungSinh danToc(DanToc danToc) {
        this.danToc = danToc;
        return this;
    }

    public void setDanToc(DanToc danToc) {
        this.danToc = danToc;
    }

    public DonViHanhChinh getDonViHanhChinh() {
        return donViHanhChinh;
    }

    public BlcGiayChungSinh donViHanhChinh(DonViHanhChinh donViHanhChinh) {
        this.donViHanhChinh = donViHanhChinh;
        return this;
    }

    public void setDonViHanhChinh(DonViHanhChinh donViHanhChinh) {
        this.donViHanhChinh = donViHanhChinh;
    }

    public BenhVien getBenhVien() {
        return benhVien;
    }

    public BlcGiayChungSinh benhVien(BenhVien benhVien) {
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
        if (!(o instanceof BlcGiayChungSinh)) {
            return false;
        }
        return id != null && id.equals(((BlcGiayChungSinh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BlcGiayChungSinh{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", tenMeNguoiNuoiDuong='" + getTenMeNguoiNuoiDuong() + "'" +
            ", namSinh='" + getNamSinh() + "'" +
            ", diaChiThuongTru='" + getDiaChiThuongTru() + "'" +
            ", soCMNDPassport='" + getSoCMNDPassport() + "'" +
            ", danToc='" + getDanToc() + "'" +
            ", gioSinh='" + getGioSinh() + "'" +
            ", ngayThangNam='" + getNgayThangNam() + "'" +
            ", noiSinh='" + getNoiSinh() + "'" +
            ", soLanSinh='" + getSoLanSinh() + "'" +
            ", soConHienSong='" + getSoConHienSong() + "'" +
            ", soConTrongLanSinh='" + getSoConTrongLanSinh() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", canNang='" + getCanNang() + "'" +
            ", hienTrang='" + getHienTrang() + "'" +
            ", nguoiDoDe='" + getNguoiDoDe() + "'" +
            ", tenDuDinh='" + getTenDuDinh() + "'" +
            ", qrCodeImage='" + getQrCodeImage() + "'" +
            ", statusApp='" + getStatusApp() + "'" +
            ", userApprove='" + getUserApprove() + "'" +
            ", userCreate='" + getUserCreate() + "'" +
            ", addressGCS='" + getAddressGCS() + "'" +
            ", typeGCS='" + getTypeGCS() + "'" +
            ", so='" + getSo() + "'" +
            ", quyenSo='" + getQuyenSo() + "'" +
            ", emailNDK='" + getEmailNDK() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", idBenhVien='" + getIdBenhVien() + "'" +
            ", tenBenhVien='" + getTenBenhVien() + "'" +
            ", idUserCreate='" + getIdUserCreate() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            ", codeSoft='" + getCodeSoft() + "'" +
            ", ngayThangNamCap='" + getNgayThangNamCap() + "'" +
            ", noiCap='" + getNoiCap() + "'" +
            ", hoTenCha='" + getHoTenCha() + "'" +
            ", pdfFile='" + getPdfFile() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
