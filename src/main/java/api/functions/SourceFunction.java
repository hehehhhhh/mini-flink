package api.functions;

public interface SourceFunction<T> extends Function {
    /**
     * 运行source，通过SourceContext发送element
     */
    void run(SourceContext<T> ctx);

    /**
     * 停止source，一般isRunning设置false
     */
    void cancel();

    /**
     * SourceFunction用于emit element和watermark
     */
    interface SourceContext<T> {

        /**
         * 从source发送一个element，
         */
        void collect(T element);

        //TODO emit watermark

        /**
         * 关闭上下文
         */
        void close();
    }
}
