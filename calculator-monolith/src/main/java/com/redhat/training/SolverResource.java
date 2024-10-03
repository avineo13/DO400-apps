package com.redhat.training;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.redhat.training.logic.OperationsLogic;
import com.redhat.training.service.SolverService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SolverResource implements SolverService {

    private static final Logger LOG = LoggerFactory.getLogger(OperationsLogic.class);

    @Inject
    private OperationsLogic operationsLogic;

    @Override
    public Float solve(@PathParam("equation") final String equation) {

        try {
            return operationsLogic.solve(equation);
        } catch (Exception e) {
            LOG.debug("Unexepected error occurred: {}", e.getMessage(), e);
        }

        throw new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST).entity("Unable to parse: " + equation).build());
    }

}
