package com.company;

import com.company.AccesoDatos.CRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Grafic extends JFrame{
    private JButton INSERTARButton;
    private JButton LEERAllButton;
    private JButton READwithPrimaryKey;
    private JButton UPDATEButton;
    private JButton DELETEwithPrimaryKey;
    private JPanel mainPanel;
    private JTable table1;
    private JTextField databaseField;
    private JTextField primaryDELETE;
    private JButton DELETEALLTABLEButton;
    public JTextField textFieldUserName;
    public JTextField textFieldPassword;
    public JTextField textFieldTable;
    private JButton GETTABLEButton;
    private JButton GETDATABASEButton;
    private JLabel DAtaBaseNameTop;
    private JLabel tableNameTop;
    private JLabel TAbleNameTop;
    private JTextField primaryREAD;
    public JTextField primaryUPDATE;

    private CRUD crud = new CRUD();
    private final Business business = new Business(crud);
    private JLabel jLabel = new JLabel();

    public Grafic(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1000, 400);
        this.pack();

        DAtaBaseNameTop.setText("DATABASE: "+ crud.database);
        tableNameTop.setText("Table: "+ crud.table);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.setEnabled(true);
        table1.getTableHeader().setReorderingAllowed(false);

        INSERTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //crudProduct.insert(new Item());
                int type = 0;
                Insert insert = new Insert("INSERT",business,type);
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        LEERAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                business.list(newModel());
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        READwithPrimaryKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                business.search(primaryREAD, newModel());
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int type = 1;
                Insert insert = new Insert("UPDATE",business,type);
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        //NO TERMINADO DESDE AQUI
        DELETEwithPrimaryKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                business.deleteID(primaryDELETE);
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        DELETEALLTABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int confirmado = JOptionPane.showConfirmDialog(
                        null,
                        "¿Lo confirmas?");
                if (JOptionPane.OK_OPTION == confirmado) {
                    business.restartCataleg();
                }
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        GETDATABASEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                crud.database = databaseField.getText();
                System.out.println(databaseField.getText());
                DAtaBaseNameTop.setText("DATABASE: "+ crud.database);
            }
        });
        GETTABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                crud.table = textFieldTable.getText();
                System.out.println(textFieldTable.getText());
                tableNameTop.setText("Table: "+ crud.table);
            }
        });
    }

    public DefaultTableModel newModel() {
        DefaultTableModel model = new DefaultTableModel();
        table1.setModel(model);
        String[] columns = crud.getColumnName();
        for (int i = 0; i < columns.length; i++) {
            model.addColumn(columns[i]);
        }
        //model.addRow(new Object[]{"ID Product", "Name Product", "Description", "Price"});
        return model;
    }

    /*public Product createProductGUI(){
        String name = "", descript = "";
        double price = 0;
        if (Product.isString(textField1.getText(), 30)) {
            name = textField1.getText();
        }
        if (Product.isString(textField2.getText(), 50)) {
            descript = textField2.getText();
        }
        try {
            Double.parseDouble(textField3.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Price is not a number!!");
            return new Product();
        }
        price = Product.isDouble(textField3.getText());

        return new Product(name,descript,price);
    }*/
}
