package com.excilys.computerdatabase.webservice.impl.xml;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.CompanyResource;
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
 *         The Class CompanyResourceImpl.
 */
@Path("/company")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CompanyResourceImpl implements CompanyResource {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    @Override
    @GET
    @Path("getAll")
    public List<CompanyDto> getAll() {
        return companyDtoMapper.mapListFromModel(companyService.list(new SortCriteria()));
    }

    @Override
    @GET
    @Path("getById/{id}")
    public CompanyDto getById(@PathParam("id") Long id) {
        return companyDtoMapper.mapFromModel(companyService.getById(id));
    }

    @Override
    @POST
    @Path("create")
    public CompanyDto create(CompanyDto t) {
        Company company = companyDtoMapper.mapToModel(t);
        companyService.create(company);
        return companyDtoMapper.mapFromModel(company);
    }

    @Override
    @POST
    @Path("update")
    public CompanyDto update(CompanyDto t) {
        Company company = companyDtoMapper.mapToModel(t);
        companyService.update(company);
        return companyDtoMapper.mapFromModel(company);
    }

    @Override
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    @Path("delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        companyService.delete(id);
        return Response.ok("ok").build();
    }
}