/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package streaming.api.datastream;

import annotation.Public;
import annotation.PublicEvolving;
import api.dag.Transformation;
import streaming.api.environment.StreamExecutionEnvironment;
import streaming.api.transformations.PhysicalTransformation;

import static util.Preconditions.checkArgument;

/**
 * {@code SingleOutputStreamOperator} represents a user defined transformation
 * applied on a {@link DataStream} with one predefined output type.
 *
 * @param <T> The type of the elements in this stream.
 */
@Public
public class SingleOutputStreamOperator<T> extends DataStream<T> {

	/** Indicate this is a non-parallel operator and cannot set a non-1 degree of parallelism. **/
	protected boolean nonParallel = false;

	protected SingleOutputStreamOperator(StreamExecutionEnvironment environment, Transformation<T> transformation) {
		super(environment, transformation);
	}

	/**
	 * Gets the name of the current data stream. This name is
	 * used by the visualization and logging during runtime.
	 *
	 * @return Name of the stream.
	 */
	public String getName() {
		return transformation.getName();
	}

	/**
	 * Sets the name of the current data stream. This name is
	 * used by the visualization and logging during runtime.
	 *
	 * @return The named operator.
	 */
	public SingleOutputStreamOperator<T> name(String name){
		transformation.setName(name);
		return this;
	}

	/**
	 * Sets the parallelism for this operator.
	 *
	 * @param parallelism
	 *            The parallelism for this operator.
	 * @return The operator with set parallelism.
	 */
	public SingleOutputStreamOperator<T> setParallelism(int parallelism) {
		transformation.setParallelism(parallelism);

		return this;
	}
}
