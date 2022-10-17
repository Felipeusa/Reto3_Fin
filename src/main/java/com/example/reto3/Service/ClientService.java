package com.example.reto3.Service;

import com.example.reto3.Model.Client;
import com.example.reto3.Repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Crist
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return (List<Client>) clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> clientEncontrado = clientRepository.getClient(client.getIdClient());
            if (clientEncontrado.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> clientEncontrado = clientRepository.getClient(client.getIdClient());
            if (!clientEncontrado.isEmpty()) {
                if (client.getPassword() != null) {
                    clientEncontrado.get().setPassword(client.getPassword());
                }
                if (client.getName() != null) {
                    clientEncontrado.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    clientEncontrado.get().setAge(client.getAge());
                }

                return clientRepository.save(clientEncontrado.get());
            }
        }
        return client;

    }

    public boolean deleteClient(int clientId) {
        Boolean resultado = getClient(clientId).map(clientPorEliminar -> {
            clientRepository.delete(clientPorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}

