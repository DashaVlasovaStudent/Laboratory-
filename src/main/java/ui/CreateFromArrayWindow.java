package ui;

import exceptions.ArrayIsNotSortedException;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class CreateFromArrayWindow extends JDialog {
    private final List<String> xValues = new ArrayList<>();
    private final List<String> yValues = new ArrayList<>();

    private final AbstractTableModel tableModel = new Table(xValues, yValues);
    private final JTable table = new JTable(tableModel);
    private final JLabel label = new JLabel("Number of points:");

    private final JTextField textField = new JTextField("");
    private final JButton addButton = new JButton("Add");
    private final JButton createButton = new JButton("Create");
    private final JButton clearButton = new JButton("Clear");

    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public CreateFromArrayWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;

        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addButton)
                )
                .addComponent(tableScrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addComponent(clearButton)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(textField)
                        .addComponent(addButton)
                )
                .addComponent(tableScrollPane)
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addComponent(clearButton)
                )
        );
    }

    private void addButtonListeners() {
        class AddingAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    if (size <= 0) {
                        Exceptions.showMessage("Enter positive number.");
                    }
                    for (int i = 0; i < size; i++) {
                        xValues.add(" ");
                        yValues.add(" ");
                    }
                    clearButton.setEnabled(true);
                    tableModel.fireTableDataChanged();
                } catch (NumberFormatException exception) {
                    Exceptions.showMessage("Enter integer.");
                }
            }
        }
        clearButton.setEnabled(false);
        textField.addActionListener(new AddingAction());
        addButton.addActionListener(new AddingAction());
        createButton.addActionListener(e -> {
            try {
                int size = xValues.size();
                double[] x = new double[size];
                double[] y = new double[size];
                for (int i = 0; i != size; i++) {
                    x[i] = Double.parseDouble(xValues.get(i));
                    y[i] = Double.parseDouble(yValues.get(i));
                }
                function =  factory.create(x, y);
                System.out.println(function);
                dispose();
            } catch (NumberFormatException exp) {
                Exceptions.showMessage("Enter decimal fraction with point");
            } catch (ArrayIsNotSortedException exp) {
                Exceptions.showMessage("X values are not sorted ascending");//неотсортированы по возрастанию
            } catch (IllegalArgumentException exp) {
                Exceptions.showMessage(exp.getMessage());
            }
        });

        clearButton.addActionListener(e -> {
            int flag = JOptionPane.showConfirmDialog(null, "Are you sure? Area will be clear", "Warning", JOptionPane.YES_NO_OPTION);
            if (flag == 0) {
                int size = xValues.size();
                xValues.clear();
                yValues.clear();
                for (int i = 0; i < size; i++) {
                    xValues.add(" ");
                    yValues.add(" ");
                }
                tableModel.fireTableDataChanged();
            }
        });
    }

    public static void main(String[] args) {
        new CreateFromArrayWindow(new LinkedListTabulatedFunctionFactory());
    }
}
