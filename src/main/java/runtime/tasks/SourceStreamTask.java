package runtime.tasks;

import api.functions.SourceFunction;
import api.operators.StreamSource;

public class SourceStreamTask<OUT, SRC extends SourceFunction<OUT>, OP extends StreamSource<OUT, SRC>>
        extends StreamTask<OUT, OP> {
}
