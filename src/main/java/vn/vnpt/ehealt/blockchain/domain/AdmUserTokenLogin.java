package vn.vnpt.ehealt.blockchain.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AdmUserTokenLogin.
 */
@Entity
@Table(name = "adm_user_tokenlogin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdmUserTokenLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "user_name", length = 255)
    private String userName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "login_ip")
    private String loginIP;

    @Column(name = "active")
    private String active;

    @Column(name = "he_thong")
    private String heThong;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public AdmUserTokenLogin userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AdmUserTokenLogin createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public AdmUserTokenLogin expirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public AdmUserTokenLogin loginIP(String loginIP) {
        this.loginIP = loginIP;
        return this;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getActive() {
        return active;
    }

    public AdmUserTokenLogin active(String active) {
        this.active = active;
        return this;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getHeThong() {
        return heThong;
    }

    public AdmUserTokenLogin heThong(String heThong) {
        this.heThong = heThong;
        return this;
    }

    public void setHeThong(String heThong) {
        this.heThong = heThong;
    }

    public String getTokenType() {
        return tokenType;
    }

    public AdmUserTokenLogin tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getStatus() {
        return status;
    }

    public AdmUserTokenLogin status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmUserTokenLogin)) {
            return false;
        }
        return id != null && id.equals(((AdmUserTokenLogin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdmUserTokenLogin{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", loginIP='" + getLoginIP() + "'" +
            ", active='" + getActive() + "'" +
            ", heThong='" + getHeThong() + "'" +
            ", tokenType='" + getTokenType() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
