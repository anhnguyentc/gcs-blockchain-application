package vn.vnpt.ehealt.blockchain.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DanToc.
 */
@Entity
@Table(name = "blc_dan_toc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DanToc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_dan_toc")
    private String maDanToc;

    @Column(name = "ten_dan_toc")
    private String tenDanToc;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(unique = true)
    private BlcGiayChungSinh blcGiayChungSinh;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDanToc() {
        return maDanToc;
    }

    public DanToc maDanToc(String maDanToc) {
        this.maDanToc = maDanToc;
        return this;
    }

    public void setMaDanToc(String maDanToc) {
        this.maDanToc = maDanToc;
    }

    public String getTenDanToc() {
        return tenDanToc;
    }

    public DanToc tenDanToc(String tenDanToc) {
        this.tenDanToc = tenDanToc;
        return this;
    }

    public void setTenDanToc(String tenDanToc) {
        this.tenDanToc = tenDanToc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public DanToc ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getStatus() {
        return status;
    }

    public DanToc status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BlcGiayChungSinh getBlcGiayChungSinh() {
        return blcGiayChungSinh;
    }

    public DanToc blcGiayChungSinh(BlcGiayChungSinh blcGiayChungSinh) {
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
        if (!(o instanceof DanToc)) {
            return false;
        }
        return id != null && id.equals(((DanToc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DanToc{" +
            "id=" + getId() +
            ", maDanToc='" + getMaDanToc() + "'" +
            ", tenDanToc='" + getTenDanToc() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
