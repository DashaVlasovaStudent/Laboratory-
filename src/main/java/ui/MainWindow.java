package ui;

import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import io.FunctionsIO;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainWindow extends JFrame {

    private final JButton arrayButton = new JButton("Create function from array");
    private final JButton buttonTabulatedFunction = new JButton("Create function from functions");
    private final JButton operationButton = new JButton("Operations");
    private final JButton settingsButton = new JButton("Settings");
    private final JButton deriveButton = new JButton("Derive function");
    private final JButton uploadFunction = new JButton("Upload function");
    private final JButton saveFunction = new JButton("Save function");
    private final JFileChooser fileChooser = new JFileChooser();
    private final TabulatedFunctionFactory tabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
    private TabulatedFunction tabulatedFunction;
    ImageIcon icon = new ImageIcon("src/бараш.png");


    public MainWindow() {
        super("MainWindow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setSize(425, 510);
        setResizable(false);

        compose();
        addButtonListeners();

        setLocationRelativeTo(null);
        setIconImage(icon.getImage());
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        getContentPane().setBackground(Color.DARK_GRAY);

        arrayButton.setBackground(Color.LIGHT_GRAY);
        arrayButton.setForeground(Color.BLACK);

        buttonTabulatedFunction.setBackground(Color.LIGHT_GRAY);
        buttonTabulatedFunction.setForeground(Color.BLACK);

        operationButton.setBackground(Color.LIGHT_GRAY);
        operationButton.setForeground(Color.BLACK);

        settingsButton.setBackground(Color.LIGHT_GRAY);
        settingsButton.setForeground(Color.BLACK);

        deriveButton.setBackground(Color.LIGHT_GRAY);
        deriveButton.setForeground(Color.BLACK);

        uploadFunction.setBackground(Color.LIGHT_GRAY);
        uploadFunction.setForeground(Color.BLACK);

        saveFunction.setBackground(Color.LIGHT_GRAY);
        saveFunction.setForeground(Color.BLACK);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(arrayButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(uploadFunction))
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(saveFunction))
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(buttonTabulatedFunction)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(operationButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(deriveButton)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(100)
                        .addComponent(settingsButton)
                )

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(70)
                .addComponent(uploadFunction)
                .addGap(20)
                .addComponent(saveFunction)
                .addGap(20)
                .addComponent(arrayButton)
                .addGap(20)
                .addComponent(buttonTabulatedFunction)
                .addGap(20)
                .addComponent(operationButton)
                .addGap(20)
                .addComponent(deriveButton)
                .addGap(20)
                .addComponent(settingsButton)
                .addGap(20)
        );
    }

    private void addButtonListeners() {
        arrayButton.addActionListener(e -> new CreateFromArrayWindow(new LinkedListTabulatedFunctionFactory()));
        buttonTabulatedFunction.addActionListener(e -> new CreateFromFunctionWindow(Settings.getFactory()));
        operationButton.addActionListener(e -> new Operations(Settings.getFactory()));
        settingsButton.addActionListener(e -> new Settings());
        deriveButton.addActionListener(e -> new Derive(Settings.getFactory()));

        uploadFunction.addActionListener(e -> {
            fileChooser.showOpenDialog(null);
            File uploadFile = fileChooser.getSelectedFile();
            if (uploadFile != null) {
                try {
                    BufferedInputStream reader = new BufferedInputStream(new FileInputStream(uploadFile));
                    tabulatedFunction = FunctionsIO.readTabulatedFunction(reader, tabulatedFunctionFactory);
                    saveFunction.setEnabled(true);
                } catch (IOException exception) {
                    Exceptions.showMessage("File is not found or some error happened");
                } catch (NumberFormatException e1) {
                    Exceptions.showMessage("Incorrect data");
                }
            }
        });

        saveFunction.addActionListener(e -> {
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                FunctionsIO.writeTabulatedFunction(outputStream, tabulatedFunction);
            } catch (IOException exception) {
                Exceptions.showMessage("There isn't any file in which function save");
            }
        });

    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
