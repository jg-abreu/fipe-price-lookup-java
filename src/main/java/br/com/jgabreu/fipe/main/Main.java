package br.com.jgabreu.fipe.main;

import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar: 
                """;

        System.out.println(menu);
        var option = sc.nextLine();
        String address;


    }

}
