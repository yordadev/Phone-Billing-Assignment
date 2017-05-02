/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments11;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Devitgg
 */
class Call {

    public String numberCalled;
    public double duration;
    //private String areaCode -> why isnt this defined here and defined locally?

    public void call(Scanner custFileSC) throws IOException {
        if (custFileSC.hasNext()) {
            duration     = Double.parseDouble(custFileSC.nextLine());
            numberCalled = custFileSC.nextLine();
        }
    }

    public String getNumber() {
        return numberCalled;
    }

    public double getDuration() {
        return duration;
    }

    public String getAreaCode() {
        String   areaCode;
        String[] figureThisOut;
        
        //split the number by removing the - and making an array of 3 with 
        //0 with first 3, 1 with middle 3 and 2 with last 4.
        figureThisOut = numberCalled.split("-");
        areaCode = figureThisOut[0]; //get the AreaCode

        return areaCode;
    }

}
