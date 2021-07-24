package com.microservice.client.resources;

import com.microservice.client.services.dto.ClientDTO;
import com.microservice.client.services.ClientService;
import com.microservice.client.services.dto.FilterClientDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @ApiOperation(value = "Buscar Todos os Clientes")
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(service.findAll(page, linesPerPage, orderBy, direction));
    }

    @ApiOperation(value = "Buscar Todos os Clientes por filtro")
    @PostMapping(value = "/filter")
    public ResponseEntity<Page<ClientDTO>> findAllFilter(
            @RequestBody  FilterClientDTO filter,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(service.findAll(page, linesPerPage, orderBy, direction, filter));
    }

    @ApiOperation(value = "Buscar por um Cliente pelo seu id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiOperation(value = "inserir um novo Cliente")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.insert(obj)).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Atualizar um Cliente")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody ClientDTO obj) {
        service.update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Deletar um Cliente")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
