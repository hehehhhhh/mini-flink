package api.operators;

import api.functions.ReduceFunction;
import runtime.streamrecord.StreamRecord;

public class StreamGroupedReduceOperator<IN>
        extends AbstractUdfStreamOperator<IN, ReduceFunction<IN>>
        implements OneInputStreamOperator<IN, IN> {
    //TODO 实现state后
    private transient IN values;

    public StreamGroupedReduceOperator(ReduceFunction<IN> reduceFunction) {
        super(reduceFunction);
    }

    @Override
    public void open() throws Exception {
        super.open();
        //TODO init value state
    }

    @Override
    public void processElement(StreamRecord<IN> element) throws Exception {
        IN value = element.getValue();
        //TODO from state get
        IN currentValue = values;

        if (currentValue != null) {
            //调用reduc function 计算
            IN reduced = userFunction.reduce(currentValue, value);
            //计算新值更新state
            values = reduced;

            output.collect(element.replace(reduced));
        } else {
            //没有历史值则不执行reduce操作
            values = value;
            output.collect(element.replace(value));
        }
    }
}
