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
 * Criteria class for the {@link vn.vnpt.ehealt.blockchain.domain.BlcAPILog} entity. This class is used
 * in {@link vn.vnpt.ehealt.blockchain.web.rest.BlcAPILogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /blc-api-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BlcAPILogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter uuid;

    private StringFilter status;

    private StringFilter messageStatus;

    private StringFilter methodName;

    private StringFilter soGCS;

    private StringFilter soSOGCS;

    private StringFilter requestContent;

    private StringFilter errorMessage;

    private LocalDateFilter loadedTime;

    private StringFilter ipAddress;

    private StringFilter idBenhvien;

    private StringFilter tenBenhvien;

    private IntegerFilter processTime;

    private IntegerFilter responseTime;

    private LongFilter blcGiayChungSinhId;

    public BlcAPILogCriteria(){
    }

    public BlcAPILogCriteria(BlcAPILogCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.uuid = other.uuid == null ? null : other.uuid.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.messageStatus = other.messageStatus == null ? null : other.messageStatus.copy();
        this.methodName = other.methodName == null ? null : other.methodName.copy();
        this.soGCS = other.soGCS == null ? null : other.soGCS.copy();
        this.soSOGCS = other.soSOGCS == null ? null : other.soSOGCS.copy();
        this.requestContent = other.requestContent == null ? null : other.requestContent.copy();
        this.errorMessage = other.errorMessage == null ? null : other.errorMessage.copy();
        this.loadedTime = other.loadedTime == null ? null : other.loadedTime.copy();
        this.ipAddress = other.ipAddress == null ? null : other.ipAddress.copy();
        this.idBenhvien = other.idBenhvien == null ? null : other.idBenhvien.copy();
        this.tenBenhvien = other.tenBenhvien == null ? null : other.tenBenhvien.copy();
        this.processTime = other.processTime == null ? null : other.processTime.copy();
        this.responseTime = other.responseTime == null ? null : other.responseTime.copy();
        this.blcGiayChungSinhId = other.blcGiayChungSinhId == null ? null : other.blcGiayChungSinhId.copy();
    }

    @Override
    public BlcAPILogCriteria copy() {
        return new BlcAPILogCriteria(this);
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

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(StringFilter messageStatus) {
        this.messageStatus = messageStatus;
    }

    public StringFilter getMethodName() {
        return methodName;
    }

    public void setMethodName(StringFilter methodName) {
        this.methodName = methodName;
    }

    public StringFilter getSoGCS() {
        return soGCS;
    }

    public void setSoGCS(StringFilter soGCS) {
        this.soGCS = soGCS;
    }

    public StringFilter getSoSOGCS() {
        return soSOGCS;
    }

    public void setSoSOGCS(StringFilter soSOGCS) {
        this.soSOGCS = soSOGCS;
    }

    public StringFilter getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(StringFilter requestContent) {
        this.requestContent = requestContent;
    }

    public StringFilter getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(StringFilter errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateFilter getLoadedTime() {
        return loadedTime;
    }

    public void setLoadedTime(LocalDateFilter loadedTime) {
        this.loadedTime = loadedTime;
    }

    public StringFilter getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(StringFilter ipAddress) {
        this.ipAddress = ipAddress;
    }

    public StringFilter getIdBenhvien() {
        return idBenhvien;
    }

    public void setIdBenhvien(StringFilter idBenhvien) {
        this.idBenhvien = idBenhvien;
    }

    public StringFilter getTenBenhvien() {
        return tenBenhvien;
    }

    public void setTenBenhvien(StringFilter tenBenhvien) {
        this.tenBenhvien = tenBenhvien;
    }

    public IntegerFilter getProcessTime() {
        return processTime;
    }

    public void setProcessTime(IntegerFilter processTime) {
        this.processTime = processTime;
    }

    public IntegerFilter getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(IntegerFilter responseTime) {
        this.responseTime = responseTime;
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
        final BlcAPILogCriteria that = (BlcAPILogCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uuid, that.uuid) &&
            Objects.equals(status, that.status) &&
            Objects.equals(messageStatus, that.messageStatus) &&
            Objects.equals(methodName, that.methodName) &&
            Objects.equals(soGCS, that.soGCS) &&
            Objects.equals(soSOGCS, that.soSOGCS) &&
            Objects.equals(requestContent, that.requestContent) &&
            Objects.equals(errorMessage, that.errorMessage) &&
            Objects.equals(loadedTime, that.loadedTime) &&
            Objects.equals(ipAddress, that.ipAddress) &&
            Objects.equals(idBenhvien, that.idBenhvien) &&
            Objects.equals(tenBenhvien, that.tenBenhvien) &&
            Objects.equals(processTime, that.processTime) &&
            Objects.equals(responseTime, that.responseTime) &&
            Objects.equals(blcGiayChungSinhId, that.blcGiayChungSinhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uuid,
        status,
        messageStatus,
        methodName,
        soGCS,
        soSOGCS,
        requestContent,
        errorMessage,
        loadedTime,
        ipAddress,
        idBenhvien,
        tenBenhvien,
        processTime,
        responseTime,
        blcGiayChungSinhId
        );
    }

    @Override
    public String toString() {
        return "BlcAPILogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uuid != null ? "uuid=" + uuid + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (messageStatus != null ? "messageStatus=" + messageStatus + ", " : "") +
                (methodName != null ? "methodName=" + methodName + ", " : "") +
                (soGCS != null ? "soGCS=" + soGCS + ", " : "") +
                (soSOGCS != null ? "soSOGCS=" + soSOGCS + ", " : "") +
                (requestContent != null ? "requestContent=" + requestContent + ", " : "") +
                (errorMessage != null ? "errorMessage=" + errorMessage + ", " : "") +
                (loadedTime != null ? "loadedTime=" + loadedTime + ", " : "") +
                (ipAddress != null ? "ipAddress=" + ipAddress + ", " : "") +
                (idBenhvien != null ? "idBenhvien=" + idBenhvien + ", " : "") +
                (tenBenhvien != null ? "tenBenhvien=" + tenBenhvien + ", " : "") +
                (processTime != null ? "processTime=" + processTime + ", " : "") +
                (responseTime != null ? "responseTime=" + responseTime + ", " : "") +
                (blcGiayChungSinhId != null ? "blcGiayChungSinhId=" + blcGiayChungSinhId + ", " : "") +
            "}";
    }

}
