/**
 * @author Enes Duman
 * @studentID 2023719042
 * @date 27.10.2023
 *
 * Gets a third degree of a polynomial, min and maximum intervals and a DeltaX from user
 * to calculate the integral of the polynomial.
 *
 */


import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) {
        /**
         * Polynomial coefficients.
         * ax^3 + bx^2 + cx + d
         */
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH); //to allow "." decimal point
        System.out.println("Enter function: ");
        String userFunction = scanner.nextLine();

        System.out.println("Minimum range: ");
        Double xmin = scanner.nextDouble();

        System.out.println("Maximum range: ");
        Double xmax = scanner.nextDouble();

        System.out.println("Delta x: ");
        Double delta = scanner.nextDouble();

        System.out.println("Function: " + userFunction +
                           "\nMinimum: " + xmin +
                           "\nMaximum: " + xmax +
                           "\nDelta: " + delta);

        /**
         * String cleaning:
         * Delete all whitespace,
         * Since Java can understand a signless number as positive, split strings from "+" signs.
         * To count negative numbers, add "+" sign in front of them so split() can see it.
         */
        userFunction = userFunction.replaceAll("\\s",""); //delete all spaces and tabs
        userFunction = userFunction.replaceAll("\\-", "+-"); //add + sign to - signs so they we can split at below code
        String[] polynom = userFunction.split("\\+"); //split string with +

        System.out.println(Arrays.toString(polynom));

        /**
         * For each string in the array, check their degrees and update coefficient variables.
         * To do this, delete x to the power of [number], parse the remaining string as double.
         * If the result of deletion is an empty string, increase the coefficient by 1. (e.g. "x^2")
         */
        for(String s : polynom) {
            if(s.contains("x") == false) {
                d += Double.parseDouble(s);

            }

            if(s.contains("^3")) {
                s = s.replaceAll("x\\^3", "");
                if(s == ""){
                    a += 1;
                } else if (s.equals("-")) {
                    a += -1;
                }else {
                    a += Double.parseDouble(s);
                }
            }

            if(s.contains("^2")) {
                s = s.replaceAll("x\\^2", "");
                if(s == ""){
                    b += 1;
                } else if (s.equals("-")) {
                    b += -1;
                }else {
                    b += Double.parseDouble(s);
                }
            }

            if(s.contains("^3") == false && s.contains("^2") == false && s.contains("x")) {
                s = s.replaceAll("x", "");
                if(s == ""){
                    c += 1;
                } else if (s.equals("-")) {
                    c += -1;
                }else {
                    c += Double.parseDouble(s);
                }
            }

            }

            System.out.println("a: " + a +
                               "\nb: " + b +
                               "\nc: " + c +
                               "\nd: " + d);

        /**
         * Left hand Riemann sum integral calculation.
         * From minimum X point until maximum X point, insert squares that are Delta X wide and f(i) height,
         * calculate each square's area.
         * So DeltaX * [f(xmin) + f(xmin+1) +...+ f(xmax)]
         * for-loop calculates the latter part and then multiply the result with deltaX at the end.
         * For more info about the Riemann sum method: https://www.storyofmathematics.com/riemann-sum/
         */
        double result = 0;
        for(double i = xmin; i <= xmax; i += delta) {
              result += (a * Math.pow(i, 3)) + (b * Math.pow(i, 2)) + (c * i) + d;
        }
        result = result * delta;

        System.out.println("RESULT: " + result);

        }


    }

