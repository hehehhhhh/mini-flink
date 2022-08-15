package api.transformations;

import api.operators.ChainingStrategy;
import api.operators.OneInputStreamOperator;
import api.operators.factory.SimpleOperatorFactory;
import api.operators.factory.StreamOperatorFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * OneInputTransformation对应OneInputStreamOperator
 */
public class OneInputTransformation<IN, OUT> extends PhysicalTransformation<OUT> {
    private final Transformation<IN> input;

    private final StreamOperatorFactory<OUT> operatorFactory;

    public OneInputTransformation(String name,
                                  int parallelism,
                                  OneInputStreamOperator<IN, OUT> oneInputStreamOperator,
                                  Transformation<IN> input) {

        super(name, parallelism);
        this.input = input;
        this.operatorFactory = SimpleOperatorFactory.of(oneInputStreamOperator);
    }

    @Override
    public void setChainingStrategy(ChainingStrategy strategy) {
        operatorFactory.setChainingStrategy(strategy);
    }

    @Override
    public List<Transformation<?>> getTransitivePredecessors() {
        List<Transformation<?>> result = new ArrayList<>();
        result.add(this);
        result.addAll(input.getTransitivePredecessors());
        return result;
    }

    @Override
    public List<Transformation<?>> getInputs() {
        return Collections.singletonList(input);
    }

    public StreamOperatorFactory<OUT> getOperatorFactory() {
        return operatorFactory;
    }
}