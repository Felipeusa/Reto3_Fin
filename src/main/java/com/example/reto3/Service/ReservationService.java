package com.example.reto3.Service;


import com.example.reto3.Model.DTOs.CountClient;
import com.example.reto3.Model.DTOs.Status;
import com.example.reto3.Model.Reservation;
import com.example.reto3.Repository.ReservationRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Crist
 */
@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation()== null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservationEncontrado = reservationRepository.getReservation(reservation.getIdReservation());
            if (reservationEncontrado.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation()!= null) {
            Optional<Reservation> reservationEncontrado = reservationRepository.getReservation(reservation.getIdReservation());
            if (!reservationEncontrado.isEmpty()) {
                if (reservation.getStartDate()!= null) {
                    reservationEncontrado.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate()!= null) {
                    reservationEncontrado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus()!= null) {
                    reservationEncontrado.get().setStatus(reservation.getStatus());
                }

                return reservationRepository.save(reservationEncontrado.get());
            }
        }
        return reservation;

    }

    public boolean deleteReservation(int reservationId) {
        Boolean resultado = getReservation(reservationId).map(reservationPorEliminar -> {
            reservationRepository.delete(reservationPorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

    public List<CountClient> getTopClient(){
        return  reservationRepository.getTopClient();
    }

    public List<Reservation> getReservationPeriod(String dateA, String dateB){
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        Date a = new Date();
        Date b = new Date();
        try{
            a = parseador.parse(dateA);
            b = parseador.parse(dateB);
        }catch (ParseException exception){
            exception.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.getReservationPeriod(a,b);
        }else {
            return  new ArrayList<>();
        }
    }

    public Status getReservationStatus(){
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");
        return new Status((long) completed.size(), (long) cancelled.size());

    }
}
