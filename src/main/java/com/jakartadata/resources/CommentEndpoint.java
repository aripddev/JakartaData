package com.jakartadata.resources;

import com.jakartadata.model.Comment;
import com.jakartadata.service.CommentService;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("comments")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CommentEndpoint {

  @Inject
  CommentService service;

  @POST
  public Response create(Comment comment) {
    service.createComment(comment);
    return Response.status(201).build();
  }

  @GET
  public List<Comment> getAll() {
    return service.findAllComments();
  }

  @GET
  @Path("{id}")
  public Response getCommentById(@PathParam("id") UUID id) {
    return service.findCommentById(id);
  }

  @GET
  @Path("/createdAt/{createdAt}")
  public List<Comment> getByCreatedAt(@PathParam("createdAt") LocalDateTime createdAt) {
    return service.findAllCommentsByCreatedAt(createdAt);

  }

  @PUT
  @Path("{id}")
  public Response updateCommentById(@PathParam("id") UUID id, Comment updatedComment) {
    return service.updateComment(id, updatedComment);
  }

  @DELETE
  @Path("{id}")
  public Response deleteCommentById(@PathParam("id") UUID id) {
    return service.deleteCommentById(id);
  }
}
