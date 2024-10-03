package com.redhat.training.logic;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;

import com.redhat.training.operation.Operation;
import com.redhat.training.operation.Add;
import com.redhat.training.operation.Identity;
import com.redhat.training.operation.Multiply;
import com.redhat.training.operation.Substract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public final class OperationsLogic {

    private static final Logger LOG = LoggerFactory.getLogger(OperationsLogic.class);

    @Inject
    Add add;

    @Inject
    Substract substract;

    @Inject
    Multiply multiply;

    @Inject
    Identity identity;

    private List<Operation> operationsList;

    public OperationsLogic() {
        super();
    }

    @PostConstruct
    protected void buildOperationList() {
        operationsList = List.of(substract, add, multiply, identity);
    }

    public Float solve(final String equation) {

        LOG.info("Solving '{}'", equation);
        for (Operation operation : operationsList) {
            Float result = operation.apply(equation);
            if (result != null) {
                LOG.info("Solved '{} = {}'", equation, result);
                return result;
            }
        }

        LOG.info("Failed to solve '{}'", equation);

        return null;
    }

}
