/**
 * 
 */
package com.revolut.transaction.api.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.revolut.transaction.api.dao.DAOFactory;
import com.revolut.transaction.api.exception.TransactionException;
import com.revolut.transaction.api.model.User;

/**
 * @author Elangovan
 *
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    private final DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);

    private static Logger log = Logger.getLogger(UserService.class);

    /**
     * Find by userName
     * 
     * @param userName
     * @return
     * @throws TransactionException
     */
    @GET
    @Path("/{userName}")
    public User getUserByName(@PathParam("userName") String userName) throws TransactionException {
        if (log.isDebugEnabled())
            log.debug("Request Received for get User by Name " + userName);
        final User user = daoFactory.getUserDAO().getUserByName(userName);
        if (user == null) {
            throw new WebApplicationException("User Not Found", Response.Status.NOT_FOUND);
        }
        return user;
    }

    /**
     * Find by all
     * 
     * @param userName
     * @return
     * @throws TransactionException
     */
    @GET
    @Path("/all")
    public List<User> getAllUsers() throws TransactionException {
        return daoFactory.getUserDAO().getAllUsers();
    }

    /**
     * Create User
     * 
     * @param user
     * @return
     * @throws TransactionException
     */
    @POST
    @Path("/create")
    public User createUser(User user) throws TransactionException {
        if (daoFactory.getUserDAO().getUserByName(user.getUserName()) != null) {
            throw new WebApplicationException("User name already exist", Response.Status.BAD_REQUEST);
        }
        final long uId = daoFactory.getUserDAO().insertUser(user);
        return daoFactory.getUserDAO().getUserById(uId);
    }

    /**
     * Find by User Id
     * 
     * @param userId
     * @param user
     * @return
     * @throws TransactionException
     */
    @PUT
    @Path("/{userId}")
    public Response updateUser(@PathParam("userId") long userId, User user) throws TransactionException {
        final int updateCount = daoFactory.getUserDAO().updateUser(userId, user);
        if (updateCount == 1) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Delete by User Id
     * 
     * @param userId
     * @return
     * @throws TransactionException
     */
    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") long userId) throws TransactionException {
        int deleteCount = daoFactory.getUserDAO().deleteUser(userId);
        if (deleteCount == 1) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
