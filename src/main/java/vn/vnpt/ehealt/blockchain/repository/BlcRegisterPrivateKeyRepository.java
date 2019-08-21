package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.BlcRegisterPrivateKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BlcRegisterPrivateKey entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlcRegisterPrivateKeyRepository extends JpaRepository<BlcRegisterPrivateKey, Long>, JpaSpecificationExecutor<BlcRegisterPrivateKey> {

}
