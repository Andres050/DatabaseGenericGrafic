package com.company;

import com.company.AccesoDatos.CRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class Business {
    private CRUD crudProduct;

    public Business(CRUD crud){
        this.crudProduct = crud;
    }

    public void add(JTextField[] jTextField){
        String primaryKey = jTextField[0].getText();
        if (!primaryKey.equals("")) {
            String[] object = new String[jTextField.length];
            for (int i = 0; i < jTextField.length; i++) {
                object[i] = jTextField[i].getText();
            }
            boolean isIn = false;
            String[] all = crudProduct.readAll();
            for (int i = 0; i < all.length; i++) {
                String[] split = all[i].replace(",", "|").split("|");
                if (object[0].equals(split[0])) {
                    isIn = true;
                }
            }
            if (!isIn) {
                crudProduct.insert(object);
            } else {
                JOptionPane.showMessageDialog(null, "The primaryKey selected to add exist!!!");
            }
        } else {
            jTextField[0].getText();
            JOptionPane.showMessageDialog(null,"You dont selected the primaryKey!!!");
        }
    }

    public void list(DefaultTableModel model){
        String[] productList = crudProduct.readAll();
        String[] separate;
        for (int i = 0;i < productList.length; i++) {
            Vector row = new Vector();
            separate = productList[i].replace("'", "").split(",");
            for (int x = 0; x < separate.length; x++) {
                row.add(separate[x]);
            }
            model.addRow(row);
        }
    }

    public void restartCataleg() {
        crudProduct.deleteAllINFOonTABLE();
    }

    public void search(JTextField textField,DefaultTableModel model){
        int id = 0;
        try {
            id = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit!!");
            return;
        }
        String[] objects = crudProduct.readAll();
        for (int i = 0; i < objects.length; i++) {
            String[] separate = objects[i].replace("'", "").split(",");
            if (Integer.parseInt(separate[0])==id) {
                Vector row = new Vector();
                for (int x = 0; x < separate.length; x++) {
                    row.add(separate[x]);
                }
                model.addRow(row);
            }
        }
    }

    public void update(JTextField textField, DefaultTableModel model, JTextField[] textFields){
        int id = 0;
        try {
            id = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit!!");
            return;
        }
        String[] object = new String[textFields.length];
        for (int i = 0; i < textFields.length; i++) {
            object[i] = textFields[i].getText();
        }
        crudProduct.updateById(object, id);
    }

    public void deleteID (JTextField textField){
        int id = 0;
        try {
            id = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The ID selected doesnt exit!!");
            return;
        }
        crudProduct.deleteById(id);
    }

    public CRUD getCrudProduct() {
        return crudProduct;
    }

    public void showTables(JTable table) {
        String text = crudProduct.showTables();
        String[] separate = text.replace("|", " ").split(" ");
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Tables options");
        for (int x = 0; x < separate.length; x++) {
            Vector row = new Vector();
            row.add(separate[x]);
            model.addRow(row);
        }
    }
    public void showDatabase(JTable table){
        String text = crudProduct.showDatabase();
        String[] separate = text.replace("|"," ").split(" ");;
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Database options");
        for (int i = 0; i < separate.length; i++) {
            Vector row = new Vector();
            row.add(separate[i]);
            model.addRow(row);
        }
    }
}
