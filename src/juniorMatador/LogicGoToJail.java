package juniorMatador;

public class LogicGoToJail extends LogicField {

    private int ticket = 3;
    private int JailLocation;

    public LogicGoToJail(int fieldNo) {
        super(fieldNo, "GOTOJAIL");
    }

    public int getTicket() {
        return ticket;
    }

    public int getJailLocation() {
        return JailLocation;
    }

    /**
     * Checks every field object initialized. Locates first field with type 'JAIL' and saves the location.
     * @param fields Array of field objects.
     */
    public void findJail(LogicField[] fields) {
        for (LogicField field : fields) {
            if (field.getType().equals("JAIL")) {
                JailLocation = field.getFieldNo();
            }
        }
    }
}
