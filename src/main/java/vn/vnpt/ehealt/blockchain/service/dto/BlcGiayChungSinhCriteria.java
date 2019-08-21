package vn.vnpt.ehealt.blockchain.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh} entity. This class is used
 * in {@link vn.vnpt.ehealt.blockchain.web.rest.BlcGiayChungSinhResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /blc-giay-chung-sinhs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BlcGiayChungSinhCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter createdDate;

    private StringFilter tenMeNguoiNuoiDuong;

    private StringFilter namSinh;

    private StringFilter diaChiThuongTru;

    private StringFilter soCMNDPassport;

    private StringFilter danToc;

    private StringFilter gioSinh;

    private StringFilter ngayThangNam;

    private StringFilter noiSinh;

    private StringFilter soLanSinh;

    private StringFilter soConHienSong;

    private StringFilter soConTrongLanSinh;

    private StringFilter gioiTinh;

    private StringFilter canNang;

    private StringFilter hienTrang;

    private StringFilter nguoiDoDe;

    private StringFilter tenDuDinh;

    private StringFilter qrCodeImage;

    private StringFilter statusApp;

    private StringFilter userApprove;

    private StringFilter userCreate;

    private StringFilter addressGCS;

    private StringFilter typeGCS;

    private StringFilter so;

    private StringFilter quyenSo;

    private StringFilter emailNDK;

    private StringFilter modifiedDate;

    private StringFilter idBenhVien;

    private StringFilter tenBenhVien;

    private StringFilter idUserCreate;

    private StringFilter publicKey;

    private StringFilter codeSoft;

    private StringFilter ngayThangNamCap;

    private StringFilter noiCap;

    private StringFilter hoTenCha;

    private StringFilter pdfFile;

    private StringFilter status;

    private LongFilter blcAPILogId;

    private LongFilter blcRegisterPrivateKeyId;

    private LongFilter danTocId;

    private LongFilter donViHanhChinhId;

    private LongFilter benhVienId;

    public BlcGiayChungSinhCriteria(){
    }

    public BlcGiayChungSinhCriteria(BlcGiayChungSinhCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.tenMeNguoiNuoiDuong = other.tenMeNguoiNuoiDuong == null ? null : other.tenMeNguoiNuoiDuong.copy();
        this.namSinh = other.namSinh == null ? null : other.namSinh.copy();
        this.diaChiThuongTru = other.diaChiThuongTru == null ? null : other.diaChiThuongTru.copy();
        this.soCMNDPassport = other.soCMNDPassport == null ? null : other.soCMNDPassport.copy();
        this.danToc = other.danToc == null ? null : other.danToc.copy();
        this.gioSinh = other.gioSinh == null ? null : other.gioSinh.copy();
        this.ngayThangNam = other.ngayThangNam == null ? null : other.ngayThangNam.copy();
        this.noiSinh = other.noiSinh == null ? null : other.noiSinh.copy();
        this.soLanSinh = other.soLanSinh == null ? null : other.soLanSinh.copy();
        this.soConHienSong = other.soConHienSong == null ? null : other.soConHienSong.copy();
        this.soConTrongLanSinh = other.soConTrongLanSinh == null ? null : other.soConTrongLanSinh.copy();
        this.gioiTinh = other.gioiTinh == null ? null : other.gioiTinh.copy();
        this.canNang = other.canNang == null ? null : other.canNang.copy();
        this.hienTrang = other.hienTrang == null ? null : other.hienTrang.copy();
        this.nguoiDoDe = other.nguoiDoDe == null ? null : other.nguoiDoDe.copy();
        this.tenDuDinh = other.tenDuDinh == null ? null : other.tenDuDinh.copy();
        this.qrCodeImage = other.qrCodeImage == null ? null : other.qrCodeImage.copy();
        this.statusApp = other.statusApp == null ? null : other.statusApp.copy();
        this.userApprove = other.userApprove == null ? null : other.userApprove.copy();
        this.userCreate = other.userCreate == null ? null : other.userCreate.copy();
        this.addressGCS = other.addressGCS == null ? null : other.addressGCS.copy();
        this.typeGCS = other.typeGCS == null ? null : other.typeGCS.copy();
        this.so = other.so == null ? null : other.so.copy();
        this.quyenSo = other.quyenSo == null ? null : other.quyenSo.copy();
        this.emailNDK = other.emailNDK == null ? null : other.emailNDK.copy();
        this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
        this.idBenhVien = other.idBenhVien == null ? null : other.idBenhVien.copy();
        this.tenBenhVien = other.tenBenhVien == null ? null : other.tenBenhVien.copy();
        this.idUserCreate = other.idUserCreate == null ? null : other.idUserCreate.copy();
        this.publicKey = other.publicKey == null ? null : other.publicKey.copy();
        this.codeSoft = other.codeSoft == null ? null : other.codeSoft.copy();
        this.ngayThangNamCap = other.ngayThangNamCap == null ? null : other.ngayThangNamCap.copy();
        this.noiCap = other.noiCap == null ? null : other.noiCap.copy();
        this.hoTenCha = other.hoTenCha == null ? null : other.hoTenCha.copy();
        this.pdfFile = other.pdfFile == null ? null : other.pdfFile.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.blcAPILogId = other.blcAPILogId == null ? null : other.blcAPILogId.copy();
        this.blcRegisterPrivateKeyId = other.blcRegisterPrivateKeyId == null ? null : other.blcRegisterPrivateKeyId.copy();
        this.danTocId = other.danTocId == null ? null : other.danTocId.copy();
        this.donViHanhChinhId = other.donViHanhChinhId == null ? null : other.donViHanhChinhId.copy();
        this.benhVienId = other.benhVienId == null ? null : other.benhVienId.copy();
    }

    @Override
    public BlcGiayChungSinhCriteria copy() {
        return new BlcGiayChungSinhCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUuid() {
        return uuid;
    }

    public void setUuid(StringFilter uuid) {
        this.uuid = uuid;
    }

    public StringFilter getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(StringFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getTenMeNguoiNuoiDuong() {
        return tenMeNguoiNuoiDuong;
    }

    public void setTenMeNguoiNuoiDuong(StringFilter tenMeNguoiNuoiDuong) {
        this.tenMeNguoiNuoiDuong = tenMeNguoiNuoiDuong;
    }

    public StringFilter getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(StringFilter namSinh) {
        this.namSinh = namSinh;
    }

    public StringFilter getDiaChiThuongTru() {
        return diaChiThuongTru;
    }

    public void setDiaChiThuongTru(StringFilter diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
    }

    public StringFilter getSoCMNDPassport() {
        return soCMNDPassport;
    }

    public void setSoCMNDPassport(StringFilter soCMNDPassport) {
        this.soCMNDPassport = soCMNDPassport;
    }

    public StringFilter getDanToc() {
        return danToc;
    }

    public void setDanToc(StringFilter danToc) {
        this.danToc = danToc;
    }

    public StringFilter getGioSinh() {
        return gioSinh;
    }

    public void setGioSinh(StringFilter gioSinh) {
        this.gioSinh = gioSinh;
    }

    public StringFilter getNgayThangNam() {
        return ngayThangNam;
    }

    public void setNgayThangNam(StringFilter ngayThangNam) {
        this.ngayThangNam = ngayThangNam;
    }

    public StringFilter getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(StringFilter noiSinh) {
        this.noiSinh = noiSinh;
    }

    public StringFilter getSoLanSinh() {
        return soLanSinh;
    }

    public void setSoLanSinh(StringFilter soLanSinh) {
        this.soLanSinh = soLanSinh;
    }

    public StringFilter getSoConHienSong() {
        return soConHienSong;
    }

    public void setSoConHienSong(StringFilter soConHienSong) {
        this.soConHienSong = soConHienSong;
    }

    public StringFilter getSoConTrongLanSinh() {
        return soConTrongLanSinh;
    }

    public void setSoConTrongLanSinh(StringFilter soConTrongLanSinh) {
        this.soConTrongLanSinh = soConTrongLanSinh;
    }

    public StringFilter getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(StringFilter gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public StringFilter getCanNang() {
        return canNang;
    }

    public void setCanNang(StringFilter canNang) {
        this.canNang = canNang;
    }

    public StringFilter getHienTrang() {
        return hienTrang;
    }

    public void setHienTrang(StringFilter hienTrang) {
        this.hienTrang = hienTrang;
    }

    public StringFilter getNguoiDoDe() {
        return nguoiDoDe;
    }

    public void setNguoiDoDe(StringFilter nguoiDoDe) {
        this.nguoiDoDe = nguoiDoDe;
    }

    public StringFilter getTenDuDinh() {
        return tenDuDinh;
    }

    public void setTenDuDinh(StringFilter tenDuDinh) {
        this.tenDuDinh = tenDuDinh;
    }

    public StringFilter getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(StringFilter qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public StringFilter getStatusApp() {
        return statusApp;
    }

    public void setStatusApp(StringFilter statusApp) {
        this.statusApp = statusApp;
    }

    public StringFilter getUserApprove() {
        return userApprove;
    }

    public void setUserApprove(StringFilter userApprove) {
        this.userApprove = userApprove;
    }

    public StringFilter getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(StringFilter userCreate) {
        this.userCreate = userCreate;
    }

    public StringFilter getAddressGCS() {
        return addressGCS;
    }

    public void setAddressGCS(StringFilter addressGCS) {
        this.addressGCS = addressGCS;
    }

    public StringFilter getTypeGCS() {
        return typeGCS;
    }

    public void setTypeGCS(StringFilter typeGCS) {
        this.typeGCS = typeGCS;
    }

    public StringFilter getSo() {
        return so;
    }

    public void setSo(StringFilter so) {
        this.so = so;
    }

    public StringFilter getQuyenSo() {
        return quyenSo;
    }

    public void setQuyenSo(StringFilter quyenSo) {
        this.quyenSo = quyenSo;
    }

    public StringFilter getEmailNDK() {
        return emailNDK;
    }

    public void setEmailNDK(StringFilter emailNDK) {
        this.emailNDK = emailNDK;
    }

    public StringFilter getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(StringFilter modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public StringFilter getIdBenhVien() {
        return idBenhVien;
    }

    public void setIdBenhVien(StringFilter idBenhVien) {
        this.idBenhVien = idBenhVien;
    }

    public StringFilter getTenBenhVien() {
        return tenBenhVien;
    }

    public void setTenBenhVien(StringFilter tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public StringFilter getIdUserCreate() {
        return idUserCreate;
    }

    public void setIdUserCreate(StringFilter idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public StringFilter getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(StringFilter publicKey) {
        this.publicKey = publicKey;
    }

    public StringFilter getCodeSoft() {
        return codeSoft;
    }

    public void setCodeSoft(StringFilter codeSoft) {
        this.codeSoft = codeSoft;
    }

    public StringFilter getNgayThangNamCap() {
        return ngayThangNamCap;
    }

    public void setNgayThangNamCap(StringFilter ngayThangNamCap) {
        this.ngayThangNamCap = ngayThangNamCap;
    }

    public StringFilter getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(StringFilter noiCap) {
        this.noiCap = noiCap;
    }

    public StringFilter getHoTenCha() {
        return hoTenCha;
    }

    public void setHoTenCha(StringFilter hoTenCha) {
        this.hoTenCha = hoTenCha;
    }

    public StringFilter getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(StringFilter pdfFile) {
        this.pdfFile = pdfFile;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public LongFilter getBlcAPILogId() {
        return blcAPILogId;
    }

    public void setBlcAPILogId(LongFilter blcAPILogId) {
        this.blcAPILogId = blcAPILogId;
    }

    public LongFilter getBlcRegisterPrivateKeyId() {
        return blcRegisterPrivateKeyId;
    }

    public void setBlcRegisterPrivateKeyId(LongFilter blcRegisterPrivateKeyId) {
        this.blcRegisterPrivateKeyId = blcRegisterPrivateKeyId;
    }

    public LongFilter getDanTocId() {
        return danTocId;
    }

    public void setDanTocId(LongFilter danTocId) {
        this.danTocId = danTocId;
    }

    public LongFilter getDonViHanhChinhId() {
        return donViHanhChinhId;
    }

    public void setDonViHanhChinhId(LongFilter donViHanhChinhId) {
        this.donViHanhChinhId = donViHanhChinhId;
    }

    public LongFilter getBenhVienId() {
        return benhVienId;
    }

    public void setBenhVienId(LongFilter benhVienId) {
        this.benhVienId = benhVienId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BlcGiayChungSinhCriteria that = (BlcGiayChungSinhCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uuid, that.uuid) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(tenMeNguoiNuoiDuong, that.tenMeNguoiNuoiDuong) &&
            Objects.equals(namSinh, that.namSinh) &&
            Objects.equals(diaChiThuongTru, that.diaChiThuongTru) &&
            Objects.equals(soCMNDPassport, that.soCMNDPassport) &&
            Objects.equals(danToc, that.danToc) &&
            Objects.equals(gioSinh, that.gioSinh) &&
            Objects.equals(ngayThangNam, that.ngayThangNam) &&
            Objects.equals(noiSinh, that.noiSinh) &&
            Objects.equals(soLanSinh, that.soLanSinh) &&
            Objects.equals(soConHienSong, that.soConHienSong) &&
            Objects.equals(soConTrongLanSinh, that.soConTrongLanSinh) &&
            Objects.equals(gioiTinh, that.gioiTinh) &&
            Objects.equals(canNang, that.canNang) &&
            Objects.equals(hienTrang, that.hienTrang) &&
            Objects.equals(nguoiDoDe, that.nguoiDoDe) &&
            Objects.equals(tenDuDinh, that.tenDuDinh) &&
            Objects.equals(qrCodeImage, that.qrCodeImage) &&
            Objects.equals(statusApp, that.statusApp) &&
            Objects.equals(userApprove, that.userApprove) &&
            Objects.equals(userCreate, that.userCreate) &&
            Objects.equals(addressGCS, that.addressGCS) &&
            Objects.equals(typeGCS, that.typeGCS) &&
            Objects.equals(so, that.so) &&
            Objects.equals(quyenSo, that.quyenSo) &&
            Objects.equals(emailNDK, that.emailNDK) &&
            Objects.equals(modifiedDate, that.modifiedDate) &&
            Objects.equals(idBenhVien, that.idBenhVien) &&
            Objects.equals(tenBenhVien, that.tenBenhVien) &&
            Objects.equals(idUserCreate, that.idUserCreate) &&
            Objects.equals(publicKey, that.publicKey) &&
            Objects.equals(codeSoft, that.codeSoft) &&
            Objects.equals(ngayThangNamCap, that.ngayThangNamCap) &&
            Objects.equals(noiCap, that.noiCap) &&
            Objects.equals(hoTenCha, that.hoTenCha) &&
            Objects.equals(pdfFile, that.pdfFile) &&
            Objects.equals(status, that.status) &&
            Objects.equals(blcAPILogId, that.blcAPILogId) &&
            Objects.equals(blcRegisterPrivateKeyId, that.blcRegisterPrivateKeyId) &&
            Objects.equals(danTocId, that.danTocId) &&
            Objects.equals(donViHanhChinhId, that.donViHanhChinhId) &&
            Objects.equals(benhVienId, that.benhVienId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        createdDate,
        tenMeNguoiNuoiDuong,
        namSinh,
        diaChiThuongTru,
        soCMNDPassport,
        danToc,
        gioSinh,
        ngayThangNam,
        noiSinh,
        soLanSinh,
        soConHienSong,
        soConTrongLanSinh,
        gioiTinh,
        canNang,
        hienTrang,
        nguoiDoDe,
        tenDuDinh,
        qrCodeImage,
        statusApp,
        userApprove,
        userCreate,
        addressGCS,
        typeGCS,
        so,
        quyenSo,
        emailNDK,
        modifiedDate,
        idBenhVien,
        tenBenhVien,
        idUserCreate,
        publicKey,
        codeSoft,
        ngayThangNamCap,
        noiCap,
        hoTenCha,
        pdfFile,
        status,
        blcAPILogId,
        blcRegisterPrivateKeyId,
        danTocId,
        donViHanhChinhId,
        benhVienId
        );
    }

    @Override
    public String toString() {
        return "BlcGiayChungSinhCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
                (tenMeNguoiNuoiDuong != null ? "tenMeNguoiNuoiDuong=" + tenMeNguoiNuoiDuong + ", " : "") +
                (namSinh != null ? "namSinh=" + namSinh + ", " : "") +
                (diaChiThuongTru != null ? "diaChiThuongTru=" + diaChiThuongTru + ", " : "") +
                (soCMNDPassport != null ? "soCMNDPassport=" + soCMNDPassport + ", " : "") +
                (danToc != null ? "danToc=" + danToc + ", " : "") +
                (gioSinh != null ? "gioSinh=" + gioSinh + ", " : "") +
                (ngayThangNam != null ? "ngayThangNam=" + ngayThangNam + ", " : "") +
                (noiSinh != null ? "noiSinh=" + noiSinh + ", " : "") +
                (soLanSinh != null ? "soLanSinh=" + soLanSinh + ", " : "") +
                (soConHienSong != null ? "soConHienSong=" + soConHienSong + ", " : "") +
                (soConTrongLanSinh != null ? "soConTrongLanSinh=" + soConTrongLanSinh + ", " : "") +
                (gioiTinh != null ? "gioiTinh=" + gioiTinh + ", " : "") +
                (canNang != null ? "canNang=" + canNang + ", " : "") +
                (hienTrang != null ? "hienTrang=" + hienTrang + ", " : "") +
                (nguoiDoDe != null ? "nguoiDoDe=" + nguoiDoDe + ", " : "") +
                (tenDuDinh != null ? "tenDuDinh=" + tenDuDinh + ", " : "") +
                (qrCodeImage != null ? "qrCodeImage=" + qrCodeImage + ", " : "") +
                (statusApp != null ? "statusApp=" + statusApp + ", " : "") +
                (userApprove != null ? "userApprove=" + userApprove + ", " : "") +
                (userCreate != null ? "userCreate=" + userCreate + ", " : "") +
                (addressGCS != null ? "addressGCS=" + addressGCS + ", " : "") +
                (typeGCS != null ? "typeGCS=" + typeGCS + ", " : "") +
                (so != null ? "so=" + so + ", " : "") +
                (quyenSo != null ? "quyenSo=" + quyenSo + ", " : "") +
                (emailNDK != null ? "emailNDK=" + emailNDK + ", " : "") +
                (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
                (idBenhVien != null ? "idBenhVien=" + idBenhVien + ", " : "") +
                (tenBenhVien != null ? "tenBenhVien=" + tenBenhVien + ", " : "") +
                (idUserCreate != null ? "idUserCreate=" + idUserCreate + ", " : "") +
                (publicKey != null ? "publicKey=" + publicKey + ", " : "") +
                (codeSoft != null ? "codeSoft=" + codeSoft + ", " : "") +
                (ngayThangNamCap != null ? "ngayThangNamCap=" + ngayThangNamCap + ", " : "") +
                (noiCap != null ? "noiCap=" + noiCap + ", " : "") +
                (hoTenCha != null ? "hoTenCha=" + hoTenCha + ", " : "") +
                (pdfFile != null ? "pdfFile=" + pdfFile + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (blcAPILogId != null ? "blcAPILogId=" + blcAPILogId + ", " : "") +
                (blcRegisterPrivateKeyId != null ? "blcRegisterPrivateKeyId=" + blcRegisterPrivateKeyId + ", " : "") +
                (danTocId != null ? "danTocId=" + danTocId + ", " : "") +
                (donViHanhChinhId != null ? "donViHanhChinhId=" + donViHanhChinhId + ", " : "") +
                (benhVienId != null ? "benhVienId=" + benhVienId + ", " : "") +
            "}";
    }

}
