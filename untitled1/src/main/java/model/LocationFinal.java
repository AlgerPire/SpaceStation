package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LocationFinal {
    private String city;
    private String principalSubdivision;
    private String countryName;
    private String continent;
    private String locality;
}
