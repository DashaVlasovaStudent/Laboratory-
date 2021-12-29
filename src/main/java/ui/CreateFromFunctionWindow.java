package ui;

import functions.*;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CreateFromFunctionWindow extends JDialog {
    private final JLabel label1 = new JLabel("Enter the quantity of points:");
    private final JLabel label2 = new JLabel("from");
    private final JLabel label3 = new JLabel("to");

    private final JTextField textField1 = new JTextField("");
    private final JTextField textField2 = new JTextField("");
    private final JTextField textField3 = new JTextField("");

    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{
            "Constant function", "Cos function", "Identity function", "Sqr function", "Unit function", "Zero function"
    });
    private final JButton createButton = new JButton("Create");

    private TabulatedFunction function;
    private final TabulatedFunctionFactory factory;

    public CreateFromFunctionWindow(TabulatedFunctionFactory factory) {
        setTitle("Creating function from function");
        this.factory = factory;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(400, 200);

        setModal(true);
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
        functionsMap.put("Constant function", new ConstantFunction(3));
        functionsMap.put("Cos function", new CosFunction());
        functionsMap.put("Identity function", new IdentityFunction());
        functionsMap.put("Sqr function", new SqrFunction());
        functionsMap.put("Unit function", new UnitFunction());
        functionsMap.put("Zero function", new ZeroFunction());

        String functionName = (String) comboBox.getSelectedItem();
        MathFunction selectedFunction = functionsMap.get(functionName);
        double from = Double.parseDouble(textField2.getText());
        double to = Double.parseDouble(textField3.getText());
        int count = Integer.parseInt(textField1.getText());

        function = factory.create(selectedFunction, from, to, count);
        System.out.println(function);
    }

    private void addButtonListeners() {
        createButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(textField1.getText());
                if (size <= 0) {
                    Exceptions.showMessage("Enter the positive number");
                }
                createFunction();
                dispose();
            } catch (NumberFormatException exp) {
                Exceptions.showMessage("Enter integer");
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        getContentPane().setBackground(Color.blue);

        label1.setFont(new Font("Consolas", Font.BOLD, 15));
        label1.setForeground(Color.CYAN);
        label1.setVerticalAlignment(JLabel.TOP);

        label2.setFont(new Font("Consolas", Font.BOLD, 15));
        label2.setForeground(Color.CYAN);
        label2.setVerticalAlignment(JLabel.TOP);

        label3.setFont(new Font("Consolas", Font.BOLD, 15));
        label3.setForeground(Color.CYAN);
        label3.setVerticalAlignment(JLabel.TOP);

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
                .addComponent(createButton)
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
                .addComponent(createButton)
        );
    }

    public static void main(String[] args) {
        new CreateFromFunctionWindow(new LinkedListTabulatedFunctionFactory());
    }
}
