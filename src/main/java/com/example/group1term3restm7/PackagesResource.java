package com.example.group1term3restm7;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Package;
//import org.json.simple.JSONArray;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Code by Richard Cook
 * Contributor - Dan Palmer found a bug
 * Group 1 April 2022
 * Threaded Proejct
 * HTTP Requests handling for the REST API
 *
 *
 */
@Path("/")
public class PackagesResource {
    //check to  make sure maria db driver is instantiated
    public PackagesResource() {
        try {
            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //get request for all packages
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getpackages")
    public String getPackages() {
        //instantiate entity manager object and assigning it to the persitance class
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        //instantiate entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("select p from Package p");
        List<Package> list = query.getResultList();

        //code to allow the date data to be parseable
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type type = new TypeToken<List<Package>>(){}.getType();
        return gson.toJson(list, type);
    }
    //get request for package specific id
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getpackage/{ packageId }")
    public String getPackage(@PathParam("packageId") int packageId) {
        //instantiate entity manager object and assigning it to the persitance class
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        //instantiate entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Package pkg = entityManager.find(Package.class, packageId);

        //code to allow the date data to be parseable
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(pkg);
    }
    //editing a new pacakge
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("postpackage")
    public String postPackage(String jsonString) {
        //instantiate entity manager object and assigning it to the persitance class
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        //instantiate entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //code to allow the date data to be parseable

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        Package packageObject = gson.fromJson(jsonString, Package.class);
        entityManager.getTransaction().begin();
        Package mergedObject = entityManager.merge(packageObject);

        entityManager.getTransaction().commit();
        entityManager.close();
        return "{ 'message': 'Package was updated' }";
    }
    //adding a new package
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/putpackage")
    public String putPackage(String jsonString) {
        //instantiate entity manager object and assigning it to the persitance class
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        //instantiate entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //code to allow the date data to be parseable

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        Package packageObject = gson.fromJson(jsonString, Package.class);
        entityManager.getTransaction().begin();
//        Package mergedObject = entityManager.merge(packageObject);
        entityManager.persist(packageObject);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "{ 'message': 'Package was inserted' }";
    }


    //deleting packages
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletepackage/{ packageId }")
    public String deletePackage(@PathParam("packageId") int packageId) {
        //instantiate entity manager object and assigning it to the persitance class
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        //instantiate entity manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Package pkg = entityManager.find(Package.class, packageId);
        String message = "";
        if (pkg == null)
        {
            message = "{ 'message': 'Package not found' }";
        }
        else
        {
            entityManager.getTransaction().begin();
            entityManager.remove(pkg);
            entityManager.getTransaction().commit();
            message = "{ 'message': 'Package was deleted' }";
        }
        entityManager.close();
        return message;
    }
}