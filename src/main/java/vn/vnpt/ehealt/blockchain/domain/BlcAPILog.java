package vn.vnpt.ehealt.blockchain.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A BlcAPILog.
 */
@Entity
@Table(name = "blc_api_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BlcAPILog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "status")
    private String status;

    @Column(name = "message_status")
    private String messageStatus;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "so_gcs")
    private String soGCS;

    @Column(name = "so_sogcs")
    private String soSOGCS;

    @Column(name = "request_content")
    private String requestContent;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "loaded_time")
    private LocalDate loadedTime;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "id_benhvien")
    private String idBenhvien;

    @Column(name = "ten_benhvien")
    private String tenBenhvien;

    @Column(name = "process_time")
    private Integer processTime;

    @Column(name = "response_time")
    private Integer responseTime;

    @OneToOne(mappedBy = "blcAPILog")
    @JsonIgnore
    private BlcGiayChungSinh blcGiayChungSinh;

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

    public BlcAPILog uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public BlcAPILog status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public BlcAPILog messageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMethodName() {
        return methodName;
    }

    public BlcAPILog methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSoGCS() {
        return soGCS;
    }

    public BlcAPILog soGCS(String soGCS) {
        this.soGCS = soGCS;
        return this;
    }

    public void setSoGCS(String soGCS) {
        this.soGCS = soGCS;
    }

    public String getSoSOGCS() {
        return soSOGCS;
    }

    public BlcAPILog soSOGCS(String soSOGCS) {
        this.soSOGCS = soSOGCS;
        return this;
    }

    public void setSoSOGCS(String soSOGCS) {
        this.soSOGCS = soSOGCS;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public BlcAPILog requestContent(String requestContent) {
        this.requestContent = requestContent;
        return this;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public BlcAPILog errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDate getLoadedTime() {
        return loadedTime;
    }

    public BlcAPILog loadedTime(LocalDate loadedTime) {
        this.loadedTime = loadedTime;
        return this;
    }

    public void setLoadedTime(LocalDate loadedTime) {
        this.loadedTime = loadedTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public BlcAPILog ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIdBenhvien() {
        return idBenhvien;
    }

    public BlcAPILog idBenhvien(String idBenhvien) {
        this.idBenhvien = idBenhvien;
        return this;
    }

    public void setIdBenhvien(String idBenhvien) {
        this.idBenhvien = idBenhvien;
    }

    public String getTenBenhvien() {
        return tenBenhvien;
    }

    public BlcAPILog tenBenhvien(String tenBenhvien) {
        this.tenBenhvien = tenBenhvien;
        return this;
    }

    public void setTenBenhvien(String tenBenhvien) {
        this.tenBenhvien = tenBenhvien;
    }

    public Integer getProcessTime() {
        return processTime;
    }

    public BlcAPILog processTime(Integer processTime) {
        this.processTime = processTime;
        return this;
    }

    public void setProcessTime(Integer processTime) {
        this.processTime = processTime;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public BlcAPILog responseTime(Integer responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public BlcGiayChungSinh getBlcGiayChungSinh() {
        return blcGiayChungSinh;
    }

    public BlcAPILog blcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
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
        if (!(o instanceof BlcAPILog)) {
            return false;
        }
        return id != null && id.equals(((BlcAPILog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BlcAPILog{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", status='" + getStatus() + "'" +
            ", messageStatus='" + getMessageStatus() + "'" +
            ", methodName='" + getMethodName() + "'" +
            ", soGCS='" + getSoGCS() + "'" +
            ", soSOGCS='" + getSoSOGCS() + "'" +
            ", requestContent='" + getRequestContent() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", loadedTime='" + getLoadedTime() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", idBenhvien='" + getIdBenhvien() + "'" +
            ", tenBenhvien='" + getTenBenhvien() + "'" +
            ", processTime=" + getProcessTime() +
            ", responseTime=" + getResponseTime() +
            "}";
    }
}
