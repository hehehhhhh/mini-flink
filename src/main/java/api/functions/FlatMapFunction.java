package api.functions;

import api.operators.output.Collector;

public interface FlatMapFunction<IN, OUT> extends Function  {
    /**
     * 接收一条元素，执行transform逻辑，然后将结果(0个、1个或多个)放到结果收集器中
     */
    void flatMap(IN element, Collector<OUT> out) throws Exception;
}
