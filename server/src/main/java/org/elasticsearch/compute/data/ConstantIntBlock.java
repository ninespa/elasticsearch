/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.compute.data;

/**
 * Block implementation that stores a constant integer value.
 */
public class ConstantIntBlock extends Block {

    private final int value;

    public ConstantIntBlock(int value, int positionCount) {
        super(positionCount);
        this.value = value;
    }

    @Override
    public int getInt(int position) {
        assert assertPosition(position);
        return value;
    }

    @Override
    public long getLong(int position) {
        return getInt(position);  // Widening primitive conversions, no loss of precision
    }

    @Override
    public double getDouble(int position) {
        return getInt(position);  // Widening primitive conversions, no loss of precision
    }

    @Override
    public Object getObject(int position) {
        return getInt(position);
    }

    @Override
    public Block getRow(int position) {
        Block curr = this;
        return new ConstantIntBlock(value, 1) {
            @Override
            public int getInt(int ignored) {
                return curr.getInt(position);
            }

            @Override
            public long getLong(int ignored) {
                return curr.getLong(position);
            }

            @Override
            public double getDouble(int ignored) {
                return curr.getDouble(position);
            }

            @Override
            public Object getObject(int ignored) {
                return curr.getObject(position);
            }

            @Override
            public String toString() {
                return "only-position " + position + ": " + curr;
            }
        };
    }

    @Override
    public String toString() {
        return "ConstantIntBlock{positions=" + getPositionCount() + ", value=" + value + '}';
    }
}
