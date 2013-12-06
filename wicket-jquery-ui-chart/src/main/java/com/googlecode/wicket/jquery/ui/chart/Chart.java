/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.jquery.ui.chart;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;

/**
 * TODO: javadoc
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public class Chart extends AbstractChart
{
	private static final long serialVersionUID = 1L;

	private final ChartDataProvider provider;

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 */
	public Chart(String id, ChartDataProvider provider)
	{
		this(id, provider, Gallery.None, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 * @param gallery the {@link Gallery}
	 */
	public Chart(String id, ChartDataProvider provider, Gallery gallery)
	{
		this(id, provider, gallery, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 * @param options {@link Options}
	 */
	public Chart(String id, ChartDataProvider provider, Options options)
	{
		this(id, provider, Gallery.None, options);
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link ChartModel}
	 * @param gallery the {@link Gallery}
	 * @param options {@link Options}
	 */
	public Chart(String id, ChartDataProvider provider, Gallery gallery, Options options)
	{
		super(id, gallery, options);

		this.provider = provider;
	}

	// Properties //

	// Events //
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
	}

	@Override
	public void onConfigure(JQueryBehavior behavior)
	{
		super.onConfigure(behavior);

		// dataSource
		behavior.setOption("dataValues", ChartModel.toJson(provider.));
		behavior.setOption("series", Series.toJson(this.provider.getSeries())); // should be set *after* dataValues

		// behavior.setOption("data", String.format("{ series: %s }", this.series.size()));
		// chart1.getDataSourceSettings().reloadData();
	}

	// Methods //
}
