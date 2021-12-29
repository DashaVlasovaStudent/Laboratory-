package ui;

import exceptions.InconsistentFunctionsException;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Operations extends JDialog {
    private static final int FIRST_FUNCTION = 0;
    private static final int SECOND_FUNCTION = 1;

    private final List<String> xValues = new ArrayList<>();
    private final List<String> yValues = new ArrayList<>();
    private final List<String> secondXValues = new ArrayList<>();
    private final List<String> secondYValues = new ArrayList<>();

    private final List<String> resultXValues = new ArrayList<>();
    private final List<String> resultYValues = new ArrayList<>();

    private final AbstractTableModel firstTableModel = new Changeable(xValues, yValues);
    private final JTable firstTable = new JTable(firstTableModel);

    private final AbstractTableModel secondTableModel = new Changeable(secondXValues, secondYValues);
    private final JTable secondTable = new JTable(secondTableModel);

    private final AbstractTableModel resultTableModel = new NotChangeable(resultXValues, resultYValues);
    private final JTable resultTable = new JTable(resultTableModel);


    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "÷"});

    private final JButton createButton = new JButton("Create");
    private final JButton createButtonTwo = new JButton("Create");
    private final JButton resultButton = new JButton("=");

    private final TabulatedFunctionFactory factory;
    private TabulatedFunction firstFunction;
    private TabulatedFunction secondFunction;
    private TabulatedFunction resultFunction;

    public Operations(TabulatedFunctionFactory factory) {
        setTitle("Operations with functions");
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(800, 400);

        comboBox.setPreferredSize(new Dimension(2, 2));
        firstTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        getContentPane().setBackground(Color.YELLOW);

        createButton.setBackground(Color.orange);
        createButton.setForeground(Color.DARK_GRAY);

        createButtonTwo.setBackground(Color.orange);
        createButtonTwo.setForeground(Color.DARK_GRAY);

        resultButton.setBackground(Color.orange);
        resultButton.setForeground(Color.DARK_GRAY);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JScrollPane scrollPane = new JScrollPane(firstTable);
        JScrollPane scrollPaneTwo = new JScrollPane(secondTable);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(8)
                        .addComponent(scrollPane)
                        .addComponent(comboBox)
                        .addComponent(scrollPaneTwo)
                        .addComponent(resultButton)
                        .addComponent(resultScrollPane))
                .addGroup(layout.createSequentialGroup()
                        .addGap(8)
                        .addComponent(createButton)
                        .addGap(198)
                        .addComponent(createButtonTwo)
                        .addGap(125))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(comboBox)
                                .addGap(110)
                        )
                        .addComponent(scrollPaneTwo)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130)
                                .addComponent(resultButton)
                        )
                        .addComponent(resultScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createButton)
                        .addGap(60)
                        .addComponent(createButtonTwo))
        );
    }

    private void setValues(List<String> xValues, List<String> yValues, TabulatedFunction function) {
        xValues.clear();
        yValues.clear();
        for (int i = 0; i < function.getCount(); i++) {
            xValues.add(Double.toString(function.getX(i)));
            yValues.add(Double.toString(function.getY(i)));
        }
    }

    private void getPopupMenu(JButton button, int flag) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem fromTable = new JMenuItem("from table");
        JMenuItem fromFunction = new JMenuItem("from function");

        fromTable.addActionListener(ee -> {
            CreateFromArrayWindow window = new CreateFromArrayWindow(factory);
            setTable(window.getFunction(), flag);
        });

        fromFunction.addActionListener(ee -> {
            CreateFromFunctionWindow window = new CreateFromFunctionWindow(factory);
            setTable(window.getFunction(), flag);
        });

        popupMenu.add(fromTable);
        popupMenu.addSeparator();
        popupMenu.add(fromFunction);
        popupMenu.show(button, button.getWidth() + 1, button.getHeight() / 30);
    }

    private void setTable(TabulatedFunction function, int flag) {
        if (function != null) {
            switch (flag) {
                case FIRST_FUNCTION -> {
                    firstFunction = function;
                    setValues(xValues, yValues, firstFunction);
                    firstTableModel.fireTableDataChanged();
                }
                case SECOND_FUNCTION -> {
                    secondFunction = function;
                    setValues(secondXValues, secondYValues, secondFunction);
                    secondTableModel.fireTableDataChanged();
                }
            }
        }
    }

    private void addButtonListeners() {
        createButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButton, FIRST_FUNCTION);
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

        createButtonTwo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getPopupMenu(createButtonTwo, SECOND_FUNCTION);
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
                TabulatedFunctionOperationService operation = new TabulatedFunctionOperationService(factory);
                switch ((String) comboBox.getSelectedItem()) {
                    case "+" -> resultFunction = operation.sum(firstFunction, secondFunction);
                    case "-" -> resultFunction = operation.subtract(firstFunction, secondFunction);
                    case "*" -> resultFunction = operation.multiplication(firstFunction, secondFunction);
                    case "÷" -> resultFunction = operation.divide(firstFunction, secondFunction);
                }
                setValues(resultXValues, resultYValues, resultFunction);
                resultTableModel.fireTableDataChanged();
            } catch (InconsistentFunctionsException exp) {
                Exceptions.showMessage(exp.getMessage());
            } catch (NullPointerException exp) {
                Exceptions.showMessage("Enter BOTH functions");
            }
        });

    }

    public static void main(String[] args) {
        new Operations(new LinkedListTabulatedFunctionFactory());
    }
}
