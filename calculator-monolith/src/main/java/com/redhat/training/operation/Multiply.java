package com.redhat.training.operation;

import java.util.function.BinaryOperator;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class Multiply extends BinaryOperation {

    private static final String REGEX = "(.*)\\*(.*)";
    private static final BinaryOperator<Float> OPERATION = (lhs, rhs) -> lhs * rhs;

    public Multiply() {
        super(REGEX, OPERATION);
    }

}
