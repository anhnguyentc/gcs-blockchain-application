package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.DonViHanhChinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DonViHanhChinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonViHanhChinhRepository extends JpaRepository<DonViHanhChinh, Long>, JpaSpecificationExecutor<DonViHanhChinh> {

}
