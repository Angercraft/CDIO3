package juniorMatador;

import gui_fields.GUI_Field;
import java.awt.*;

public class Field extends GUI_Field {

    private int fieldNo;

    public Field(Color color, String name, int fieldNo){
        super(color, color, name, "", "");
        this.fieldNo = fieldNo;
    }

    public int getFieldNo(){
        return fieldNo;
    }

    public void setFieldNo(int fieldNo){
        this.fieldNo=fieldNo;
    }

	public void landOnField() {
		System.out.println("No action has been specified for this field");
	}

}