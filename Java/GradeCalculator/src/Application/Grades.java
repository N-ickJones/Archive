package Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Grades {

    private final SimpleStringProperty col, col2;

    public Grades(String col, String col2) {
        this.col = new SimpleStringProperty(col);
        this.col2 = new SimpleStringProperty(col2);
    }

    public StringProperty colProperty() {
        return this.col;
    }

    public StringProperty col2Property() {
        return this.col2;
    }

    public void setCol(String col) {
        this.col.set(col);
    }

    public void setCol2(String col2) {
        this.col2.set(col2);
    }
}
