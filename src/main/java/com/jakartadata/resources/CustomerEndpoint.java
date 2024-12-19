package com.jakartadata.resources;

import com.jakartadata.model.Customer;
import com.jakartadata.service.CustomerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

  @Inject
  CustomerService service;

  @POST
  public Response create(Customer customer) {
    service.createCustomer(customer);
    return Response.status(201).build();
  }

  @GET
  public List<Customer> getAll() {
    return service.findAllCustomers();
  }

  @GET
  @Path("{id}")
  public Response getCustomerById(@PathParam("id") Long id) {
    return service.findCustomerById(id);
  }

  @GET
  @Path("/name/{name}")
  public List<Customer> getByName(@PathParam("name") String name) {
    return service.findAllCustomersByFirstName(name);

  }

  @PUT
  @Path("{id}")
  public Response updateCustomerById(@PathParam("id") Long id, Customer updatedCustomer) {
    return service.updateCustomer(id, updatedCustomer);
  }

  @DELETE
  @Path("{id}")
  public Response deleteCustomerById(@PathParam("id") Long id) {
    return service.deleteCustomerById(id);
  }
}
