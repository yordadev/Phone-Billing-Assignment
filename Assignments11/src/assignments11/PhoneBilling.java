/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Devitgg
 */
public class PhoneBilling {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) 
            throws FileNotFoundException, IOException {
        RateTable theRateTable;
        theRateTable              = new RateTable();
        File customerFileLocation = new File("documents/customers_vault.txt");
        Scanner   custFileSC      = new Scanner(customerFileLocation);
        String[]  theCustomer     = new String[4];
        String    theCustomerIs;
        double    total;

        //who is the customer?
        theCustomerIs = getCustomerFileName();

        //get the customer's information
        getCustomerInformation(custFileSC, theCustomerIs, theCustomer);

        //load the rate table
        theRateTable.loadRateTable();

        //display the report heading
        displayReportHeading(theCustomer);

        //process each call;
        total = processEachCall(custFileSC, theRateTable);

        //display final total
        displayTotal(total);
    }

    private static String getCustomerFileName() throws FileNotFoundException {
        File customerFileLocation = new File("documents/customers_vault.txt");
        Scanner     grabThis      = new Scanner(customerFileLocation);
        String[]    options       = new String[2];
        String      theCustomer;
        int         x,
                    y;

        //get all customer names for GUI selection
        if (grabThis.hasNext()) {
            for (x = 0; x < 2; x++) {
                options[x] = grabThis.nextLine(); //save the customers name
                for (y = 0; y < 7; y++) //throw out next 7 lines
                {
                    grabThis.nextLine();
                }
            }
        }

        //drop down selection, pick the customer
        theCustomer = (String) JOptionPane.showInputDialog(
                null,
                "The Memer Network",
                "Please choose a customer",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        return theCustomer;//return the customers name
    }

    private static void getCustomerInformation(Scanner custFileSC,
            String theCustomerIs, String[] theCustomer) {
        String  check;
        int     x;

        //find the customer and collect their data into "theCustomer"
        while (custFileSC.hasNext()) {
            check = custFileSC.nextLine();
            if (theCustomerIs.equals(check)) {
                theCustomer[0] = check;
                for (x = 1; x < 4; x++) {
                    theCustomer[x] = custFileSC.nextLine();
                }
                break;
            }
        }
    }

    private static void displayReportHeading(String[] theCustomer) {
        System.out.println("                                    "
                + "The Memer Network \n\n"
                + "     Customer Name:  " + theCustomer[0] + "\n"
                + "           Address:  " + theCustomer[1] + "\n"
                + "                     " + theCustomer[2] + "\n"
                + "       Phone Number: " + theCustomer[3]
                + "\n\n"
                + "Number Called        Descriptor        "
                + " Applicable Rate      Duration          "
                + "Exended Cost");
    }

    private static double processEachCall(Scanner custFileSC,
            RateTable theRateTable) throws IOException {
        Call    currentCall;
        currentCall = new Call();
        double  extendedCost = 0.00,
                callCost;
        int     x;

        for (x = 0; x < 2; x++) {
            currentCall.call(custFileSC);
            callCost = theRateTable.getExtendedCost(currentCall);
            extendedCost = updateTotal(callCost, extendedCost);
            displayCallSummary(currentCall, theRateTable, callCost);
        }
        return extendedCost;
    }

    private static double updateTotal(double callCost,
            double extendedCostTotal) {
        extendedCostTotal = extendedCostTotal + callCost; //keep running total
        return extendedCostTotal;
    }

    private static void displayCallSummary(Call currentCall, 
            RateTable theRateTable, double callCost) {
        DecimalFormat memer = new DecimalFormat("$#,###,##0.00");
        System.out.println(currentCall.getNumber() + "         " + 
           theRateTable.getDescriptor(currentCall.getAreaCode()) + 
                "               " + 
                 theRateTable.getRate(currentCall.getAreaCode()) + 
                "           " + 
                                       currentCall.getDuration() + 
                      "               " + memer.format(callCost));
    }

    private static void displayTotal(double total) {
        DecimalFormat memer = new DecimalFormat("$#,###,##0.00");
        System.out.println("\nFinal Total: " + memer.format(total));
    }
}
