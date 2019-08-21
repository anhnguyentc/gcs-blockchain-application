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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe} entity. This class is used
 * in {@link vn.vnpt.ehealt.blockchain.web.rest.BlcGiayKhamSucKhoeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /blc-giay-kham-suc-khoes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BlcGiayKhamSucKhoeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter soGiayKSK;

    private StringFilter hoTen;

    private StringFilter gioiTinh;

    private IntegerFilter tuoi;

    private StringFilter soCMND;

    private LocalDateFilter ngayCap;

    private StringFilter noiCap;

    private StringFilter noiO;

    private StringFilter idBenhVien;

    private StringFilter tenBenhVien;

    private StringFilter ngayKham;

    private StringFilter hangBangLai;

    private StringFilter ketLuan;

    private StringFilter bacSyKetLuan;

    private StringFilter pdfGiayKSK;

    private IntegerFilter status;

    private LongFilter donViHanhChinhId;

    private LongFilter benhVienId;

    public BlcGiayKhamSucKhoeCriteria(){
    }

    public BlcGiayKhamSucKhoeCriteria(BlcGiayKhamSucKhoeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.soGiayKSK = other.soGiayKSK == null ? null : other.soGiayKSK.copy();
        this.hoTen = other.hoTen == null ? null : other.hoTen.copy();
        this.gioiTinh = other.gioiTinh == null ? null : other.gioiTinh.copy();
        this.tuoi = other.tuoi == null ? null : other.tuoi.copy();
        this.soCMND = other.soCMND == null ? null : other.soCMND.copy();
        this.ngayCap = other.ngayCap == null ? null : other.ngayCap.copy();
        this.noiCap = other.noiCap == null ? null : other.noiCap.copy();
        this.noiO = other.noiO == null ? null : other.noiO.copy();
        this.idBenhVien = other.idBenhVien == null ? null : other.idBenhVien.copy();
        this.tenBenhVien = other.tenBenhVien == null ? null : other.tenBenhVien.copy();
        this.ngayKham = other.ngayKham == null ? null : other.ngayKham.copy();
        this.hangBangLai = other.hangBangLai == null ? null : other.hangBangLai.copy();
        this.ketLuan = other.ketLuan == null ? null : other.ketLuan.copy();
        this.bacSyKetLuan = other.bacSyKetLuan == null ? null : other.bacSyKetLuan.copy();
        this.pdfGiayKSK = other.pdfGiayKSK == null ? null : other.pdfGiayKSK.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.donViHanhChinhId = other.donViHanhChinhId == null ? null : other.donViHanhChinhId.copy();
        this.benhVienId = other.benhVienId == null ? null : other.benhVienId.copy();
    }

    @Override
    public BlcGiayKhamSucKhoeCriteria copy() {
        return new BlcGiayKhamSucKhoeCriteria(this);
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

    public StringFilter getSoGiayKSK() {
        return soGiayKSK;
    }

    public void setSoGiayKSK(StringFilter soGiayKSK) {
        this.soGiayKSK = soGiayKSK;
    }

    public StringFilter getHoTen() {
        return hoTen;
    }

    public void setHoTen(StringFilter hoTen) {
        this.hoTen = hoTen;
    }

    public StringFilter getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(StringFilter gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public IntegerFilter getTuoi() {
        return tuoi;
    }

    public void setTuoi(IntegerFilter tuoi) {
        this.tuoi = tuoi;
    }

    public StringFilter getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(StringFilter soCMND) {
        this.soCMND = soCMND;
    }

    public LocalDateFilter getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(LocalDateFilter ngayCap) {
        this.ngayCap = ngayCap;
    }

    public StringFilter getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(StringFilter noiCap) {
        this.noiCap = noiCap;
    }

    public StringFilter getNoiO() {
        return noiO;
    }

    public void setNoiO(StringFilter noiO) {
        this.noiO = noiO;
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

    public StringFilter getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(StringFilter ngayKham) {
        this.ngayKham = ngayKham;
    }

    public StringFilter getHangBangLai() {
        return hangBangLai;
    }

    public void setHangBangLai(StringFilter hangBangLai) {
        this.hangBangLai = hangBangLai;
    }

    public StringFilter getKetLuan() {
        return ketLuan;
    }

    public void setKetLuan(StringFilter ketLuan) {
        this.ketLuan = ketLuan;
    }

    public StringFilter getBacSyKetLuan() {
        return bacSyKetLuan;
    }

    public void setBacSyKetLuan(StringFilter bacSyKetLuan) {
        this.bacSyKetLuan = bacSyKetLuan;
    }

    public StringFilter getPdfGiayKSK() {
        return pdfGiayKSK;
    }

    public void setPdfGiayKSK(StringFilter pdfGiayKSK) {
        this.pdfGiayKSK = pdfGiayKSK;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
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
        final BlcGiayKhamSucKhoeCriteria that = (BlcGiayKhamSucKhoeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uuid, that.uuid) &&
            Objects.equals(soGiayKSK, that.soGiayKSK) &&
            Objects.equals(hoTen, that.hoTen) &&
            Objects.equals(gioiTinh, that.gioiTinh) &&
            Objects.equals(tuoi, that.tuoi) &&
            Objects.equals(soCMND, that.soCMND) &&
            Objects.equals(ngayCap, that.ngayCap) &&
            Objects.equals(noiCap, that.noiCap) &&
            Objects.equals(noiO, that.noiO) &&
            Objects.equals(idBenhVien, that.idBenhVien) &&
            Objects.equals(tenBenhVien, that.tenBenhVien) &&
            Objects.equals(ngayKham, that.ngayKham) &&
            Objects.equals(hangBangLai, that.hangBangLai) &&
            Objects.equals(ketLuan, that.ketLuan) &&
            Objects.equals(bacSyKetLuan, that.bacSyKetLuan) &&
            Objects.equals(pdfGiayKSK, that.pdfGiayKSK) &&
            Objects.equals(status, that.status) &&
            Objects.equals(donViHanhChinhId, that.donViHanhChinhId) &&
            Objects.equals(benhVienId, that.benhVienId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        soGiayKSK,
        hoTen,
        gioiTinh,
        tuoi,
        soCMND,
        ngayCap,
        noiCap,
        noiO,
        idBenhVien,
        tenBenhVien,
        ngayKham,
        hangBangLai,
        ketLuan,
        bacSyKetLuan,
        pdfGiayKSK,
        status,
        donViHanhChinhId,
        benhVienId
        );
    }

    @Override
    public String toString() {
        return "BlcGiayKhamSucKhoeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (soGiayKSK != null ? "soGiayKSK=" + soGiayKSK + ", " : "") +
                (hoTen != null ? "hoTen=" + hoTen + ", " : "") +
                (gioiTinh != null ? "gioiTinh=" + gioiTinh + ", " : "") +
                (tuoi != null ? "tuoi=" + tuoi + ", " : "") +
                (soCMND != null ? "soCMND=" + soCMND + ", " : "") +
                (ngayCap != null ? "ngayCap=" + ngayCap + ", " : "") +
                (noiCap != null ? "noiCap=" + noiCap + ", " : "") +
                (noiO != null ? "noiO=" + noiO + ", " : "") +
                (idBenhVien != null ? "idBenhVien=" + idBenhVien + ", " : "") +
                (tenBenhVien != null ? "tenBenhVien=" + tenBenhVien + ", " : "") +
                (ngayKham != null ? "ngayKham=" + ngayKham + ", " : "") +
                (hangBangLai != null ? "hangBangLai=" + hangBangLai + ", " : "") +
                (ketLuan != null ? "ketLuan=" + ketLuan + ", " : "") +
                (bacSyKetLuan != null ? "bacSyKetLuan=" + bacSyKetLuan + ", " : "") +
                (pdfGiayKSK != null ? "pdfGiayKSK=" + pdfGiayKSK + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (donViHanhChinhId != null ? "donViHanhChinhId=" + donViHanhChinhId + ", " : "") +
                (benhVienId != null ? "benhVienId=" + benhVienId + ", " : "") +
            "}";
    }

}
