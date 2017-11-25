package juniorMatador;

public abstract class LogicField {

    private int fieldNo;
    private String type;

    public LogicField(int fieldNo, String type) {
        this.fieldNo = fieldNo;
        this.type = type;
    }

    public int getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(int fieldNo) {
        this.fieldNo = fieldNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}