package com.example.reto3.Repository.CrudRepository;

import com.example.reto3.Model.Reservation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Crist
 */
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    /*@Query("SELECT c.machine, COUNT(c.machine) FROM Reservation AS c GROUP BY c.machine ORDER BY COUNT(c.machine) DESC")
    public List<Object[]> contarTotalReservasPorMaquina ();*/

    @Query ("SELECT c.client, COUNT(c.client) FROM Reservation AS c GROUP BY c.client ORDER BY COUNT(c.client) DESC")
    public List<Object[]> contarTotalReservasPorClient ();

     public List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore (Date d1, Date d2);
     public List<Reservation> findAllByStatus(String status);


}
