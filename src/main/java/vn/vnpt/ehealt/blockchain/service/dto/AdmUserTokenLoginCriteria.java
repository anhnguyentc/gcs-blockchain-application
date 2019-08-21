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
 * Criteria class for the {@link vn.vnpt.ehealt.blockchain.domain.AdmUserTokenLogin} entity. This class is used
 * in {@link vn.vnpt.ehealt.blockchain.web.rest.AdmUserTokenLoginResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /adm-user-token-logins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AdmUserTokenLoginCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter userName;

    private LocalDateFilter createdDate;

    private LocalDateFilter expirationDate;

    private StringFilter loginIP;

    private StringFilter active;

    private StringFilter heThong;

    private StringFilter tokenType;

    private IntegerFilter status;

    public AdmUserTokenLoginCriteria(){
    }

    public AdmUserTokenLoginCriteria(AdmUserTokenLoginCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.userName = other.userName == null ? null : other.userName.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.expirationDate = other.expirationDate == null ? null : other.expirationDate.copy();
        this.loginIP = other.loginIP == null ? null : other.loginIP.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.heThong = other.heThong == null ? null : other.heThong.copy();
        this.tokenType = other.tokenType == null ? null : other.tokenType.copy();
        this.status = other.status == null ? null : other.status.copy();
    }

    @Override
    public AdmUserTokenLoginCriteria copy() {
        return new AdmUserTokenLoginCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUserName() {
        return userName;
    }

    public void setUserName(StringFilter userName) {
        this.userName = userName;
    }

    public LocalDateFilter getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateFilter createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateFilter getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateFilter expirationDate) {
        this.expirationDate = expirationDate;
    }

    public StringFilter getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(StringFilter loginIP) {
        this.loginIP = loginIP;
    }

    public StringFilter getActive() {
        return active;
    }

    public void setActive(StringFilter active) {
        this.active = active;
    }

    public StringFilter getHeThong() {
        return heThong;
    }

    public void setHeThong(StringFilter heThong) {
        this.heThong = heThong;
    }

    public StringFilter getTokenType() {
        return tokenType;
    }

    public void setTokenType(StringFilter tokenType) {
        this.tokenType = tokenType;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AdmUserTokenLoginCriteria that = (AdmUserTokenLoginCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(expirationDate, that.expirationDate) &&
            Objects.equals(loginIP, that.loginIP) &&
            Objects.equals(active, that.active) &&
            Objects.equals(heThong, that.heThong) &&
            Objects.equals(tokenType, that.tokenType) &&
            Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        userName,
        createdDate,
        expirationDate,
        loginIP,
        active,
        heThong,
        tokenType,
        status
        );
    }

    @Override
    public String toString() {
        return "AdmUserTokenLoginCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (userName != null ? "userName=" + userName + ", " : "") +
                (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
                (expirationDate != null ? "expirationDate=" + expirationDate + ", " : "") +
                (loginIP != null ? "loginIP=" + loginIP + ", " : "") +
                (active != null ? "active=" + active + ", " : "") +
                (heThong != null ? "heThong=" + heThong + ", " : "") +
                (tokenType != null ? "tokenType=" + tokenType + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
            "}";
    }

}
