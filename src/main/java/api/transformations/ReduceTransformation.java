package api.transformations;

import api.functions.KeySelector;
import api.functions.ReduceFunction;
import api.operators.ChainingStrategy;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class ReduceTransformation<IN, K> extends PhysicalTransformation<IN> {
    private final Transformation<IN> input;

    private final ReduceFunction<IN> reducer;

    private final KeySelector<IN, K> keySelector;

    private ChainingStrategy chainingStrategy = ChainingStrategy.DEFAULT_CHAINING_STRATEGY;

    public ReduceTransformation(String name,
                                int parallelism,
                                Transformation<IN> input,
                                ReduceFunction<IN> reducer,
                                KeySelector<IN, K> keySelector) {

        super(name, parallelism);

        this.input = input;
        this.reducer = reducer;
        this.keySelector = keySelector;
    }

    @Override
    public void setChainingStrategy(ChainingStrategy strategy) {
        this.chainingStrategy = strategy;
    }

    public ChainingStrategy getChainingStrategy() {
        return chainingStrategy;
    }

    @Override
    public List<Transformation<?>> getTransitivePredecessors() {
        List<Transformation<?>> result = Lists.newArrayList();
        result.add(this);
        result.addAll(input.getTransitivePredecessors());
        return result;
    }

    @Override
    public List<Transformation<?>> getInputs() {
        return Collections.singletonList(input);
    }

    public ReduceFunction<IN> getReducer() {
        return reducer;
    }
}
