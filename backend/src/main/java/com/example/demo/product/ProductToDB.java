package com.example.demo.product;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.google.gson.JsonObject;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Made midway through development
 * I realize I need to save the products from FakeStore API into my MongoDB database
 * Why? Because I need to know the IDs of the products the user wants to buy and use it with Stripe to create the checkout (used to display product at checkout and invoice)
 * So get product IDs from database and use it with Stripe to create the checkout
 */
public class ProductToDB {

    public static void main(String[] args){
        try{
            URL url = new URL("https://fakestoreapi.com/products");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = br.readLine()) != null){
                builder.append(line);
            }
            Gson gson = new Gson();

            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("Shopping");
            db.createCollection("Product");
            MongoCollection<Document> collection = db.getCollection("Product");

            JsonArray arr = gson.fromJson(builder.toString(), JsonArray.class);
            for(int i =0;i < arr.size(); i++){
                JsonObject obj = arr.get(i).getAsJsonObject();

                String category = obj.get("category").getAsString().toUpperCase();
                if(category.equals("MEN'S CLOTHING")){
                    category ="MEN";
                }
                else if(category.equals("WOMEN'S CLOTHING")){
                    category = "WOMEN";
                }
                long price =  (long) (obj.get("price").getAsFloat() * 100);

                Product p = new Product(obj.get("title").getAsString(), price, Product.Category.valueOf(category), obj.get("description").getAsString(), obj.get("image").getAsString());

                Document document = Document.parse(gson.toJson(p));
                System.out.println(document.toString());
                collection.insertOne(document);
            }

         }
        catch(MalformedURLException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
