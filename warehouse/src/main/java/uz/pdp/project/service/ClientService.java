package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.Client;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Page<Client> getClient(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(int id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public Result addClient(Client client) {
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new Result("exists bu phoneNumber", false);
        Client client1 = new Client();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("added", true);
    }

    public Result editClient(int id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("client not found", false);
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new Result("exists bu phoneNumber", false);
        optionalClient.get().setName(client.getName());
        optionalClient.get().setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(optionalClient.get());
        return new Result("edited", true);
    }

    public Result deleteClient(int id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("client not found", false);
    }
}
