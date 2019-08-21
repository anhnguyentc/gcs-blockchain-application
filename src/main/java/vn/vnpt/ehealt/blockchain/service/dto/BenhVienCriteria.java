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
 * Criteria class for the {@link vn.vnpt.ehealt.blockchain.domain.BenhVien} entity. This class is used
 * in {@link vn.vnpt.ehealt.blockchain.web.rest.BenhVienResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /benh-viens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BenhVienCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maBenhVien;

    private StringFilter tenBenhVien;

    private StringFilter ghiChu;

    private IntegerFilter status;

    private LongFilter blcGiayChungSinhId;

    private LongFilter blcGiayKhamSucKhoeId;

    public BenhVienCriteria(){
    }

    public BenhVienCriteria(BenhVienCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maBenhVien = other.maBenhVien == null ? null : other.maBenhVien.copy();
        this.tenBenhVien = other.tenBenhVien == null ? null : other.tenBenhVien.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.blcGiayChungSinhId = other.blcGiayChungSinhId == null ? null : other.blcGiayChungSinhId.copy();
        this.blcGiayKhamSucKhoeId = other.blcGiayKhamSucKhoeId == null ? null : other.blcGiayKhamSucKhoeId.copy();
    }

    @Override
    public BenhVienCriteria copy() {
        return new BenhVienCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaBenhVien() {
        return maBenhVien;
    }

    public void setMaBenhVien(StringFilter maBenhVien) {
        this.maBenhVien = maBenhVien;
    }

    public StringFilter getTenBenhVien() {
        return tenBenhVien;
    }

    public void setTenBenhVien(StringFilter tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public StringFilter getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(StringFilter ghiChu) {
        this.ghiChu = ghiChu;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getBlcGiayChungSinhId() {
        return blcGiayChungSinhId;
    }

    public void setBlcGiayChungSinhId(LongFilter blcGiayChungSinhId) {
        this.blcGiayChungSinhId = blcGiayChungSinhId;
    }

    public LongFilter getBlcGiayKhamSucKhoeId() {
        return blcGiayKhamSucKhoeId;
    }

    public void setBlcGiayKhamSucKhoeId(LongFilter blcGiayKhamSucKhoeId) {
        this.blcGiayKhamSucKhoeId = blcGiayKhamSucKhoeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BenhVienCriteria that = (BenhVienCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maBenhVien, that.maBenhVien) &&
            Objects.equals(tenBenhVien, that.tenBenhVien) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(status, that.status) &&
            Objects.equals(blcGiayChungSinhId, that.blcGiayChungSinhId) &&
            Objects.equals(blcGiayKhamSucKhoeId, that.blcGiayKhamSucKhoeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maBenhVien,
        tenBenhVien,
        ghiChu,
        status,
        blcGiayChungSinhId,
        blcGiayKhamSucKhoeId
        );
    }

    @Override
    public String toString() {
        return "BenhVienCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maBenhVien != null ? "maBenhVien=" + maBenhVien + ", " : "") +
                (tenBenhVien != null ? "tenBenhVien=" + tenBenhVien + ", " : "") +
                (ghiChu != null ? "ghiChu=" + ghiChu + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (blcGiayChungSinhId != null ? "blcGiayChungSinhId=" + blcGiayChungSinhId + ", " : "") +
                (blcGiayKhamSucKhoeId != null ? "blcGiayKhamSucKhoeId=" + blcGiayKhamSucKhoeId + ", " : "") +
            "}";
    }

}
