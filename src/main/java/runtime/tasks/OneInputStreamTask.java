package runtime.tasks;

import api.operators.OneInputStreamOperator;

public class OneInputStreamTask<IN, OUT> extends StreamTask<OUT, OneInputStreamOperator<IN, OUT>> {
}
