package com.excilys.computerdatabase.webservice.impl.xml;

import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.ComputerResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class ComputerResourceImpl.
 */
@Path("/computer")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class ComputerResourceImpl implements ComputerResource {
    @Autowired
    private ComputerService computerService;
    @Autowired
    private ComputerDtoMapper computerDtoMapper;

    @Override
    @GET
    @Path("getAll")
    public List<ComputerDto> getAll() {
        return computerDtoMapper.mapListFromModel(computerService.list(new SortCriteria()));
    }

    @Override
    @GET
    @Path("getById/{id}")
    public ComputerDto getById(@PathParam("id") Long id) {
        return computerDtoMapper.mapFromModel(computerService.getById(id));
    }

    @Override
    @POST
    @Path("create")
    public ComputerDto create(ComputerDto t) {
        Computer computer = computerDtoMapper.mapToModel(t);
        computerService.create(computer);
        return computerDtoMapper.mapFromModel(computer);
    }

    @Override
    @POST
    @Path("update")
    public ComputerDto update(ComputerDto t) {
        Computer computer = computerDtoMapper.mapToModel(t);
        computerService.update(computer);
        return computerDtoMapper.mapFromModel(computer);
    }

    @Override
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Path("delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        computerService.delete(id);
        return Response.ok("ok").build();
    }
}