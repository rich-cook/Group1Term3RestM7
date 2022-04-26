package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "packages")
public class Package {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PackageId", nullable = false)
    private Integer packageId;

    @Column(name = "PkgName", nullable = false, length = 50)
    private String pkgName;

    @Column(name = "PkgStartDate")
    private Date pkgStartDate;

    @Column(name = "PkgEndDate")
    private Date pkgEndDate;

    @Column(name = "PkgDesc", length = 50)
    private String pkgDesc;

    @Column(name = "PkgBasePrice", nullable = false, precision = 19, scale = 4)
    private Double pkgBasePrice;

    @Column(name = "PkgAgencyCommission", precision = 19, scale = 4)
    private Double pkgAgencyCommission;

    public Integer getId() {
        return packageId;
    }

    public void setId(Integer id) {
        this.packageId = id;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Date getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(Date pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    public Date getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public Double getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(Double pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    public Double getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(Double pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

}