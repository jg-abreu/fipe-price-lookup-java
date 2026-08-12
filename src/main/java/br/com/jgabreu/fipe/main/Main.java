package br.com.jgabreu.fipe.main;

import br.com.jgabreu.fipe.model.Data;
import br.com.jgabreu.fipe.model.Models;
import br.com.jgabreu.fipe.service.ApiConsumption;
import br.com.jgabreu.fipe.service.ConvertData;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ApiConsumption consumption = new ApiConsumption();
    private ConvertData convertData = new ConvertData();

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

        if (option.toLowerCase().contains("car")) {
            address = URL_BASE + "carros/marcas";
        } else if (option.toLowerCase().contains("motorcycle")) {
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "camonhoes/marcas";
        }

        var json = consumption.getData(address);
        System.out.println(json);
        var brands = convertData.getList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codeBrand = sc.nextLine();

        address = address + "/" + codeBrand + "/modelos";
        json = consumption.getData(address);
        var modelList = convertData.getData(json, Models.class);

        System.out.println("\nModelos dessa marca: ");
        modelList.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);
    }

}
