/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterwise;

/**
 *
 * @author Jelle
 */
public class Error {
    
    
    private String errorInput;
    private String errorCategory;
    private String inputFix;
    private String correctExample;
    private String stringFix;
    
    public Error(String error, String category)  {
        ErrorFrame e;
        this.errorInput = error;
        this.errorCategory = category;
        switch (category) {
            case "Vægt"        :this.inputFix       = "heltal eller kommatal";
                                this.correctExample = "2 eller 2.5";
                                
                break;
            case "Pris"        :this.inputFix       = "heltal eller kommatal";
                                this.correctExample = "2 eller 2.5";
                break;                    
            case "ID"          :this.inputFix       = "heltal";
                                this.correctExample = "2";
                break;
            case "Antal"       :this.inputFix       = "heltal";
                                this.correctExample = "2";
                break;
            case "Genbestil"   :this.inputFix       = "heltal";
                                this.correctExample = "2";
                break;
            case "Navn"        :this.inputFix       = "Der er ikke indtastet noget navn";
                                this.correctExample = "Navnefeltet må ikke være tomt";
                break;
            case "Størrelse"   :this.inputFix       = "Der er ikke valgt nogen størrelse";
                                this.correctExample = "Der skal være valgt størrelse";
                break;
        }
        e = new ErrorFrame(error, category, inputFix, correctExample);
    }
    
    
    public String getErrorInput() {
        return errorInput;
    }

    public void setErrorInput(String errorInput) {
        this.errorInput = errorInput;
    }

    public String getErrorCategory() {
        return errorCategory;
    }

    public void setErrorCategory(String errorCategory) {
        this.errorCategory = errorCategory;
    }

    public String getInputFix() {
        return inputFix;
    }

    public void setInputFix(String inputFix) {
        this.inputFix = inputFix;
    }

    public String getCorrectExample() {
        return correctExample;
    }

    public void setCorrectExample(String correctExample) {
        this.correctExample = correctExample;
    }
    
    
}
