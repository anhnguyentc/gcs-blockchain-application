package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.BlcGiayKhamSucKhoe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BlcGiayKhamSucKhoe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlcGiayKhamSucKhoeRepository extends JpaRepository<BlcGiayKhamSucKhoe, Long>, JpaSpecificationExecutor<BlcGiayKhamSucKhoe> {

}
