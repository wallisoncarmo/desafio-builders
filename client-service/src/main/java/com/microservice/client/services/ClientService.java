package com.microservice.client.services;

import com.microservice.client.services.dto.ClientDTO;
import com.microservice.client.services.dto.FilterClientDTO;
import org.springframework.data.domain.Page;

public interface ClientService {

    public Long insert(ClientDTO obj);

    public ClientDTO update(ClientDTO obj, Long id);

    public Page<ClientDTO> findAll(Integer page, Integer linesPerPage, String orderBy, String direction);

    public Page<ClientDTO> findAll(Integer page, Integer linesPerPage, String orderBy, String direction, FilterClientDTO filter);

    public ClientDTO findById(Long id);

    public void delete(Long id);
}
