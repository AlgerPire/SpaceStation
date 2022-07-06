package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table (name = "info")
public class Info {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    private String city;
    private String county;
    private String division;
    private String locality;
    private String continent;
    private Time ora;

}
