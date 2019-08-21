package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.BlcGiayChungSinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BlcGiayChungSinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlcGiayChungSinhRepository extends JpaRepository<BlcGiayChungSinh, Long>, JpaSpecificationExecutor<BlcGiayChungSinh> {

}
