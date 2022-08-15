package execution;

import configuration.Configuration;

public interface PipelineExecutorFactory {
    PipelineExecutor getExecutor(final Configuration configuration);
}
