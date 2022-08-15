package api.functions.context;

/**
 * RuntimeContext是Function执行的上下文信息，每个Function的并行实例都有一个RuntimeContext。也就是RuntimeContext是Function
 * 级别的，而不是job级别的。RuntimeContext包含了一些静态上下文信息、state等。
 */
public interface RuntimeContext {
    /**
     * 算子任务并行度
     */
    public abstract int getNumberOfParallelSubtasks();

    /**
     * 该DAG job中最大算子的并行度
     */
    public abstract int getMaxNumberOfParallelSubtasks();

    /**
     * 当前子任务所在算子的并发度的索引ID，0~NumberOfParallelSubtasks-1
     */
    public abstract int getIndexOfThisSubtask();

    //----------------accessing state --------------------------//

    // Keyed state

    public abstract <T> ValueState<T> getState(ValueStateDescriptor<T> stateDescriptor);

    public abstract <T> ListState<T> getListState(ListStateDescriptor<T> stateDescriptor);

    public abstract <T>ReducingState<T> getReducingState(ReducingState<T> stateDescriptor);

    public abstract <IN, ACC, OUT> AggregatingState<IN, OUT> getAggregatingState(
            AggregatingStateDescriptor<IN, ACC, OUT> stateDescriptor);

    public abstract <UK, UV> MapState<UK, UV> getMapState(MapStateDescriptor<UK, UV> stateDescriptor);
}
