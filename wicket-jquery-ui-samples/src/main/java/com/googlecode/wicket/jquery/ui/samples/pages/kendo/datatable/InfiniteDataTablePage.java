package com.googlecode.wicket.jquery.ui.samples.pages.kendo.datatable;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.samples.data.ProductsDAO;
import com.googlecode.wicket.jquery.ui.samples.data.bean.Product;
import com.googlecode.wicket.kendo.ui.datatable.DataTable;
import com.googlecode.wicket.kendo.ui.datatable.column.IColumn;
import com.googlecode.wicket.kendo.ui.datatable.column.PropertyColumn;

public class InfiniteDataTablePage extends AbstractDataTablePage
{
	private static final long serialVersionUID = 1L;

	public InfiniteDataTablePage()
	{
		// DataTable //
		IDataProvider<Product> provider = newDataProvider();
		List<IColumn> columns = newColumnList();

		Options options = new Options();
		options.set("height", 430);
		options.set("scrollable", "{ virtual: true }"); //infinite scroll

		final DataTable<Product> table = new DataTable<Product>("datatable", columns, provider, 15, options);
		this.add(table);
	}


	private static IDataProvider<Product> newDataProvider()
	{
		return new ListDataProvider<Product>(ProductsDAO.all());
	}

	private static List<IColumn> newColumnList()
	{
		List<IColumn> columns = new ArrayList<IColumn>();

		columns.add(new PropertyColumn("ID", "id", 30));
		columns.add(new PropertyColumn("Name", "name"));
		columns.add(new PropertyColumn("Description", "description"));
		columns.add(new PropertyColumn("Unit Price", "price"));

		return columns;
	}
}
