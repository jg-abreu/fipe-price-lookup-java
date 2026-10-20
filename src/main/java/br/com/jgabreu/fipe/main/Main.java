package br.com.jgabreu.fipe.main;

import br.com.jgabreu.fipe.model.Data;
import br.com.jgabreu.fipe.model.Models;
import br.com.jgabreu.fipe.model.Vehicle;
import br.com.jgabreu.fipe.service.ApiConsumption;
import br.com.jgabreu.fipe.service.ConvertData;
import br.com.jgabreu.fipe.service.JsonResultExporter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ApiConsumption consumption = new ApiConsumption();
    private ConvertData convertData = new ConvertData();
    private JsonResultExporter jsonResultExporter = new JsonResultExporter();

    public void showMenu() {
        var menu = """
                *** OPTIONS ***
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
            address = URL_BASE + "caminhoes/marcas";
        }

        var json = consumption.getData(address);
        var brands = convertData.getList(json, Data.class);
        brands.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Enter the brand code for search: ");
        var codeBrand = sc.nextLine();

        address = address + "/" + codeBrand + "/modelos";
        json = consumption.getData(address);
        var modelList = convertData.getData(json, Models.class);

        System.out.println("\nModels from this brand: ");
        modelList.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.println("Enter part of the car name to search for: ");
        var nameVehicle = sc.nextLine();
        List<Data> filteredModels = modelList.models().stream()
                .filter(m -> m.name().toLowerCase().contains(nameVehicle.toLowerCase()))
                        .collect(Collectors.toList());

        System.out.println("\nFiltered Models");
        filteredModels.forEach(System.out::println);

        System.out.println("Please enter the model code to search for valuation prices: ");
        var modelCode = sc.nextLine();

        address = address + "/" + modelCode + "/anos";
        json = consumption.getData(address);
        List<Data> years = convertData.getList(json, Data.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var yearAddress = address + "/" + years.get(i).code();
            json = consumption.getData(yearAddress);
            Vehicle vehicle = convertData.getData(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("All filtered vehicles with valuations by year: ");
        vehicles.forEach(System.out::println);
        jsonResultExporter.export(vehicles);

    }

}
