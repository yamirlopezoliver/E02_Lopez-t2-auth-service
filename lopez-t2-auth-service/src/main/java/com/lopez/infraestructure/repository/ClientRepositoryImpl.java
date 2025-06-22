package com.lopez.infraestructure.repository;

import com.lopez.domain.model.Client;
import com.lopez.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {
    private final JpaClientRepository jpaClientRepository;
    @Override
    public Optional<Client> findByClientId(String clientId) {
        return jpaClientRepository.findByClientId(clientId);
    }
}
