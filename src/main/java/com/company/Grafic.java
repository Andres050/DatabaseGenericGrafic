package com.company;

import com.company.AccesoDatos.CRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


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
    private JButton ChangeConnectionButton;
    private JLabel ConnectionLabel;
    private JTextField serverAddressField;
    private JButton SHOWDATABASESButton;
    private JButton SHOWTABLESButton;

    private CRUD crud = new CRUD();
    private final Business business = new Business(crud);
    private JLabel jLabel = new JLabel();

    public Grafic(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1000, 400);
        this.pack();


        ConnectionLabel.setText("CONNECTION: "+crud.login+" , "+crud.password+" , "+crud.serverAddress);
        DAtaBaseNameTop.setText("DATABASE: "+ crud.database);
        tableNameTop.setText("Table: "+ crud.table);
        TAbleNameTop.setText("TABLE: "+crud.table);
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
                String prove = primaryREAD.getText();
                if (prove.equals(" ") || prove.equals("") ||prove == null) {
                    JOptionPane.showMessageDialog(null, "You dont write a ID on primaryKey READ!!");
                } else {
                    business.search(primaryREAD, newModel());
                    JOptionPane.showMessageDialog(null, "Operation finished!!!");
                }
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String prove = primaryUPDATE.getText();
                if (prove.equals(" ") || prove.equals("") ||prove == null) {
                    JOptionPane.showMessageDialog(null, "You dont write a ID on primaryKey UPDATE!!");
                } else {
                    if (isThere(prove)) {
                        Insert insert = new Insert("UPDATE", business, 1);
                        JOptionPane.showMessageDialog(null, "Operation finished!!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "ID selected dont find!!!");
                    }
                }
            }
        });


        //NO TERMINADO DESDE AQUI
        DELETEwithPrimaryKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String prove = primaryDELETE.getText();
                if (prove.equals(" ") || prove.equals("") ||prove == null) {
                    JOptionPane.showMessageDialog(null, "You dont write a ID on primaryKey DELETE!!");
                } else {
                    business.deleteID(primaryDELETE);
                    JOptionPane.showMessageDialog(null, "Operation finished!!!");                }
            }
        });
        DELETEALLTABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int confirmado = JOptionPane.showConfirmDialog(
                        null,
                        "¿Confirmas que lo quieres eliminar?");
                if (JOptionPane.OK_OPTION == confirmado) {
                    business.restartCataleg();
                }
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        GETDATABASEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String prove = databaseField.getText();
                if (prove.equals("") || prove.equals(" ") || prove == null) {
                    JOptionPane.showMessageDialog(null, "You dont write a Database!!");
                } else {
                    String saveDatabase = crud.database;
                    crud.database = databaseField.getText();
                    String work = crud.databaseExist();
                    if (work.equals("Ok")) {
                        JOptionPane.showMessageDialog(null, "Connection its doing fine!!!");
                        String text = crud.showTables();
                        String[] separate = text.replace("|"," ").split(" ");
                        crud.table = separate[0];
                        TAbleNameTop.setText("TABLE: "+crud.table);
                        tableNameTop.setText("Table: "+crud.table);
                    } else {
                        JOptionPane.showMessageDialog(null, work);
                        crud.database = saveDatabase;
                    }
                }
                DAtaBaseNameTop.setText("DATABASE: "+ crud.database);
            }
        });
        GETTABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String prove = textFieldTable.getText();
                if (prove.equals("") || prove.equals(" ") || prove == null) {
                    JOptionPane.showMessageDialog(null, "You dont write a table!!");
                } else {
                    String saveTable = crud.table;
                    crud.table = textFieldTable.getText();
                    String work = crud.tableExist();
                    if (work.equals("Ok")) {
                        JOptionPane.showMessageDialog(null, "Connection its doing fine!!");
                    } else {
                        JOptionPane.showMessageDialog(null, work);
                        crud.table = saveTable;
                    }
                }
                tableNameTop.setText("Table: "+ crud.table);
                TAbleNameTop.setText("TABLE: "+crud.table);
            }
        });
        ChangeConnectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginSave = crud.login;
                String passwordSave = crud.password;
                String addressSave = crud.serverAddress;
                String databaseSave = crud.database;
                String password = ""; String UserName = ""; String address = ""; String database = "";
                password = textFieldPassword.getText();
                UserName = textFieldUserName.getText();
                address = serverAddressField.getText();
                database = databaseField.getText();
                if ((password != null || !password.equals("")) && (!UserName.equals("") && UserName != null) && (address!=null || !address.equals("") || !address.equals(" "))) {
                    JOptionPane.showMessageDialog(null, "You write a complete connection!!");
                    try {
                        crud.login = textFieldUserName.getText();
                        crud.password = textFieldPassword.getText();
                        crud.serverAddress = serverAddressField.getText();
                        if (database.equals("") ||database.equals(" ") ||database == null) {
                            JOptionPane.showMessageDialog(null, "DataBase not selected!!!");
                            crud.database = crud.showDatabase().replace("|"," ").split(" ")[0];
                            JOptionPane.showMessageDialog(null, "DATABASE Selected: "+crud.database);
                            DAtaBaseNameTop.setText("DATABASE: "+crud.database);
                            crud.table = crud.showTables().replace("|"," ").split(" ")[0];
                            TAbleNameTop.setText("TABLE: "+crud.table);
                            tableNameTop.setText("Table: "+crud.table);
                            JOptionPane.showMessageDialog(null, "TABLE Selected: "+crud.table);
                        } else {
                            crud.database = databaseField.getText();
                            Connection connection = crud.connection();
                            connection.close();
                            JOptionPane.showMessageDialog(null, "Connection its doing fine!!");
                            ConnectionLabel.setText("CONNECTION: " + textFieldUserName.getText() + "," + textFieldPassword.getText() + " , " + serverAddressField.getText());
                            DAtaBaseNameTop.setText("DATABASE: "+databaseField.getText());
                            JOptionPane.showMessageDialog(null, "Table changed!!");
                            crud.table = crud.showTables().replace("|", " ").split(" ")[0];
                            TAbleNameTop.setText("TABLE: "+crud.table);
                            tableNameTop.setText("Table: "+crud.table);
                        }
                    } catch (SQLException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                        crud.login = loginSave;
                        crud.password = passwordSave;
                        crud.serverAddress = addressSave;
                        ConnectionLabel.setText("CONNECTION: "+loginSave+","+passwordSave+","+addressSave);
                        DAtaBaseNameTop.setText("DATABASE: "+databaseSave);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You dont write a complete connection!!");
                }
            }
        });
        SHOWDATABASESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business.showDatabase(table1);
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
        SHOWTABLESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business.showTables(table1);
                JOptionPane.showMessageDialog(null, "Operation finished!!!");
            }
        });
    }

    public boolean isThere(String prove) {
        String[] text = crud.readAll();
        for (int i = 0; i < text.length; i++) {
            String[] separete = text[i].replace(",","|").split("|");
            if (separete[0].equals(prove)) {
                return true;
            }
        }
        return false;
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
}
