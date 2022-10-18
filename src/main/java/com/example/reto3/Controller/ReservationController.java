package com.example.reto3.Controller;

import com.example.reto3.Model.DTOs.CountClient;
import com.example.reto3.Model.DTOs.Status;
import com.example.reto3.Model.Message;
import com.example.reto3.Model.Reservation;
import com.example.reto3.Service.ReservationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id){
        return reservationService.getReservation(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation ) { return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/report-clients")
    public List<CountClient> getReportClients(){return reservationService.getTopClient();}

    @GetMapping("/report-dates/{date1}/{date2}")
    public List<Reservation> getReservationDate(@PathVariable("date1") String date1, @PathVariable ("date2") String date2){
        return reservationService.getReservationPeriod(date1, date2);
    }

    @GetMapping("/report-status")
    public Status getReportStatusReservations(){
        return reservationService.getReservationStatus();
    }



}
