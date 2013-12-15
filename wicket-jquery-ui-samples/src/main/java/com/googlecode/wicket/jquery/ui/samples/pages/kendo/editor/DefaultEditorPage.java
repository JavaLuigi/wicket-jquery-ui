package com.googlecode.wicket.jquery.ui.samples.pages.kendo.editor;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import com.googlecode.wicket.jquery.ui.kendo.button.Button;
import com.googlecode.wicket.jquery.ui.kendo.editor.Editor;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;

public class DefaultEditorPage extends AbstractEditorPage
{
	private static final long serialVersionUID = 1L;

	public DefaultEditorPage()
	{
		Form<Void> form = new Form<>("form");
		this.add(form);

		// FeedbackPanel //
		final FeedbackPanel feedback = new JQueryFeedbackPanel("feedback");
		form.add(feedback);

		// ComboBox //
		final Editor<String> editor = new Editor<String>("editor", Model.of("<p>test</p>"));
		form.add(editor);

		// Buttons //
		form.add(new Button("button") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				String html = editor.getModelObject();

				this.info(html != null ? html : "empty");
			}
		});
	}
}
