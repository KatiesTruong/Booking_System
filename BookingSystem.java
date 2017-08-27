/*
 * class BookingSystem
 * 
 * This code is a partially complete application for the booking system which 
 * implements a fully functional menu for CPT121 Assignment 3 in SP2 2017.
 * 
 * You are expected to work off this sample implementation of the BookingSystem
 * class - you *DO NOT* need to implement your own version of this program menu.
 * 
 * Note that the required program features as described in Stages 2 and 4 of the
 * Assignment 3 specification should be implemented in the corresponding helper 
 * methods further * down in the class.
 * 
 */

import java.util.Scanner;
import java.io.*;

public class BookingSystem
{
    // Declare the array of Tour references in which the Tour and
    // ScheduledTour objects that the user adds will be stored in and
    // the corresponding tour count.
    //
    // Note that this array and the corresponding counter will be
    // accessible both from within the main method as well as from
    // within any helper methods you may decide to implement.
    private static final Attraction[] attractionList = new Attraction[100];
    private static int attractionCount = 0;

    // Declaring a shared scanner just in case you choose to
    // implement some helper methods in your application class
    // that need access to one.

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws BookingException
    {
        // variables required to process user's menu selection
        String input;
        char selection = '\0';
        // keep repeating the menu until the user chooses to exit
        do
        {
            // display menu options
            System.out.println("******* Tour Booking System Menu *******");
            System.out.println("");
            System.out.println("A - Add New Attraction");
            System.out.println("B - Display Attraction Summary");
            System.out.println("C - Sell Passes");
            System.out.println("D - Refund Passes");
            System.out.println("E - Add New Scheduled Tour");
            System.out.println("F - Update Maximum Group Size");
            System.out.println("X - Exit Program");
            System.out.println();

            // prompt the user to enter their selection
            System.out.print("Enter your selection: ");
            input = sc.nextLine();

            System.out.println();

            // check to see if the user failed to enter exactly one character
            // for their menu selection

            if (input.length() != 1)
            {
                System.out.println("Error - selection must be a single character!");
            }
            else
            {
                // extract the user's menu selection as a char value and
                // convert it to upper case so that the menu becomes
                // case-insensitive

                selection = Character.toUpperCase(input.charAt(0));

                // process the user's selection
                switch (selection)
                {
                    case 'A':
                    case 'a':
                    // call addNewAttraction() helper method
                    addNewAttraction();
                    break;
                    case 'B':
                    case 'b':
                        // call displayAttractionSummary() helper method
                        displayAttractionSummary();
                        break;
                    case 'C':
                    case 'c':
                        // call sellPasses() helper method
                            sellPasses();
                        break;
                    case 'D':
                    case 'd':
                        // call refundPasses() helper method
                        refundPasses();
                        break;
                    case 'E':
                    case 'e':
                        // call addNewScheduledTour() helper method
                        addNewScheduledTour();
                        break;
                    case 'F':
                    case 'f':
                        // call updateMaxTourGroupSize() helper method
                        updateMaxGroupSize();
                        break;
                    case 'X':
                    case 'x':
                        System.out.println("Booking system shutting down...");
                        break;
                    default:
                        // default case - handles invalid selections
                        System.out.println("Error - invalid selection!");
                }
         }
         System.out.println();
      } while (selection != 'X');
   }

   /*
    * addNewAttraction()
    *
    * Adds new attraction with id and searches for attraction id
    * and if there are two same attraction ids, error message will
    * appear.
    */
   private static final void addNewAttraction()
   {
       // Implement your code for Stage 2 Requirement A) here
       // prompts user to add their ID, description and admission fee
       // when adding new Attraction
       String id, description;
       double admissionFee;
       Attraction a;

       System.out.println("Add New Attraction Feature Selected!");

       System.out.println("Enter new Attraction ID: ");
       id = sc.nextLine();

       // validate when duplicating ID of an existing one
       boolean idFound = false;

       // searches for matching attraction ID in attraction array
       for (int i = 0; i < attractionCount; i++) {
           if (attractionList[i].getAttractionID().equals(id)) {
               idFound = true;
           }
       }

       // rejects adding new attraction object when ID already exists
       if (idFound) {
           System.out.println("Error! Attraction ID " + id + " already exists.");
       }
       else {
           // allows continuation of attraction ID input if it isn't duplicate
           System.out.println("Enter Attraction Description: ");
           description = sc.nextLine();

           System.out.println("Enter Attraction Admission Fee: ");
           admissionFee = sc.nextDouble();

           //consumes end line
           sc.nextLine();
           // creates new attraction object
           a = new Attraction(id, description, admissionFee);
           // when creating new attraction object, array shifts to the next space
           attractionList[attractionCount] = a;
           attractionCount++;
       }
   }

   /*
    * displayAttractionSummary()
    *
    * Searches for current attraction object that was created
    * by user when creating attraction id and prints its
    * details that is called from Attraction class onto program.
    *
    */
   private static final void displayAttractionSummary()
   {
      // Implement your code for Stage 2 Requirement B) here
      //
      System.out.println("Display Attraction Summary Feature Selected!");
      for (int i = 0; i < attractionCount; i++) {
          attractionList[i].printDetails();
          System.out.println();
      }
   }

   /*
    * sellPasses()
    *
    * Requests user to enter attraction id, searches for its object
    * and then its id in order to input information for
    * sell passes. Then, user is requested to enter a number of
    * tourists. If user doesn't enter the correct number an error message
    * should appear. If not, then the program sends a message that it was sold
    * and the booking cost.
    */

