/*
 MIT License

Copyright (c) 2017 Devitgg

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
