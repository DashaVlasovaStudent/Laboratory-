package ui;

import functions.*;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CreateFromFunctionWindow extends JDialog {
    private final JLabel label1 = new JLabel("Enter the number of points:");
    private final JLabel label2 = new JLabel("From");
    private final JLabel label3 = new JLabel("to");

    private final JTextField textField1 = new JTextField("");
    private final JTextField textField2 = new JTextField("");
    private final JTextField textField3 = new JTextField("");

    private final JComboBox<String> comboBox = new JComboBox<>(new String[]
            {
                    "Constant function", "Cos function", "Identity function", "Sqr function", "Unit function", "Zero Function"
            });

    private final JButton create = new JButton("Create");
    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public CreateFromFunctionWindow(TabulatedFunctionFactory factory) {
        this.factory = factory;
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 180);
        create.setFocusPainted(false);

        addButtonListeners();
        compose();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    private void createFunction() {
        Map<String, MathFunction> functionsMap = new HashMap<>();
        functionsMap.put("Constant Function", new ConstantFunction(2));
        functionsMap.put("Cos function", new CosFunction());
        functionsMap.put("Identity Function", new IdentityFunction());
        functionsMap.put("Sqr Function", new SqrFunction());
        functionsMap.put("Unit Function", new UnitFunction());
        functionsMap.put("Zero Function", new ZeroFunction());

        String functionName = (String) comboBox.getSelectedItem();
        MathFunction selectedFunction = functionsMap.get(functionName);
        double from = Double.parseDouble(textField2.getText());
        double to = Double.parseDouble(textField3.getText());
        int count = Integer.parseInt(textField1.getText());

        function = factory.create(selectedFunction, from, to, count);
        //Table tableFunction = new Table()
        System.out.println(function);
    }

    private void addButtonListeners() {
        create.addActionListener(e -> {
            try {
                int size = Integer.parseInt(textField1.getText());
                if (size <= 0) {
                    Exceptions.showMessage("Enter positive number");
                }
                createFunction();
                dispose();
            } catch (NumberFormatException exp) {
                Exceptions.showMessage("Enter integer");
            } catch (IllegalArgumentException exp) {
                Exceptions.showMessage(exp.getMessage());
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        textField1.setSize(new Dimension(50, 10));
        textField2.setSize(new Dimension(50, 10));
        textField3.setSize(new Dimension(50, 10));

        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label1)
                        .addComponent(textField1))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label2)
                        .addComponent(textField2)
                        .addComponent(label3)
                        .addComponent(textField3))
                .addComponent(comboBox)
                .addComponent(create)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label1)
                        .addComponent(textField1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(label2)
                        .addComponent(textField2)
                        .addComponent(label3)
                        .addComponent(textField3))
                .addComponent(comboBox)
                .addComponent(create)
        );
    }

    public static void main(String[] args) {
        new CreateFromFunctionWindow(new LinkedListTabulatedFunctionFactory());
    }
}
