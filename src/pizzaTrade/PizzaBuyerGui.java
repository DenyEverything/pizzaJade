package pizzaTrade;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class PizzaBuyerGui extends JFrame {
    private PizzaBuyerAgent myAgent;
    private JTextField titleField, ingredientsField;

    PizzaBuyerGui(PizzaBuyerAgent a) {
        super(a.getLocalName());

        myAgent = a;
        JPanel p = new JPanel();

        p.setLayout(new GridLayout(4, 2));
        p.add(new JLabel("Search for pizza name:"));
        titleField = new JTextField(15);
        p.add(titleField);

        p.add(new JLabel("OR Ingredients:"));
        ingredientsField = new JTextField(15);
        p.add(ingredientsField);

        p.add(new JLabel("Search by:"));
        p.add(new JLabel(" "));
        JRadioButton name,ing;
        ButtonGroup buttonGroup = new ButtonGroup();
        name = new JRadioButton("pizza name");
        ing = new JRadioButton("ingredients");
        buttonGroup.add(name);
        buttonGroup.add(ing);
        p.add(name);
        p.add(ing);
        name.setSelected(true);

        getContentPane().add(p, BorderLayout.CENTER);
        JButton addButton = new JButton("Search");
        addButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    String title = titleField.getText().trim();
                    String[] ingredientsArray = ingredientsField.getText().split(",");
                    ArrayList<String> ingredientsList = new ArrayList<String>(Arrays.asList(ingredientsArray));
                    myAgent.updateTarget(title, ingredientsList, name.isSelected());

                    titleField.setText("");
                    ingredientsField.setText("");
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(PizzaBuyerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } );
        p = new JPanel();
        p.add(addButton);
        getContentPane().add(p, BorderLayout.SOUTH);

        // Make the agent terminate when the user closes
        // the GUI using the button on the upper right corner
        addWindowListener(new	WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        } );

        setResizable(false);
    }

    public void showGui() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setVisible(true);
    }

}


