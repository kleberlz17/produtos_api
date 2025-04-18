package kleberlz.apiprodutos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kleberlz.apiprodutos.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
	
	Client findByClientId(String clientId);

}
