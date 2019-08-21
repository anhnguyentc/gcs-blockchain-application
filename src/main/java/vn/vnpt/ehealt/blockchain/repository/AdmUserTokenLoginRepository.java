package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.AdmUserTokenLogin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdmUserTokenLogin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmUserTokenLoginRepository extends JpaRepository<AdmUserTokenLogin, Long>, JpaSpecificationExecutor<AdmUserTokenLogin> {

}
