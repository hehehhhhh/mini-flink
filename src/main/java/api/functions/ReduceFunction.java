package api.functions;

public abstract class ReduceFunction<T> implements Function {
    public abstract T reduce(T value1, T value2) throws Exception;
}
