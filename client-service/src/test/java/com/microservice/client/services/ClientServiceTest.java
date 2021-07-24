package com.microservice.client.services;

import com.microservice.client.builder.ClientBuilder;
import com.microservice.client.builder.FilterClientBuilder;
import com.microservice.client.domain.Client;
import com.microservice.client.repositoties.ClientRepository;
import com.microservice.client.services.dto.ClientDTO;
import com.microservice.client.services.dto.FilterClientDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClientServiceTest {

    private ClientBuilder clientBuilder;

    private FilterClientBuilder filterClientBuilder;

    @Autowired
    private ClientService clienteService;

    @MockBean
    private ClientRepository clientRepository;

    @Before
    public void setUp() {

        clientBuilder = new ClientBuilder();

        filterClientBuilder = new FilterClientBuilder();
    }

    @Test
    public void whenFindByIdThenReturnClient() {

        ClientDTO clientDTO = clientBuilder.getClientDTO();
        given(clientRepository.findById(clientBuilder.getClientDTO().getId())).willReturn(Optional.of(clientBuilder.getClientEntity()));
        clienteService.findById(clientDTO.getId());
        verify(clientRepository, VerificationModeFactory.times(1)).findById(any());
    }


    @Test
    public void whenFindAllThenReturnClient() {

        Integer page = 0;
        Integer linesPerPage = 10;
        String direction = "ASC";
        String orderBy = "name";

        given(clientRepository.findAll((PageRequest) any())).willReturn(new PageImpl<>(Arrays.asList(clientBuilder.getClientEntity())));
        clienteService.findAll(page, linesPerPage, orderBy, direction, null);
        verify(clientRepository, VerificationModeFactory.times(1)).findAll((PageRequest) any());
    }

    @Test
    public void whenFindAllFilterThenReturnClient() {

        Integer page = 0;
        Integer linesPerPage = 10;
        String direction = "ASC";
        String orderBy = "name";

        given(clientRepository.findAll((Specification<Client>) any(), (PageRequest) any())).willReturn(new PageImpl<>(Arrays.asList(clientBuilder.getClientEntity())));
        clienteService.findAll(page, linesPerPage, orderBy, direction, filterClientBuilder.getFilterClientDTO());
        verify(clientRepository, VerificationModeFactory.times(1)).findAll((Specification<Client>) any(), (PageRequest) any());
    }

    @Test
    public void whenInsertThenReturnSucess() {
        ClientDTO clientDTO = clientBuilder.getClientDTO();
        given(clientRepository.save(any())).willReturn(clientBuilder.getClientEntity());
        clienteService.insert(clientDTO);
        verify(clientRepository, VerificationModeFactory.times(1)).save(any());
    }

    @Test
    public void whenUpdateThenReturnSucess() {
        ClientDTO clientDTO = clientBuilder.getClientDTO();
        given(clientRepository.save(any())).willReturn(clientBuilder.getClientEntity());
        clienteService.update(clientDTO, clientDTO.getId());
        verify(clientRepository, VerificationModeFactory.times(1)).save(any());
    }

    @Test
    public void whenDeleteThenReturnSucess() {

        Client client = clientBuilder.getClientEntity();

        given(clientRepository.findById(client.getId())).willReturn(Optional.of(client));
        doNothing().when(clientRepository).delete(Mockito.any());
        clienteService.delete(client.getId());
        verify(clientRepository, VerificationModeFactory.times(1)).deleteById(client.getId());
    }

}
