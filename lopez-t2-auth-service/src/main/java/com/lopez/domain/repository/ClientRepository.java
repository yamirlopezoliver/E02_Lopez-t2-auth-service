package com.lopez.domain.repository;

import com.lopez.domain.model.Client;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findByClientId(String clientId);
}
