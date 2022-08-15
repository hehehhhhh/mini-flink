package api.operators.factory;

import api.operators.ChainingStrategy;

public abstract class AbstractStreamOperatorFactory<OUT> implements StreamOperatorFactory<OUT> {
    protected ChainingStrategy chainingStrategy = ChainingStrategy.DEFAULT_CHAINING_STRATEGY;

    @Override
    public void setChainingStrategy(ChainingStrategy chainingStrategy) {
        this.chainingStrategy = chainingStrategy;
    }

    @Override
    public ChainingStrategy getChainingStrategy() {
        return chainingStrategy;
    }
}
