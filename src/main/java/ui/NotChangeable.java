package ui;

import java.io.Serial;
import java.util.List;

public class NotChangeable extends Table {


    @Serial
    private static final long serialVersionUID = 6687923912223003365L;

    public NotChangeable(List<String> xValues, List<String> yValues) {
        super(xValues, yValues);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
