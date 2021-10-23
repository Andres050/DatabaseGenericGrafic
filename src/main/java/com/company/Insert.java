package com.company;

import com.company.AccesoDatos.CRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Insert extends JFrame {

    private JLabel[] labels;
    private JTextField[] textFields;
    private JPanel panel = new JPanel(null);
    private JScrollPane jScrollPane = new JScrollPane(panel);

    public Insert (String title, Business business, int type) {
        super(title);

        this.setSize(700,1000);
        this.setLayout(null);
        this.setVisible(true);
        this.setContentPane(panel);
        setResizable(false);

        setLabels(allLabels(business));
        setTextFields(allTextFields(business));

        JButton boton1=new JButton("Aceptar");
        boton1.setText("SEND");
        boton1.setSize(100,100);
        boton1.setLocation(350,300);
        boton1.setVisible(true);
        panel.add(boton1);
        panel.setAutoscrolls(true);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jScrollPane, LEFT_ALIGNMENT);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //crudProduct.insert(new Item());
                setVisible(false);
                dispose();
                if (type==0) {
                    business.add(getTextFields());
                }else if (type==1) {
                    business.update(Main.frame.primaryUPDATE, Main.frame.newModel(),getTextFields());
                }
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
    }

    public JLabel[] allLabels(Business business) {
        int numberOfFields = business.getCrudProduct().getColumnName().length;
        JLabel[] Jlabel = new JLabel[numberOfFields];
        int more = 100;int x = 20;

        for (int j = 0; j < Jlabel.length; j++) {
            Jlabel[j] = new JLabel();
            Jlabel[j].setText(business.getCrudProduct().getColumnName()[j]);
            Jlabel[j].setVisible(true);
            Jlabel[j].setSize(80, 50);
            Jlabel[j].setLocation(x, more);
            more += 50;
        }
        for (int j = 0; j < Jlabel.length; j++) {
            panel.add(Jlabel[j]);
        }
        return Jlabel;
    }

    public JTextField[] allTextFields(Business business) {
        int numberOfFields = business.getCrudProduct().getColumnName().length;
        JTextField[] tfs = new JTextField[numberOfFields];
        int more = 100;
        int x = 170;
        for (int j = 0; j < tfs.length; j++) {
            tfs[j] = new JTextField();
            tfs[j].setVisible(true);
            tfs[j].setSize(150, 50);
            tfs[j].setLocation(x, more);
            more += 50;
        }
        for (int j = 0; j < tfs.length; j++) {
            panel.add(tfs[j]);
        }
        return tfs;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public void setTextFields(JTextField[] textFields) {
        this.textFields = textFields;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public JTextField[] getTextFields() {
        return textFields;
    }
}
