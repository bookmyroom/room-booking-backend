package pl.booking.bookmyroom.hotel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;

    private String street;

    private String streetNumber;

    private String phoneNumber;

    @Max(5)
    private Integer standard;

    private Integer corporationId;
}
