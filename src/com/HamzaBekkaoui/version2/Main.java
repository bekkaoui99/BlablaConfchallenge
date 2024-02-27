package com.HamzaBekkaoui.version2;

import com.HamzaBekkaoui.entity.City;
import com.HamzaBekkaoui.entity.Product;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;



public class Main {

    private static final String DATASET_URL = "input.txt";
    private static final String OUTPUT_URL = "output.txt";
    private static final int MAX_PRODUCTS = 5;

    public static void main(String[] args){

        long start = System.currentTimeMillis();
        long end ;
        List<City> cities = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);


        // reading the dataset and collecting information
        try (Stream<String> lines = Files.lines(Paths.get(DATASET_URL))){

            lines.parallel().forEach(line -> {
                counter.incrementAndGet();
                String[] city_product_price = line.split(",");
                String getCity = city_product_price[0];
                String getProduct = city_product_price[1];
                double getPrice = Double.parseDouble(city_product_price[2]);
                collectingData(getCity , getProduct , getPrice , cities);

            });

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }


        // searching for the cheapest city in the country and sort the list of products
        City cheapestCity = cities.stream().min(Comparator.comparing(City::getTotalPrice)).get();
        cheapestCity.getProducts().sort(Comparator.comparing(Product::productPrice).thenComparing(Product::productName));



        // write the result in the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_URL))) {
            writer.write(cheapestCity.getName() + " " + String.format("%.2f" ,cheapestCity.getTotalPrice()) );
            for (int i = 0; i < MAX_PRODUCTS; i++) {
                writer.write("\n" + cheapestCity.getProducts().get(i).productName() + " "
                        + String.format("%.2f" , cheapestCity.getProducts().get(i).productPrice()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) + "ms");
        System.out.println("number of lines the dataset : " + counter.get());

    }

    static synchronized void collectingData(String cityName , String productName , double productPrice , List<City> cities){
        Product product = new Product(productName , productPrice);
        Optional<City> isExist = cities.stream()
                .filter(city -> city.getName().equals(cityName))
                .findFirst();

        if(isExist.isPresent()){
            isExist.get().getProducts().add(product);
            isExist.get().setTotalPrice(productPrice);
        }
        else {
            City city = new City(cityName , new ArrayList<>() , productPrice );
            city.getProducts().add(product);
            cities.add(city);
        }

    }


}
