package api.transformations;

import api.operators.ChainingStrategy;
import api.operators.StreamSink;
import api.operators.factory.SimpleOperatorFactory;
import api.operators.factory.StreamOperatorFactory;
import api.transformations.PhysicalTransformation;
import api.transformations.Transformation;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class LegacySinkTransformation<IN> extends PhysicalTransformation<Object> {
    private final Transformation<IN> input;

    private final StreamOperatorFactory<Object> operatorFactory;

    public LegacySinkTransformation(Transformation<IN> input,
                                    String name,
                                    StreamSink<IN> sinkOperator,
                                    int parallelism) {
        //sink无输出，所以输出类型为null
        super(name, parallelism);
        this.input = input;
        this.operatorFactory = SimpleOperatorFactory.of(sinkOperator);
    }

    @Override
    public List<Transformation<?>> getTransitivePredecessors() {
        final List<Transformation<?>> result = Lists.newArrayList();
        result.add(this);
        result.addAll(input.getTransitivePredecessors());
        return result;
    }

    @Override
    public List<Transformation<?>> getInputs() {
        return Collections.singletonList(input);
    }

    @Override
    public void setChainingStrategy(ChainingStrategy strategy) {
        operatorFactory.setChainingStrategy(strategy);
    }

    public StreamOperatorFactory<Object> getOperatorFactory() {
        return operatorFactory;
    }
}
