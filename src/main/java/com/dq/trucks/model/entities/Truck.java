package com.dq.trucks.model.entities;


import com.dq.trucks.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Truck extends Entity {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String manufacturer;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private String serialNumber;
}
