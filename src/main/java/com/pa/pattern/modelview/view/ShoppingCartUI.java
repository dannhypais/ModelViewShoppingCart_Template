/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.pattern.modelview.view;



import com.pa.pattern.modelview.model.Product;
import com.pa.pattern.modelview.model.ShoppingCart;
import com.pa.pattern.modelview.model.ShoppingCartException;
import com.pa.pattern.observer.Observer;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Collection;


/**
 *
 * @author patriciamacedo
 */
public class ShoppingCartUI extends VBox {

    //controlos
    private TextField txtInputId;
    private Button btAddProduct;
    private ListView<Product> listProductsView;
    private Label lblError;
    private Label lblCount;

    //modelo
    private final ShoppingCart model;
    
    public ShoppingCartUI(ShoppingCart model) {
        this.model = model;
        initComponents();
        setTriggers();
    }
    

    private void initComponents() {
        
        this.txtInputId = new TextField();
        this.btAddProduct = new Button("Add");
        this.listProductsView = new ListView<>();
        lblError = new Label();
        
        lblCount = new Label("0");
        
        HBox firstRow = new HBox(txtInputId,btAddProduct, new Label("Total Value"),lblCount);
        firstRow.setAlignment(Pos.CENTER);
        firstRow.setPadding(new Insets(2,2,2,2));
        firstRow.setSpacing(4);

        this.getChildren().addAll(firstRow, listProductsView, lblError);
    }

    private void setTriggers() {
        btAddProduct.setOnAction((ActionEvent event) -> {
            doAddProduct();
        });

    }

    private void doAddProduct() {
        String id = getInputProductId();

        try {
            model.addProduct(Integer.parseInt(id));
            clearInput();
        } catch (ShoppingCartException e) {
            showError(e.getMessage());
        }
        catch (NumberFormatException e) {
            showError("it is not a number");
        }
    }



    private void showError(String msg) {
        lblError.setText(msg);
    }



    private String getInputProductId() {
        return txtInputId.getText().trim();
    }

    private void clearInput() {
        txtInputId.setText("");
    }

   
    
}
