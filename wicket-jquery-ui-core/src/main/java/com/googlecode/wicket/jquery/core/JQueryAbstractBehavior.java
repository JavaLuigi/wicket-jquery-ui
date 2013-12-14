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
package com.googlecode.wicket.jquery.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.settings.JavaScriptLibrarySettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.wicket.jquery.core.settings.IJQueryLibrarySettings;
import com.googlecode.wicket.jquery.core.settings.JQueryLibrarySettings;

/**
 * Provides the base class for every jQuery behavior.
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public abstract class JQueryAbstractBehavior extends Behavior
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(JQueryAbstractBehavior.class);

	/**
	 * Gets the {@link IJQueryLibrarySettings}
	 *
	 * @return Default internal {@link IJQueryLibrarySettings} if {@link Application}'s {@link JavaScriptLibrarySettings} is not an instance of {@link IJQueryLibrarySettings}
	 */
	public static IJQueryLibrarySettings getJQueryLibrarySettings()
	{
		if (Application.exists() && (Application.get().getJavaScriptLibrarySettings() instanceof IJQueryLibrarySettings))
		{
			return (IJQueryLibrarySettings) Application.get().getJavaScriptLibrarySettings();
		}

		return JQueryLibrarySettings.get();
	}


	/**
	 * Behavior name
	 */
	private final String name;

	/**
	 * Additional references
	 */
	private final List<ResourceReference> references;

	/**
	 * Constructor.
	 * @param name the name of the behavior. It is used in the token so the behavior can be identified in the generated page.
	 */
	public JQueryAbstractBehavior(final String name)
	{
		this.name = name;
		this.references = new ArrayList<ResourceReference>();
	}


	/**
	 * Adds a reference to be added at {@link #renderHead(Component, IHeaderResponse)} time.
	 * @param reference a {@link CssResourceReference}, a {@link JavaScriptResourceReference} or a {@link JQueryPluginResourceReference}
	 * @return true (as specified by Collection.add(E))
	 */
	public boolean add(ResourceReference reference)
	{
		return this.references.add(reference);
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		// Gets the library settings //
		IJQueryLibrarySettings settings = getJQueryLibrarySettings();

		// jQuery UI resource reference //
		if (settings.getJQueryUIReference() != null)
		{
			response.render(new PriorityHeaderItem(JavaScriptHeaderItem.forReference(settings.getJQueryUIReference())));
		}

		// jQuery Globalize resource reference //
		if (settings.getJQueryGlobalizeReference() != null)
		{
			response.render(new PriorityHeaderItem(JavaScriptHeaderItem.forReference(settings.getJQueryGlobalizeReference())));
		}

		// Additional resource references //
		for(ResourceReference reference : this.references)
		{
			if (reference instanceof CssResourceReference)
			{
				response.render(new PriorityHeaderItem(CssHeaderItem.forReference(reference)));
			}

			if (reference instanceof JavaScriptResourceReference)
			{
				response.render(new PriorityHeaderItem(JavaScriptHeaderItem.forReference(reference)));
			}
		}

		// Adds the statement //
		AjaxRequestTarget target = component.getRequestCycle().find(AjaxRequestTarget.class);

		if (target != null)
		{
			target.appendJavaScript(this.toString());
		}
		else
		{
			this.renderScript(JavaScriptHeaderItem.forScript(this.toString(), this.getToken()), response);
		}
	}

	/**
	 * Renders the {@link Behavior}'s javascript<br/>
	 * This can be overridden to provides a priority:</br>
	 * <code>response.render(new PriorityHeaderItem(script));</code>
	 *
	 * @param script the {@link JavaScriptHeaderItem}
	 * @param response the {@link IHeaderResponse}
	 */
	protected void renderScript(JavaScriptHeaderItem script, IHeaderResponse response)
	{
		response.render(script);
	}

	/**
	 * Gets the jQuery statement.
	 * @return Statement like 'jQuery(function() { ... })'
	 */
	protected abstract String $();


	// Properties //

	/**
	 * Get the unique behavior token that act as the script id.
	 * @return the token
	 */
	String getToken()
	{
		return String.format("jquery-%s-%d", this.name, this.hashCode());
	}

	// Events //
	@Override
	public void onConfigure(Component component)
	{
		super.onConfigure(component);

		if (Application.exists() && Application.get().usesDevelopmentConfig())
		{
			if (!Application.get().getMarkupSettings().getStripWicketTags())
			{
				LOG.warn("Application > MarkupSettings > StripWicketTags: setting is currently set to false. It is highly recommended to set it to true to prevent widget misbehaviors.");
			}
		}
	}

	@Override
	public final String toString()
	{
		return this.$();
	}
}
