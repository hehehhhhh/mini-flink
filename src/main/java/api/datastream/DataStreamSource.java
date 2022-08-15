package api.datastream;

import api.environment.StreamExecutionEnvironment;
import api.operators.StreamSource;
import api.transformations.LegacySourceTransformation;

public class DataStreamSource<T> extends SingleOutputStreamOperator<T> {
    /**
     * 创建Source stream，内部创建LegacySourceTransformation
     */
    public DataStreamSource(StreamExecutionEnvironment environment,
                            StreamSource<T, ?> operator,
                            String sourceName) {
        super(environment,
                new LegacySourceTransformation<>(sourceName, environment.getParallelism(), operator));
    }
}
