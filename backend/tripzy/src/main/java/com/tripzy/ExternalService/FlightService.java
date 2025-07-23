package com.tripzy.ExternalService;

import com.fasterxml.jackson.databind.JsonNode;
import com.tripzy.Configurations.RequiredDTO.FlightDetails;
import com.tripzy.Configurations.RequiredDTO.FlightSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FlightService {

    @Value("${serpapi.key}")
    private String apiKey;
    @Value("${serpapi.host}")
    private String host;

    @Autowired
    private WebClient webClient;
///search.json?engine=google_flights&departure_id=PEK&arrival_id=AUS&outbound_date=2025-07-20&return_date=2025-07-26&currency=USD&hl=en
    public FlightSearchResponse getFlights(String origin, String destination, String returnDate,String deptDate){
//        String originCode=new AirportCode().getAirportCode(origin).block();
//        String destinationCode=new AirportCode().getAirportCode(destination).block();
        JsonNode root= webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(host)
                        .path("/search.json")
                        .queryParam("engine","google_flights")
                        .queryParam("departure_id",origin)
                        .queryParam("arrival_id",destination)
                        .queryParam("outbound_date",deptDate)
                        .queryParam("return_date",returnDate)
                        .queryParam("hl","en")
                        .queryParam("currency","INR")
                        .queryParam("api_key",apiKey)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        assert root != null;
        JsonNode bestFlights=root.get("best_flights");
        JsonNode otherFlights=root.get("other_flights");
        FlightSearchResponse ans=new FlightSearchResponse();
        if (bestFlights!=null) {
            for(JsonNode flightTemp:bestFlights){
                try {
                    JsonNode firstLeg=flightTemp.get("flights").get(0);
                    FlightDetails flightDetails=new FlightDetails();
                    if(flightTemp.get("total_duration")!=null)flightDetails.setTotalDurationInMinutes(flightTemp.get("total_duration").asDouble());
                    else flightDetails.setTotalDurationInMinutes(0.0);
                    if(flightTemp.get("price")!=null)flightDetails.setPrice(flightTemp.get("price").asDouble());
                    else flightDetails.setPrice(0.0);
                    flightDetails.setDepartureAirportName(firstLeg.get("departure_airport").get("name").asText());
                    flightDetails.setDepartureAirportCode(firstLeg.get("departure_airport").get("id").asText());
                    flightDetails.setDepartureTime(LocalDateTime.parse(firstLeg.get("departure_airport").get("time").asText(),formatter));
                    flightDetails.setArrivalAirportName(firstLeg.get("arrival_airport").get("name").asText());
                    flightDetails.setArrivalAirportCode(firstLeg.get("arrival_airport").get("id").asText());
                    flightDetails.setArrivalTime(LocalDateTime.parse(firstLeg.get("arrival_airport").get("time").asText(),formatter));
                    flightDetails.setFlightNumber(firstLeg.get("flight_number").asText());
                    flightDetails.setAirline(firstLeg.get("airline").asText());
                    flightDetails.setLogo(flightTemp.get("airline_logo").asText());

                    ans.getFlights().add(flightDetails);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        if (otherFlights!=null) {
            for(JsonNode flightTemp:otherFlights){
                try {
                    JsonNode firstLeg=flightTemp.get("flights").get(0);
                    FlightDetails flightDetails=new FlightDetails();
                    if(flightTemp.get("total_duration")!=null)flightDetails.setTotalDurationInMinutes(flightTemp.get("total_duration").asDouble());
                    else flightDetails.setTotalDurationInMinutes(0.0);
                    if(flightTemp.get("price")!=null)flightDetails.setPrice(flightTemp.get("price").asDouble());
                    else flightDetails.setPrice(0.0);
                    flightDetails.setDepartureAirportName(firstLeg.get("departure_airport").get("name").asText());
                    flightDetails.setDepartureAirportCode(firstLeg.get("departure_airport").get("id").asText());
                    flightDetails.setDepartureTime(LocalDateTime.parse(firstLeg.get("departure_airport").get("time").asText(),formatter));
                    flightDetails.setArrivalAirportName(firstLeg.get("arrival_airport").get("name").asText());
                    flightDetails.setArrivalAirportCode(firstLeg.get("arrival_airport").get("id").asText());
                    flightDetails.setArrivalTime(LocalDateTime.parse(firstLeg.get("arrival_airport").get("time").asText(),formatter));
                    flightDetails.setFlightNumber(firstLeg.get("flight_number").asText());
                    flightDetails.setAirline(firstLeg.get("airline").asText());
                    flightDetails.setLogo(flightTemp.get("airline_logo").asText());

                    ans.getFlights().add(flightDetails);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return ans;
    }
}
