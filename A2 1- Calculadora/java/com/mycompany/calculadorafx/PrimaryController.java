package com.mycompany.calculadorafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

    private float aux;
    private String operacao;
    
    @FXML
    private TextField displayTexto;
        
    @FXML
       void calcular() {
           switch(this.operacao){
               case "+":
                   aux = aux + Float.parseFloat(displayTexto.getText());
                   break;
                case "-":
                    aux = aux - Float.parseFloat(displayTexto.getText());
                   break;
                case "*":
                    aux = aux * Float.parseFloat(displayTexto.getText());
                   break;
                case "/":
                    aux = aux / Float.parseFloat(displayTexto.getText());
                   break;
            }
           //volta com o valor do aux em texto
           displayTexto.setText(String.valueOf(aux));
           this.operacao = "";
       }

       @FXML
       void limpa(ActionEvent event) {
           displayTexto.setText("0.0");
       }

       @FXML
       void limpaTudo(ActionEvent event) {
           displayTexto.setText("0.0");
           operacao = "";
           aux = 0;
       }

       @FXML
       void opr(ActionEvent event) {
           if(aux != 0){
               calcular();
           }else{
               aux = Float.parseFloat(displayTexto.getText());
           }
           
           this.operacao = ((Button) event.getSource()).getText();
       }

       @FXML
       void print(ActionEvent event) {
           String display = displayTexto.getText();
           
           if(display.isEmpty() || aux == Float.parseFloat(display)){
               display = "";
           }
           
           displayTexto.setText(display + ((Button)event.getSource()).getText());
           
           
       }
    }
