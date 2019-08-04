package vn.vnpt.ehealt.blockchain.repository;

import vn.vnpt.ehealt.blockchain.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
