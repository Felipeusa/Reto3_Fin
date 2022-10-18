package com.example.reto3.Repository;

import com.example.reto3.Model.Client;
import com.example.reto3.Model.DTOs.CountClient;
import com.example.reto3.Model.Reservation;

import com.example.reto3.Repository.CrudRepository.ReservationCrudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Crist
 */
@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }

    public List<CountClient> getTopClient(){
        List<CountClient> respuesta = new ArrayList<>();
        List<Object[]> reporte = reservationCrudRepository.contarTotalReservasPorClient();

        for(int x = 0; x<reporte.size(); x++){
            respuesta.add(new CountClient((Long) reporte.get(x)[1], (Client) reporte.get(x)[0]));
        }
        return respuesta;
    }

    public List<Reservation> getReservationPeriod(Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndDevolutionDateBefore(a,b);
    }

    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }
}

