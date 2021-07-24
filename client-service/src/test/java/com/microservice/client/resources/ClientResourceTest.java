package com.microservice.client.resources;


import com.microservice.client.builder.ClientBuilder;
import com.microservice.client.resources.util.TestUtil;
import com.microservice.client.services.ClientService;
import com.microservice.client.services.dto.ClientDTO;
import com.microservice.client.services.dto.FilterClientDTO;
import com.microservice.client.services.exceptions.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientResource.class)
@ActiveProfiles("test")
public class ClientResourceTest {
    public static final String BASE_URL = "/client";

    @Autowired
    private MockMvc mvc;

    private ClientBuilder clientBuilder;

    @MockBean
    private ClientService clientService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        clientBuilder = new ClientBuilder();
    }

    @Test
    public void whenInsertClientThenReturnStatusCreate() throws Exception {

        ClientDTO obj = clientBuilder.getClientDTO();

        given(clientService.insert(Mockito.any())).willReturn(obj.getId());

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(obj)))
                .andExpect(status().isCreated());

        verify(clientService, VerificationModeFactory.times(1)).insert(Mockito.any());
        reset(clientService);
    }

    @Test
    public void whenFindAllClientThenReturnStatusOk() throws Exception {

        given(clientService.findAll(any(), any(), any(), any())).willReturn(null);

        mvc.perform(get(BASE_URL )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService, VerificationModeFactory.times(1)).findAll(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        reset(clientService);
    }

    @Test
    public void whenFindFilterClientThenReturnStatusOk() throws Exception {

        FilterClientDTO obj = new FilterClientDTO();

        given(clientService.findAll(any(), any(), any(), any(), any())).willReturn(null);

        mvc.perform(post(BASE_URL + "/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(obj)))
                .andExpect(status().isOk());

        verify(clientService, VerificationModeFactory.times(1)).findAll(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        reset(clientService);
    }

    @Test
    public void whenFindByIdClientThenReturnStatusOk() throws Exception {

        given(clientService.findById(Mockito.any())).willReturn(clientBuilder.getClientDTO());

        mvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService, VerificationModeFactory.times(1)).findById(Mockito.any());
        reset(clientService);
    }


    @Test
    public void whenUpdateClientThenReturnStatusNotContent() throws Exception {

        ClientDTO obj = clientBuilder.getClientDTO();

        given(clientService.update(Mockito.any(), Mockito.any())).willReturn(obj);

        mvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(obj)))
                .andExpect(status().isNoContent());

        verify(clientService, VerificationModeFactory.times(1)).update(Mockito.any(), Mockito.any());
        reset(clientService);
    }

    @Test
    public void whenDeleteByIdClientThenReturnStatusOk() throws Exception {

        doNothing().when(clientService).delete(Mockito.any());

        mvc.perform(delete(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(clientService, VerificationModeFactory.times(1)).delete(Mockito.any());
        reset(clientService);
    }


    @Test
    public void whenInsertClientThenReturnErrorValid() throws Exception {

        ClientDTO obj = new ClientDTO();

        given(clientService.insert(Mockito.any())).willReturn(obj.getId());

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.toJson(obj)))
                .andExpect(status().isBadRequest());

        reset(clientService);
    }


    @Test
    public void whenFindByIdClientThenReturnErrorNotFound() throws Exception {

        given(clientService.findById(Mockito.any())).willThrow(new ObjectNotFoundException("Registro não encontrado"));

        mvc.perform(get(BASE_URL + "/54654")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(clientService, VerificationModeFactory.times(1)).findById(Mockito.any());
        reset(clientService);
    }

}
