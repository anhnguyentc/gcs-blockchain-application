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
 * Criteria class for the {@link vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey} entity. This class is used
 * in {@link vn.vnpt.ehealt.blockchain.web.rest.BlcRegisterPrivateKeyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /blc-register-private-keys?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BlcRegisterPrivateKeyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter idBenhVien;

    private StringFilter tenBenhVien;

    private StringFilter uuidAcccount;

    private StringFilter uuidGCSDB;

    private StringFilter idUserCreate;

    private StringFilter codeSoft;

    private StringFilter publicKey;

    private StringFilter addressKey;

    private StringFilter createdDate;

    private StringFilter modifiedDate;

    private IntegerFilter status;

    private LongFilter blcGiayChungSinhId;

    public BlcRegisterPrivateKeyCriteria(){
    }

    public BlcRegisterPrivateKeyCriteria(BlcRegisterPrivateKeyCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.idBenhVien = other.idBenhVien == null ? null : other.idBenhVien.copy();
        this.tenBenhVien = other.tenBenhVien == null ? null : other.tenBenhVien.copy();
        this.uuidAcccount = other.uuidAcccount == null ? null : other.uuidAcccount.copy();
        this.uuidGCSDB = other.uuidGCSDB == null ? null : other.uuidGCSDB.copy();
        this.idUserCreate = other.idUserCreate == null ? null : other.idUserCreate.copy();
        this.codeSoft = other.codeSoft == null ? null : other.codeSoft.copy();
        this.publicKey = other.publicKey == null ? null : other.publicKey.copy();
        this.addressKey = other.addressKey == null ? null : other.addressKey.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.blcGiayChungSinhId = other.blcGiayChungSinhId == null ? null : other.blcGiayChungSinhId.copy();
    }

    @Override
    public BlcRegisterPrivateKeyCriteria copy() {
        return new BlcRegisterPrivateKeyCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public StringFilter getUuidAcccount() {
        return uuidAcccount;
    }

    public void setUuidAcccount(StringFilter uuidAcccount) {
        this.uuidAcccount = uuidAcccount;
    }

    public StringFilter getUuidGCSDB() {
        return uuidGCSDB;
    }

    public void setUuidGCSDB(StringFilter uuidGCSDB) {
        this.uuidGCSDB = uuidGCSDB;
    }

    public StringFilter getIdUserCreate() {
        return idUserCreate;
    }

    public void setIdUserCreate(StringFilter idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public StringFilter getCodeSoft() {
        return codeSoft;
    }

    public void setCodeSoft(StringFilter codeSoft) {
        this.codeSoft = codeSoft;
    }

    public StringFilter getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(StringFilter publicKey) {
        this.publicKey = publicKey;
    }

    public StringFilter getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(StringFilter addressKey) {
        this.addressKey = addressKey;
    }

    public StringFilter getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(StringFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(StringFilter modifiedDate) {
        this.modifiedDate = modifiedDate;
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
        final BlcRegisterPrivateKeyCriteria that = (BlcRegisterPrivateKeyCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(idBenhVien, that.idBenhVien) &&
            Objects.equals(tenBenhVien, that.tenBenhVien) &&
            Objects.equals(uuidAcccount, that.uuidAcccount) &&
            Objects.equals(uuidGCSDB, that.uuidGCSDB) &&
            Objects.equals(idUserCreate, that.idUserCreate) &&
            Objects.equals(codeSoft, that.codeSoft) &&
            Objects.equals(publicKey, that.publicKey) &&
            Objects.equals(addressKey, that.addressKey) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(modifiedDate, that.modifiedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(blcGiayChungSinhId, that.blcGiayChungSinhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        idBenhVien,
        tenBenhVien,
        uuidAcccount,
        uuidGCSDB,
        idUserCreate,
        codeSoft,
        publicKey,
        addressKey,
        createdDate,
        modifiedDate,
        status,
        blcGiayChungSinhId
        );
    }

    @Override
    public String toString() {
        return "BlcRegisterPrivateKeyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idBenhVien != null ? "idBenhVien=" + idBenhVien + ", " : "") +
                (tenBenhVien != null ? "tenBenhVien=" + tenBenhVien + ", " : "") +
                (uuidAcccount != null ? "uuidAcccount=" + uuidAcccount + ", " : "") +
                (uuidGCSDB != null ? "uuidGCSDB=" + uuidGCSDB + ", " : "") +
                (idUserCreate != null ? "idUserCreate=" + idUserCreate + ", " : "") +
                (codeSoft != null ? "codeSoft=" + codeSoft + ", " : "") +
                (publicKey != null ? "publicKey=" + publicKey + ", " : "") +
                (addressKey != null ? "addressKey=" + addressKey + ", " : "") +
                (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
                (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (blcGiayChungSinhId != null ? "blcGiayChungSinhId=" + blcGiayChungSinhId + ", " : "") +
            "}";
    }

}
