package de.hahn.blog.tablecellselection.view.beans;

import javax.faces.component.UIComponent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Key;
import oracle.jbo.Row;


public class TableCellSelectionBean {
    private static ADFLogger logger = ADFLogger.createADFLogger(TableCellSelectionBean.class);
    String cellInfo;

    public TableCellSelectionBean() {
    }

    public void handleTableCellSelection(ClientEvent event) {
        // get payload which is the ui component which fired the event
        UIComponent ui = (UIComponent)event.getParameters().get("payload");
        // get the column from the event which is sent too
        String column = (String)event.getParameters().get("column");
        RichOutputText rt = (RichOutputText)ui;
        // get current row key
        DCBindingContainer bindingContainer = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding binding = bindingContainer.findIteratorBinding("EmployeesViewIterator");
        Row currentRow = binding.getCurrentRow();
        Key key = currentRow.getKey();
        // compile info about clicked cell
        String out = "Payload:" + ui + "<br>column: "+ column + "<br>val: " + rt.getValue() + "<br>key: "+key.toString();
        logger.info(out);
        setCellInfo(out);
    }

    public void setCellInfo(String cellInfo) {
        this.cellInfo = cellInfo;
    }

    public String getCellInfo() {
        return cellInfo;
    }
}
