package ui;

import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Derive extends JDialog {
    private final ArrayList<String> xValues = new ArrayList<>();
    private final ArrayList<String> yValues = new ArrayList<>();
    private final ArrayList<String> resultX = new ArrayList<>();
    private final ArrayList<String> resultY = new ArrayList<>();

    private final AbstractTableModel tableModel = new Changeable(xValues, yValues);
    private final JTable table = new JTable(tableModel);

    private final AbstractTableModel resultTableModel = new NotChangeable(resultX, resultY);
    private final JTable resultTable = new JTable(resultTableModel);

    private final JButton createButton = new JButton("Create");
    private final JButton resultButton = new JButton("Derive");


    private final TabulatedFunctionFactory factory;
    private TabulatedFunction function;
    private TabulatedFunction resultFunction;

    public Derive(TabulatedFunctionFactory factory) {
        setTitle("Derive function");
        this.factory = factory;

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(800, 400);

        resultButton.setEnabled(false);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JScrollPane scrollPane = new JScrollPane(table);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(110)
                        .addComponent(resultButton))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane)
                        .addGap(40)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addGap(25)
                        .addComponent(createButton)
                ));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(resultButton)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                ));
    }

    private void setValues(ArrayList<String> xValues, ArrayList<String> yValues, TabulatedFunction function) {
        xValues.clear();
        yValues.clear();
        for (int i = 0; i < function.getCount(); i++) {
            xValues.add(Double.toString(function.getX(i)));
            yValues.add(Double.toString(function.getY(i)));
        }
    }

    private void setTable(TabulatedFunction function) {
        if (function != null) {
            this.function = function;
            resultButton.setEnabled(true);
            setValues(xValues, yValues, this.function);
            tableModel.fireTableDataChanged();
        }
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem fromTable = new JMenuItem("from the table");
                    JMenuItem fromFunction = new JMenuItem("from the function");

                    fromTable.addActionListener(ee -> {
                        CreateFromArrayWindow window = new CreateFromArrayWindow(factory);
                        setTable(window.getFunction());
                    });

                    fromFunction.addActionListener(ee -> {
                        CreateFromFunctionWindow window = new CreateFromFunctionWindow(factory);
                        setTable(window.getFunction());
                    });

                    popupMenu.add(fromTable);
                    popupMenu.addSeparator();
                    popupMenu.add(fromFunction);
                    popupMenu.show(createButton, createButton.getWidth() + 1, createButton.getHeight() / 30);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        resultButton.addActionListener(e -> {
            try {
                TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);
                resultFunction = operator.derive(function);
                setValues(resultX, resultY, resultFunction);
                resultTableModel.fireTableDataChanged();
            } catch (NullPointerException exp) {
                Exceptions.showMessage("Enter the function");
            }
        });


    }

    public static void main(String[] args) {
        new Derive(new LinkedListTabulatedFunctionFactory());
    }
}
