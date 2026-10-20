# FIPE Price Lookup

![Java 17](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot 4](https://img.shields.io/badge/Spring_Boot-4-6DB33F?logo=springboot&logoColor=white)
![Jackson](https://img.shields.io/badge/Jackson-JSON-2C2D72)

A command-line app that looks up the average market price of cars, motorcycles and trucks in Brazil's [FIPE table](https://veiculos.fipe.org.br/), showing every model year at once and saving each search to a JSON file.

## Features

- **Pick a vehicle type** — car, motorcycle or truck — and list all its brands
- **Filter models by name** — type part of the name (e.g. `palio`) instead of scrolling through hundreds of models
- **All years in one search** — unlike the FIPE website, which shows one year at a time, the app fetches the price for every available model year
- **JSON export** — each search is saved to `resultados/resultado_yyyy-MM-dd_HHmmss.json`, keeping a history of previous lookups (the files are git-ignored)

## How it works

The app consumes the public [FIPE API](https://deividfortuna.github.io/fipe/) with Java's `HttpClient`, chaining one request per step: brands → models → available years → price for each year. Responses are deserialized with Jackson into records (`Data`, `Models`, `Vehicle`), and the model filter and sorting use the Streams API.

## Tech stack

Java 17 · Spring Boot 4 (command-line runner) · Jackson · Maven

## Running

Requirements: Java 17+ and an internet connection (for the FIPE API).

```bash
git clone https://github.com/jg-abreu/fipe-price-lookup-java.git
cd fipe-price-lookup-java
./mvnw spring-boot:run
```

Then follow the prompts: choose `Car`, `Motorcycle` or `Truck`, enter a brand code, part of the model name and the model code. The prices for every year are printed and saved under `resultados/`.

## Project structure

```
src/main/java/br/com/jgabreu/fipe
├── main/       interactive menu and the lookup flow
├── model/      records mapped from the API responses
└── service/    HTTP client, JSON conversion and the JSON exporter
```

## Credits

Built as the solution to a challenge from [Alura](https://www.alura.com.br)'s course on lambdas, streams and the Spring Framework. The implementation, including the JSON export, is my own.

## Author

**João Abreu** — back-end developer and Computer Science student in Rio de Janeiro, Brazil.

[GitHub](https://github.com/jg-abreu) · [LinkedIn](https://www.linkedin.com/in/jo%C3%A3o-abreuuu) · jgabreudev@gmail.com
