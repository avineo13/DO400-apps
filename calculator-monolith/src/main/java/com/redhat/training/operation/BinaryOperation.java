package com.redhat.training.operation;

import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

import javax.inject.Inject;

import com.redhat.training.logic.OperationsLogic;

import java.util.regex.Matcher;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public abstract class BinaryOperation implements Operation {

    @Inject
    protected OperationsLogic operationsLogic;

    private String regex;

    private BinaryOperator<Float> operation;

    protected BinaryOperation(final String regexArg, final BinaryOperator<Float> operationArg) {

        regex = regexArg;
        operation = operationArg;
    }

    @Override
    public Float apply(final String equation) {
        return solveGroups(equation).stream().reduce(operation).orElse(null);
    }

    private Pattern buildPattern() {
        return Pattern.compile(regex);
    }

    private List<Float> solveGroups(final String equation) {
        Matcher matcher = buildPattern().matcher(equation);
        if (matcher.matches()) {
            List<Float> result = new ArrayList<>(matcher.groupCount());
            for (int groupNum = 1; groupNum <= matcher.groupCount(); groupNum++) {
                result.add(operationsLogic.solve(matcher.group(groupNum)));
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

}
