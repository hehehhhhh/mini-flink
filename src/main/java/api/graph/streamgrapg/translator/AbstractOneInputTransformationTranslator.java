package api.graph.streamgrapg.translator;

import api.graph.streamgrapg.StreamGraph;
import api.operators.factory.StreamOperatorFactory;
import api.transformations.Transformation;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractOneInputTransformationTranslator<IN, OUT, OP extends Transformation<OUT>>
        extends SimpleTransformationTranslator<OUT, OP> {
    protected Collection<Integer> translateInternal(final Transformation<OUT> transformation,
                                                    final StreamOperatorFactory<OUT> operatorFactory,
                                                    final Context context) {

        StreamGraph streamGraph = context.getStreamGraph();
        int transformationId = transformation.getId();
        Transformation<?> inputTransformation = transformation.getInputs().get(0);

        //创建StreamNode
        streamGraph.addOperator(
                transformationId,
                operatorFactory,
                transformation.getName());

        //将并发度配置到StreamNode中
        streamGraph.setParallelism(transformationId, transformation.getParallelism());
        streamGraph.setMaxParallelism(transformationId, transformation.getMaxParallelism());

        //对上游input和当前transformation建立edge
        for (Integer inputId : context.getStreamNodeIds(inputTransformation)) {
            streamGraph.addEdge(inputId, transformationId);
        }

        return Collections.singleton(transformationId);
    }
}
