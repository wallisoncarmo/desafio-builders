package com.microservice.client.services.mapper;

import com.microservice.client.domain.Client;
import com.microservice.client.services.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
}
