package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.BlcAPILog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BlcAPILog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlcAPILogRepository extends JpaRepository<BlcAPILog, Long>, JpaSpecificationExecutor<BlcAPILog> {

}
