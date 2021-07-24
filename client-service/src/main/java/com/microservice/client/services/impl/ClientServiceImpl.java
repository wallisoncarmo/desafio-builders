package com.microservice.client.services.impl;

import com.microservice.client.domain.Client;
import com.microservice.client.repositoties.ClientRepository;
import com.microservice.client.repositoties.specification.ClientSpecifications;
import com.microservice.client.services.ClientService;
import com.microservice.client.services.dto.ClientDTO;
import com.microservice.client.services.dto.FilterClientDTO;
import com.microservice.client.services.exceptions.ObjectNotFoundException;
import com.microservice.client.services.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Long insert(ClientDTO objDTO) {
        objDTO.setId(null);
        Client obj = save(objDTO);
        return obj.getId();
    }

    @Override
    public ClientDTO update(ClientDTO objDTO, Long id) {
        objDTO.setId(id);
        this.save(objDTO);
        return objDTO;
    }

    private Client save(ClientDTO objDTO) {
        Client obj = clientMapper.toEntity(objDTO);
        repository.save(obj);
        return obj;
    }

    @Override
    public Page<ClientDTO> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return this.repository.findAll(pageRequest).map(obj -> clientMapper.toDto(obj));
    }

    @Override
    public Page<ClientDTO> findAll(Integer page, Integer linesPerPage, String orderBy, String direction, FilterClientDTO filter) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<Client> list;

        if (Objects.isNull(filter)) {
            return this.findAll(page, linesPerPage, orderBy, direction);
        } else {

            Specification<Client> spec = Specification.where(ClientSpecifications.likeName(filter.getName()));

            if (Objects.nonNull(filter.getCpf())) {
                spec = spec.and(ClientSpecifications.equalsCpf(filter.getCpf()));
            }

            if (Objects.nonNull(filter.getCep())) {
                spec = spec.and(ClientSpecifications.equalsCep(filter.getCep()));
            }

            if (Objects.nonNull(filter.getPublicPlace())) {
                spec = spec.and(ClientSpecifications.likePublicPlace(filter.getPublicPlace()));
            }

            if (Objects.nonNull(filter.getNeighborhood())) {
                spec = spec.and(ClientSpecifications.likeNeighborhood(filter.getNeighborhood()));
            }

            if (Objects.nonNull(filter.getCity())) {
                spec = spec.and(ClientSpecifications.likeCity(filter.getCity()));
            }

            if (Objects.nonNull(filter.getUf())) {
                spec = spec.and(ClientSpecifications.equalsUF(filter.getUf()));
            }

            if (Objects.nonNull(filter.getComplement())) {
                spec = spec.and(ClientSpecifications.likeComplement(filter.getComplement()));
            }

            if (Objects.nonNull(filter.getIdade())) {
                spec = spec.and(ClientSpecifications.equalsIdade(filter.getIdade()));
            }
            return this.repository.findAll(spec, pageRequest).map(obj -> clientMapper.toDto(obj));
        }
    }

    @Override
    public ClientDTO findById(Long id) {
        Client obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado"));
        return clientMapper.toDto(obj);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
