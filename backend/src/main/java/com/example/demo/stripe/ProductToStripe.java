package com.example.demo.stripe;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.param.ProductCreateParams;
import org.bson.Document;


/**
 * use stripe API to save products in MongoDB into Stripe
 */
public class ProductToStripe {

    //Find some way to use WebHooks to have Database & Stripe be in sync
    // Update items with category later
    public static void main(String[] args) throws StripeException {
        Stripe.apiKey = "API KEY";
        MongoClient client = MongoClients.create();
        MongoDatabase db = client.getDatabase("Shopping");
        MongoCollection<Document> collection = db.getCollection("Product");
        for(Document doc : collection.find()){

            String id = doc.getObjectId("_id").toString();
            String description = doc.get("description").toString();
            String imgURL = doc.get("image").toString();
            String name = doc.get("title").toString();
            String category = doc.get("category").toString();
            float price = Float.parseFloat( doc.get("price").toString());

            ProductCreateParams.DefaultPriceData unitAmt = ProductCreateParams.DefaultPriceData.builder().setCurrency("usd").setUnitAmount(toPennies(price)).build();

            ProductCreateParams params = ProductCreateParams.builder().setName(name)
                    .setId(id)
                    .setDescription(description)
                    .addImage(imgURL).setDefaultPriceData(unitAmt)
                    .putMetadata("category", category)
                    .build();
            Product product = Product.create(params);
        }

    }

    private static long toPennies(float price){
        return (long)(price *100);
    }
}
