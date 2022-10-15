package com.example.reto3.Repository;

import com.example.reto3.Model.Machine;

import com.example.reto3.Repository.CrudRepository.MachineCrudRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Crist
 */
@Repository
public class MachineRepository {

    @Autowired
    private MachineCrudRepository machineCrudRepository;

    public List<Machine> getAll() {
        return (List<Machine>) machineCrudRepository.findAll();
    }

    public Optional<Machine> getMachine(int id) {
        return machineCrudRepository.findById(id);
    }

    public Machine save(Machine machine) {
        return machineCrudRepository.save(machine);
    }

    public void delete(Machine machine) {
        machineCrudRepository.delete(machine);
    }
}
