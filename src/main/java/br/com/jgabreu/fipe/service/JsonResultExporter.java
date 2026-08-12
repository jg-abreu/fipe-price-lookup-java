package br.com.jgabreu.fipe.service;

import br.com.jgabreu.fipe.model.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonResultExporter {

    private static final String OUTPUT_DIR = "resultados";
    private final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public void export(List<Vehicle> vehicles) {
        try {
            File dir = new File(OUTPUT_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss"));
            File file = new File(dir, "resultado_" + timestamp + ".json");

            mapper.writeValue(file, vehicles);
            System.out.println("\nResult saved to: " + file.getPath());
        } catch (IOException e) {
            System.out.println("Failed to save result to JSON: " + e.getMessage());
        }
    }
}