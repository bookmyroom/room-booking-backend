package pl.booking.bookmyroom.reservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date reservationStart, reservationEnd;
    private Integer hotelsId, roomId, userId;

    public boolean isCollidingWith(Date otherReservationStart, Date otherReservationEnd){
        if (this.reservationStart.before(otherReservationStart) && this.reservationEnd.after(otherReservationEnd)){
            return true;
        }
        if (this.reservationEnd.after(otherReservationEnd) && this.reservationStart.before(otherReservationStart)){
            return true;
        }
        if (this.reservationStart.equals(otherReservationStart) || this.reservationEnd.equals(otherReservationEnd)){
            return true;
        }
        //TODO one more condition
        return false;
    }
}
