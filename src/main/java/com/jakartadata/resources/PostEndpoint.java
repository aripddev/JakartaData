package com.jakartadata.resources;

import com.jakartadata.model.Post;
import com.jakartadata.service.PostService;
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
import java.util.UUID;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PostEndpoint {

  @Inject
  PostService service;

  @POST
  public Response create(Post customer) {
    service.createPost(customer);
    return Response.status(201).build();
  }

  @GET
  public List<Post> getAll() {
    return service.findAllPosts();
  }

  @GET
  @Path("{id}")
  public Response getCustomerById(@PathParam("id") UUID id) {
    return service.findPostById(id);
  }

  @GET
  @Path("/title/{title}")
  public List<Post> getByTitle(@PathParam("title") String title) {
    return service.findAllPostsByTitle(title);

  }

  @PUT
  @Path("{id}")
  public Response updateCustomerById(@PathParam("id") UUID id, Post updatedCustomer) {
    return service.updatePost(id, updatedCustomer);
  }

  @DELETE
  @Path("{id}")
  public Response deleteCustomerById(@PathParam("id") UUID id) {
    return service.deletePostById(id);
  }
}
