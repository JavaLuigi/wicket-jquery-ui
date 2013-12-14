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
package com.googlecode.wicket.jquery.ui.form.datepicker;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.model.IModel;

import com.googlecode.wicket.jquery.core.IJQueryWidget;
import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.core.ajax.JQueryAjaxPostBehavior;

/**
 * Provides a jQuery date-picker based on a {@link DateTextField}
 *
 * @author Sebastien Briquet - sebfz1
 */
//XXX: report break: input type=date (instead of type=text)
public class DatePicker extends DateTextField implements IJQueryWidget, IDatePickerListener
{
	private static final long serialVersionUID = 1L;

	protected Options options;

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 */
	public DatePicker(String id)
	{
		this(id, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param options {@link Options}
	 */
	public DatePicker(String id, Options options)
	{
		super(id);

		this.options = options;
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param pattern a <code>SimpleDateFormat</code> pattern
	 * @param options {@link Options}
	 */
	public DatePicker(String id, String pattern, Options options)
	{
		super(id, pattern);

		this.options = options;
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link IModel}
	 */
	public DatePicker(String id, IModel<Date> model)
	{
		this(id, model, new Options());
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link IModel}
	 * @param options {@link Options}
	 */
	public DatePicker(String id, IModel<Date> model, Options options)
	{
		super(id, model);

		this.options = options;
	}

	/**
	 * Constructor
	 *
	 * @param id the markup id
	 * @param model the {@link IModel}
	 * @param pattern a <code>SimpleDateFormat</code> pattern
	 * @param options {@link Options}
	 */
	public DatePicker(String id, IModel<Date> model, String pattern, Options options)
	{
		super(id, model, pattern);

		this.options = options;
	}

	// IDatePickerListener //
	@Override
	public boolean isOnSelectEventEnabled()
	{
		return false;
	}

	// Events //
	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		this.add(JQueryWidget.newWidgetBehavior(this)); // cannot be in ctor as the markupId may be set manually afterward
	}

	@Override
	public void onConfigure(JQueryBehavior behavior)
	{
		// noop
	}

	@Override
	public void onBeforeRender(JQueryBehavior behavior)
	{
		// noop
	}

	@Override
	public void onSelect(AjaxRequestTarget target, String date)
	{
		// noop
	}

	// IJQueryWidget //
	@Override
	public JQueryBehavior newWidgetBehavior(String selector)
	{
		return new DatePickerBehavior(selector, this.options) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isOnSelectEventEnabled()
			{
				return DatePicker.this.isOnSelectEventEnabled();
			}

			@Override
			public void onSelect(AjaxRequestTarget target, String date)
			{
				DatePicker.this.onSelect(target, date);
			}

			@Override
			protected JQueryAjaxPostBehavior newOnSelectBehavior()
			{
				return new OnSelectBehavior(this, DatePicker.this);
			}
		};
	}
}
