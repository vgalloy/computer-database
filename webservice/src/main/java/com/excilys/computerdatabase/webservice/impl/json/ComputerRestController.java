package com.excilys.computerdatabase.webservice.impl.json;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.ComputerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * @author Vincent Galloy
 *         The Class ComputerRestController.
 */
@RestController
@RequestMapping("rest/json/computer")
public class ComputerRestController implements ComputerResource {
    @Autowired
    private ComputerService computerService;

    @Override
    @GET
    @RequestMapping("getAll")
    public List<Computer> getAll() {
        return computerService.list(new SortCriteria());
    }

    @Override
    @GET
    @RequestMapping("getById/{id}")
    public Computer getById(@PathParam("id") Long id) {
        return computerService.getById(id);
    }

    @Override
    @POST
    @RequestMapping("create")
    public Computer create(Computer t) {
        computerService.create(t);
        return t;
    }

    @Override
    @POST
    @RequestMapping("update")
    public Computer update(Computer t) {
        computerService.update(t);
        return t;
    }

    @Override
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @RequestMapping("delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        computerService.delete(id);
        return Response.ok("ok").build();
    }
}