package br.com.jgabreu.fipe.main;

import br.com.jgabreu.fipe.service.ApiConsumption;

import java.net.URL;
import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ApiConsumption consumption = new ApiConsumption();

    public void showMenu() {
        var menu = """
                *** OPÇÕES ***
                Car
                Motorcycle
                Truck
                
                Enter one of the options to search: 
                """;

        System.out.println(menu);
        var option = sc.nextLine();
        String address;

        if(option.toLowerCase().contains("car")) {
            address = URL_BASE + "carros/marcas";
        } else if (option.toLowerCase().contains("motorcycle")) {
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "camonhoes/marcas";
        }

        var json = consumption.getData(address);
        System.out.println(json);

    }

}
