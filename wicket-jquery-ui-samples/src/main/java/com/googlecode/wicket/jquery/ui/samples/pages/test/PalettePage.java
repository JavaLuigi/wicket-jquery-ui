package com.googlecode.wicket.jquery.ui.samples.pages.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.model.util.ListModel;

import com.googlecode.wicket.jquery.ui.form.palette.Palette;
import com.googlecode.wicket.jquery.ui.samples.TemplatePage;

public class PalettePage extends TemplatePage
{
	private static final long serialVersionUID = 1L;

	public PalettePage()
	{
		this.init();
	}

	private void init()
	{
		final Form<Void> form = new Form<Void>("form");
		this.add(form);

		// Palette //
		form.add(new Palette<String>("palette", new ListModel<String>(new ArrayList<String>()), new CollectionModel<String>(Arrays.asList("Choice #1", "Choice #2", "Choice #3", "Choice #4")), new ChoiceRenderer<String>(), 6, true));
	}
}