   private static final void sellPasses() throws BookingException
   {

      // Implement your code for Stage 2 Requirement C) here
      // ...
      System.out.println("Sell Passes Feature Selected!");
      String id;
      int numTourists;

      System.out.println("Enter Attraction ID for sell passes: ");
      id = sc.nextLine();

      Attraction a = null;
      // searches for ID in attractionList array
      for (int i = 0; i < attractionCount; i++) {
          if (attractionList[i].getAttractionID().equals(id)) {
              a = attractionList[i];
          }
      }
      // input validation whether ID match was found or not
      if (a == null) {
          System.out.println("Attraction ID " + id + " not found.");
      }
      else {
          System.out.println("Enter number of tourists: ");
          numTourists = Integer.parseInt(sc.nextLine());

          // searches for sale of passes for number of tourists
          // invokes method via Attraction reference that was declared via Attraction
          double result = a.sellPasses(numTourists);
          if (result == Double.NaN) {
              System.out.println("Error! Passes were unsuccessfully sold.");
          }
          else {
              System.out.println("Sold successfully!");
              System.out.println("Booking cost: " + a.sellPasses(numTourists));
          }
      }
   }

   /*
    * refundPasses()
    * Requests user to enter attraction id and attempts to search its object id
    * in the array. If the id is not found (equals to null) it will show an error
    * that it was not found. If it was, it continues to request the user to enter a
    * number of tourists to refund. If none was entered an error will appear, but
    * if it did, a message will show that cancellation/ refund was successful and shows
    * the amound refunded.
    */
   private static final void refundPasses()
   {
      // Implement your code for Stage 2 Requirement D) here
      // ...
      System.out.println("Refund Passes Feature Selected!");
      String id;
      int numTourists;
      Attraction a;
      // Requests user to enter Attraction ID to search its object
      System.out.println("Enter Attraction ID: ");
      id = sc.nextLine();

      a = null;
      for (int i = 0; i < attractionCount; i++) {
          if (attractionList[i].getAttractionID().equals(id)) {
              a = attractionList[i];
          }
      }
      if (a == null) {
          System.out.println("Attraction ID " + id + " not found.");
      }
      else {
          System.out.println("Enter number of Tourists for refund: ");
          numTourists = Integer.parseInt(sc.nextLine());

          // invoke method via Attraction class and giving validation
          double result = a.refundPasses(numTourists);
          if (result == Double.NaN) {
              System.out.println("Error! Passes were unsuccessfully sold.");
          }
          else {
              System.out.println("Cancellation successful!");
              System.out.println("Amount refunded: " + a.refundPasses(numTourists));
          }
      }
   }
   /*
    * addNewScheduledTour()
    *
    * Requests the user to enter a new tour id, description, admin fee, tour date,
    * maximum group size and tour guide. It creates a new ScheduledTour object in the same array of
    * attractionList and searches whether the id is the same as a previous is, and shows an error
    * when tour id is the same as attraction id.
    */

   private static void addNewScheduledTour()
   {
      // Implement your code for Stage 4 Requirement A) here
      // Prompts user to input details for tour features
      System.out.println("Add New Scheduled Tour Feature Selected!");
      String tourID, tourDescription, tourDate, tourGuide;
      double tourAdmissionFee;
      int maxGroupSize;
      Attraction a;

      System.out.println("Enter new Tour ID: ");
      tourID = sc.nextLine();

      System.out.println("Enter Tour Description: ");
      tourDescription = sc.next();

      System.out.println("Enter Tour admission fee: ");
      tourAdmissionFee = sc.nextDouble();

      // consumes next line
      sc.nextLine();

      System.out.println("Enter Tour Date: ");
      tourDate = sc.nextLine();

      System.out.println("Enter Maximum Group Size: ");
      maxGroupSize = sc.nextInt();

      // consumes line
      sc.nextLine();

      System.out.println("Enter Tour Guide: ");
      tourGuide = sc.nextLine();

      // creates new ScheduledTour objects and adds the next empty spot
      // of array
      a = new ScheduledTour(tourID,
              tourDescription, tourAdmissionFee, tourDate, maxGroupSize, tourGuide);
      attractionList[attractionCount] = a;
      attractionCount++;

      // searches within array to check objects between Attraction ids
      boolean idFound = false;
      for (int i = 0; i < attractionCount && !idFound; i++) {
          if (attractionList[i].getAttractionID().equals(tourID)) {
              idFound = true;
          }
      }
      //error message when same attraction id exists
      if (idFound) {
          System.out.println("Error! Attraction ID " + tourID + " already exists. Try again.");
      }
   }

   /*
    * updateMaxTourGroupSize()
    *
    * Requests user to enter the attraction id and checks whether it is the same as
    * another attraction id. If it is, then an error message shows, and if the
    * attraction id does not exist, it shows an error message as well. If it is found,
    * then the program requests the user to enter a new maximum group size, and when
    * successful, the program sends a successful message.
    */
   private static void updateMaxGroupSize()
   {
      // Implement your code for Stage 4 Requirement B) here
      // ...
      System.out.println("Update Maximum Group Size Feature Selected!");
      Attraction a;
      ScheduledTour s = null;

      System.out.println("Enter Attraction ID: ");
      String id = sc.nextLine();

      //checks for tour ID matching
      boolean idFound = false;
      for (int i = 0; i < attractionCount; i++) {
          if (attractionList[i].getAttractionID().equals(id)) {
              idFound = true;
          }
      }
      // when attraction ID does not exist prints on screen
      if (!idFound) {
          System.out.println("Attraction ID " + id + " not found.");
      }
      else {
          if (s == null) {
              System.out.println("Enter new maximum size group: ");
              int maxSize = Integer.parseInt(sc.nextLine());

              // invokes setMaxGroupSize and typecasts maxSize within
              s.setMaxGroupSize(maxSize);
              System.out.println("Group size change successful!");
          }
      }
   }
}
