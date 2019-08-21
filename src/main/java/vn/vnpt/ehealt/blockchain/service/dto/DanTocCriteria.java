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
 * Criteria class for the {@link vn.vnpt.ehealt.blockchain.domain.DanToc} entity. This class is used
 * in {@link vn.vnpt.ehealt.blockchain.web.rest.DanTocResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dan-tocs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DanTocCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maDanToc;

    private StringFilter tenDanToc;

    private StringFilter ghiChu;

    private IntegerFilter status;

    private LongFilter blcGiayChungSinhId;

    public DanTocCriteria(){
    }

    public DanTocCriteria(DanTocCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maDanToc = other.maDanToc == null ? null : other.maDanToc.copy();
        this.tenDanToc = other.tenDanToc == null ? null : other.tenDanToc.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.blcGiayChungSinhId = other.blcGiayChungSinhId == null ? null : other.blcGiayChungSinhId.copy();
    }

    @Override
    public DanTocCriteria copy() {
        return new DanTocCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaDanToc() {
        return maDanToc;
    }

    public void setMaDanToc(StringFilter maDanToc) {
        this.maDanToc = maDanToc;
    }

    public StringFilter getTenDanToc() {
        return tenDanToc;
    }

    public void setTenDanToc(StringFilter tenDanToc) {
        this.tenDanToc = tenDanToc;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DanTocCriteria that = (DanTocCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maDanToc, that.maDanToc) &&
            Objects.equals(tenDanToc, that.tenDanToc) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(status, that.status) &&
            Objects.equals(blcGiayChungSinhId, that.blcGiayChungSinhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maDanToc,
        tenDanToc,
        ghiChu,
        status,
        blcGiayChungSinhId
        );
    }

    @Override
    public String toString() {
        return "DanTocCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maDanToc != null ? "maDanToc=" + maDanToc + ", " : "") +
                (tenDanToc != null ? "tenDanToc=" + tenDanToc + ", " : "") +
                (ghiChu != null ? "ghiChu=" + ghiChu + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (blcGiayChungSinhId != null ? "blcGiayChungSinhId=" + blcGiayChungSinhId + ", " : "") +
            "}";
    }

}
