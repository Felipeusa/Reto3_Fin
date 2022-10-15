package com.example.reto3.Service;

import com.example.reto3.Model.Machine;
import com.example.reto3.Repository.MachineRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Crist
 */
@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAll() {
        return (List<Machine>) machineRepository.getAll();
    }

    public Optional<Machine> getMachine(int id) {
        return machineRepository.getMachine(id);
    }

    public Machine save(Machine machine) {
        if (machine.getId() == null) {
            return machineRepository.save(machine);
        } else {
            Optional<Machine> machineEncontrado = machineRepository.getMachine(machine.getId());
            if (machineEncontrado.isEmpty()) {
                return machineRepository.save(machine);
            } else {
                return machine;
            }
        }
    }

    public Machine update(Machine machine) {
        if (machine.getId() != null) {
            Optional<Machine> machineEncontrado = machineRepository.getMachine(machine.getId());
            if (!machineEncontrado.isEmpty()) {
                if (machine.getBrand() != null) {
                    machineEncontrado.get().setBrand(machine.getBrand());
                }
                if (machine.getName() != null) {
                    machineEncontrado.get().setName(machine.getName());
                }
                if (machine.getDescription() != null) {
                    machineEncontrado.get().setDescription(machine.getDescription());
                }
                if (machine.getYear() != null) {
                    machineEncontrado.get().setYear(machine.getYear());
                }

                return machineRepository.save(machineEncontrado.get());
            }
        }
        return machine;

    }

    public boolean deleteMachine(int machineId) {
        Boolean resultado = getMachine(machineId).map(machinePorEliminar -> {
            machineRepository.delete(machinePorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}
