package ui;

import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Settings extends JDialog {
    JTabbedPane tabbedPane = new JTabbedPane();
    private static TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
    private static boolean flag = true;

    public Settings() {
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(320, 200);

        tabbedPane.setPreferredSize(new Dimension(300, 300));
        JPanel factorySelection = new Panel();
        tabbedPane.addTab("Factories", factorySelection);

        getContentPane().add(tabbedPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static TabulatedFunctionFactory getFactory() {
        return factory;
    }


    static class Panel extends JPanel {
        ButtonGroup group = new ButtonGroup();

        public Panel() {
            JRadioButton fromArrays = new JRadioButton("Arrays", !flag);
            group.add(fromArrays);

            JRadioButton fromList = new JRadioButton("Linked list", flag);
            group.add(fromList);

            add(fromArrays);
            add(fromList);

            fromArrays.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new LinkedListTabulatedFunctionFactory();
                    flag = true;
                }
            });
            fromList.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    factory = new LinkedListTabulatedFunctionFactory();
                    flag = false;
                }
            });
        }
    }

    public static void main(String[] args) {
        new Settings();
    }
}
