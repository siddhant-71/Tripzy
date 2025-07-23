package com.tripzy.Configurations.RequiredDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FlightSearchResponse {
    List<FlightDetails> flights=new ArrayList<>();
}
