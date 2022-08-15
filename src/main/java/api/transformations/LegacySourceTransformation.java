package api.transformations;

import api.operators.ChainingStrategy;
import api.operators.StreamSource;
import api.operators.factory.SimpleOperatorFactory;
import api.operators.factory.StreamOperatorFactory;

import java.util.Collections;
import java.util.List;

/**
 * source connector的Transformation
 */
public class LegacySourceTransformation<OUT> extends PhysicalTransformation<OUT> {
    private final StreamOperatorFactory<OUT> operatorFactory;

    public LegacySourceTransformation(String name,
                                      int parallelism,
                                      StreamSource<OUT, ?> sourceOperator) {
        super(name, parallelism);
        this.operatorFactory = SimpleOperatorFactory.of(sourceOperator);
    }

    @Override
    public List<Transformation<?>> getTransitivePredecessors() {
        return Collections.singletonList(this);
    }

    @Override
    public List<Transformation<?>> getInputs() {
        return Collections.emptyList();
    }

    @Override
    public void setChainingStrategy(ChainingStrategy chainingStrategy) {
        this.operatorFactory.setChainingStrategy(chainingStrategy);
    }

    public StreamOperatorFactory<OUT> getOperatorFactory() {
        return operatorFactory;
    }
}
