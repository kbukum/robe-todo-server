package io.robe.sample.todo.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.robe.common.service.RobeService;
import io.robe.common.service.search.SearchParam;
import io.robe.common.service.search.model.SearchModel;
import io.robe.common.utils.reflection.Fields;
import io.robe.sample.todo.dao.TodoItemDao;
import io.robe.sample.todo.entity.TodoItem;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by kamilbukum on 06/02/2017.
 */
@Path("todoItems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoItemResource {

    @Inject
    TodoItemDao todoItemDao;

    /**
     * Return all TodoItem as a collection
     * @return all {@link TodoItem} as a collection
     */
    @RobeService(group = "TodoItem", description = "Returns all TodoItem as a collection.")
    @GET
    @UnitOfWork(readOnly = true, cacheMode = CacheMode.GET, flushMode = FlushMode.MANUAL)
    public List<TodoItem> getAll(@SearchParam SearchModel search) {
        return todoItemDao.findAllStrict(search);
    }

    /**
     * Return a TodoItem resource  with the matches given id.
     * <p>
     * Status Code:
     * Not Found  404
     * @param id          This is  the oid of {@link TodoItem}
     * @return a  {@link TodoItem} resource with the matches given id.
     */
    @RobeService(group = "TodoItem", description = "Returns a TodoItem resource with the matches given id.")
    @Path("{id}")
    @GET
    @UnitOfWork(readOnly = true, cacheMode = CacheMode.GET, flushMode = FlushMode.MANUAL)
    public TodoItem get(@PathParam("id") String id) {
        TodoItem entity = todoItemDao.findById(id);
        if (entity == null) {
            throw new WebApplicationException(Response.status(404).build());
        }
        return entity;
    }

    /**
     * Update a TodoItem resource  with the matches given id.
     * <p>
     * Status Code:
     * Not Found  404
     * Not Matches 412
     * @param id          This is  the oid of {@link TodoItem}
     * @param model       This is the one model of {@link TodoItem}
     * @return Update a  {@link TodoItem} resource with the matches given id.
     */
    @RobeService(group = "TodoItem", description = "Update a TodoItem resource with the matches given id.")
    @Path("{id}")
    @PUT
    @UnitOfWork
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public TodoItem update(@PathParam("id") String id, @Valid TodoItem model) {
        if (!id.equals(model.getOid())) {
            throw new WebApplicationException(Response.status(412).build());
        }
        TodoItem current = todoItemDao.findById(id);
        if (current == null) {
            throw new WebApplicationException(Response.status(404).build());
        }
        return todoItemDao.merge(model);
    }

    /**
     * Update a TodoItem resource with the matches given id.
     * <p>
     * Status Code:
     * Not Found  404
     * Not Matches 412
     *
     * @param id          This is  the oid of {@link TodoItem}
     * @param model       This is the one model of {@link TodoItem}
     * @return Updates a  {@link TodoItem} resource with the matches given id.
     */
    @RobeService(group = "TodoItem", description = "Update a TodoItem resource with the matches given id. PATCH")
    @Path("{id}")
    @PATCH
    @UnitOfWork
    public TodoItem merge(@PathParam("id") String id, TodoItem model) {

        if (!id.equals(model.getOid()))
            throw new WebApplicationException(Response.status(412).build());
        TodoItem dest = todoItemDao.findById(id);
        todoItemDao.detach(dest);
        if (dest == null) {
            throw new WebApplicationException(Response.status(404).build());
        }
        Fields.mergeRight(model, dest);
        return todoItemDao.update(model);
    }

    /**
     * Create a TodoItem resource.
     * @param model       This is the one model of {@link TodoItem}
     * @return create a {@link TodoItem} resource.
     */
    @RobeService(group = "TodoItem", description = "Create a TodoItem resource.")
    @POST
    @UnitOfWork
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public TodoItem create(@Valid TodoItem model) {
        model = todoItemDao.create(model);
        return model;
    }

    /**
     * Delete a TodoItem resource  with the matches given id.
     * <p>
     * Status Code:
     * Not Found  404
     * Not Matches 412
     * @param id          This is  the oid of {@link TodoItem#oid}
     * @param model       This is the one model of {@link TodoItem}
     * @return Delete a  {@link TodoItem} resource  with the matches given id.
     */
    @RobeService(group = "TodoItem", description = "Delete a TodoItem resource with the matches given id.")
    @Path("{id}")
    @DELETE
    @UnitOfWork
    public TodoItem delete(@PathParam("id") String id, @Valid TodoItem model) {
        TodoItem entity = todoItemDao.findById(id);
        if (entity == null) {
            throw new WebApplicationException(Response.status(404).build());
        }
        todoItemDao.delete(entity);
        return entity;
    }

}
