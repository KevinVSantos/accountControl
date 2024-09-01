package br.com.KevinVSantos.AccountControl.controller;

import br.com.KevinVSantos.AccountControl.domain.dto.client.ClientDto;
import br.com.KevinVSantos.AccountControl.domain.entity.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.KevinVSantos.AccountControl.service.ClientService;


@RestController
@RequestMapping(value = "/client")
public class ClientController extends AbstractController<String, ClientService, Client> {

}
