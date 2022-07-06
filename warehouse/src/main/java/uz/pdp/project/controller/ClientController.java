package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Client;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public Page<Client> getClient(@RequestParam int page) {
        return clientService.getClient(page);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Result addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/{id}")
    public Result editClient(@PathVariable int id, Client client) {
        return clientService.editClient(id, client);
    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable int id) {
        return clientService.deleteClient(id);
    }
}
