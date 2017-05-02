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
