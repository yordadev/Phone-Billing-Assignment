/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Devitgg
 */
class RateTable {

    private String[] descriptors,
            rates,
            areaCodes;
    Scanner grabThis;
    File areaCodeFile = new File("documents/areaCodes.txt");

    public void loadRateTable() throws FileNotFoundException {
        grabThis     = new Scanner(areaCodeFile);
        descriptors  = new String[324];
        rates        = new String[324];
        areaCodes    = new String[324];
        int x;

        while (grabThis.hasNext()) {
            for (x = 0; x < 324; x++) {
                areaCodes[x] = grabThis.nextLine();
                rates[x] = grabThis.nextLine();
                descriptors[x] = grabThis.nextLine();
            }
        }
    }

    public String getDescriptor(String callAreaCode) {
        int     x;
        String  theDescriptor,
                err = "error";

        for (x = 0; x < 324; x++) {
            if (callAreaCode.equals(areaCodes[x])) {
                return descriptors[x];
            }
        }
        return err;
    }

    public double getRate(String callAreaCode) {
        int x;
        String theDescriptor;
        double error = 1337;

        for (x = 0; x < 324; x++) {
            if (areaCodes[x].equals(callAreaCode)) {
                return Double.parseDouble(rates[x]);
            }
        }
        return error;
    }

    public double getExtendedCost(Call currentCall) {
        double cost,
                error = 0;;
        int x = 0;

        while (x < descriptors.length) {
            if (currentCall.getAreaCode().equals(areaCodes[x])) {
                cost = currentCall.getDuration() * Double.parseDouble(rates[x]);
                return cost;
            }
            x++;
        }
        return error;
    }

}
