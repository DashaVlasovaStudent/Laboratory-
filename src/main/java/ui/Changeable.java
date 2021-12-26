package ui;

import java.io.Serial;
import java.util.List;

public class Changeable extends Table {

    @Serial
    private static final long serialVersionUID = 2719471924901042822L;

    public Changeable(List<String> xValues, List<String> yValues) {
        super(xValues, yValues);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case X_COLUMN_NUMBER -> false;
            case Y_COLUMN_NUMBER -> true;
            default -> false;
        };
    }
}
