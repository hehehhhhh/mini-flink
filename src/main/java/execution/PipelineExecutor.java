package execution;

import api.graph.streamgrapg.Pipeline;
import configuration.Configuration;

import java.util.concurrent.CompletableFuture;

/**
 * 用于执行pipeline的实体，可能是本地，也可以是远端集群
 */
public interface PipelineExecutor {
    CompletableFuture<JobClient> execute(final Pipeline pipeline,
                                         final Configuration configuration) throws Exception;
}
