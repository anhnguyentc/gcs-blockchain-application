package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.BenhVien;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BenhVien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BenhVienRepository extends JpaRepository<BenhVien, Long>, JpaSpecificationExecutor<BenhVien> {

}
