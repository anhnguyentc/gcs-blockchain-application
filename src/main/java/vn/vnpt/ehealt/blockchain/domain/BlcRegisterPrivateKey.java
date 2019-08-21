package vn.vnpt.ehealt.blockchain.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BlcRegisterPrivateKey.
 */
@Entity
@Table(name = "blc_register_private_key")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BlcRegisterPrivateKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_benh_vien", nullable = false)
    private String idBenhVien;

    @NotNull
    @Column(name = "ten_benh_vien", nullable = false)
    private String tenBenhVien;

    @Column(name = "uuid_acccount")
    private String uuidAcccount;

    @Column(name = "uuid_gcsdb")
    private String uuidGCSDB;

    @Column(name = "id_user_create")
    private String idUserCreate;

    @Column(name = "code_soft")
    private String codeSoft;

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "address_key")
    private String addressKey;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "modified_date")
    private String modifiedDate;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JsonIgnoreProperties("blcRegisterPrivateKeys")
    private BlcGiayChungSinh blcGiayChungSinh;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdBenhVien() {
        return idBenhVien;
    }

    public BlcRegisterPrivateKey idBenhVien(String idBenhVien) {
        this.idBenhVien = idBenhVien;
        return this;
    }

    public void setIdBenhVien(String idBenhVien) {
        this.idBenhVien = idBenhVien;
    }

    public String getTenBenhVien() {
        return tenBenhVien;
    }

    public BlcRegisterPrivateKey tenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
        return this;
    }

    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public String getUuidAcccount() {
        return uuidAcccount;
    }

    public BlcRegisterPrivateKey uuidAcccount(String uuidAcccount) {
        this.uuidAcccount = uuidAcccount;
        return this;
    }

    public void setUuidAcccount(String uuidAcccount) {
        this.uuidAcccount = uuidAcccount;
    }

    public String getUuidGCSDB() {
        return uuidGCSDB;
    }

    public BlcRegisterPrivateKey uuidGCSDB(String uuidGCSDB) {
        this.uuidGCSDB = uuidGCSDB;
        return this;
    }

    public void setUuidGCSDB(String uuidGCSDB) {
        this.uuidGCSDB = uuidGCSDB;
    }

    public String getIdUserCreate() {
        return idUserCreate;
    }

    public BlcRegisterPrivateKey idUserCreate(String idUserCreate) {
        this.idUserCreate = idUserCreate;
        return this;
    }

    public void setIdUserCreate(String idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public String getCodeSoft() {
        return codeSoft;
    }

    public BlcRegisterPrivateKey codeSoft(String codeSoft) {
        this.codeSoft = codeSoft;
        return this;
    }

    public void setCodeSoft(String codeSoft) {
        this.codeSoft = codeSoft;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public BlcRegisterPrivateKey publicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAddressKey() {
        return addressKey;
    }

    public BlcRegisterPrivateKey addressKey(String addressKey) {
        this.addressKey = addressKey;
        return this;
    }

    public void setAddressKey(String addressKey) {
        this.addressKey = addressKey;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public BlcRegisterPrivateKey createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public BlcRegisterPrivateKey modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public BlcRegisterPrivateKey status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BlcGiayChungSinh getBlcGiayChungSinh() {
        return blcGiayChungSinh;
    }

    public BlcRegisterPrivateKey blcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
        this.blcGiayChungSinh = blcGiayChungSinh;
        return this;
    }

    public void setBlcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
        this.blcGiayChungSinh = blcGiayChungSinh;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlcRegisterPrivateKey)) {
            return false;
        }
        return id != null && id.equals(((BlcRegisterPrivateKey) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BlcRegisterPrivateKey{" +
            "id=" + getId() +
            ", idBenhVien='" + getIdBenhVien() + "'" +
            ", tenBenhVien='" + getTenBenhVien() + "'" +
            ", uuidAcccount='" + getUuidAcccount() + "'" +
            ", uuidGCSDB='" + getUuidGCSDB() + "'" +
            ", idUserCreate='" + getIdUserCreate() + "'" +
            ", codeSoft='" + getCodeSoft() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            ", addressKey='" + getAddressKey() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
