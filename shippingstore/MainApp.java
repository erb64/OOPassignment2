package shippingstore;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class of the ShippingStore database manager. It provides a
 * console for a user to use the 5 main commands.
 *
 * @author Junye Wen
 */
public class MainApp {

    /**
     * This method will begin the user interface console. Main uses a loop to
     * continue doing commands until the user types '6'. A lot of user input
     * validation is done in the loop. At least enough to allow the interface
     * with ShippingStore to be safe.
     *
     * @param args this program expects no command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        PackageDatabase packages = new PackageDatabase();
        UserDatabase users = new UserDatabase();

        String welcomeMessage = "\nWelcome to the Shipping Store. Choose one of the following functions:\n\n"
                + "\t1. Show all existing package records in the database\n"
                + "\t2. Add a new package order to the database\n"
                + "\t3. Delete a package order from the database\n"
                + "\t4. Search for a package order (given its Tracking #)\n"
                + "\t5. Show a list of users in the database\n"
                + "\t6. Add new user to the database\n"
                + "\t7. Update user info (given their id)\n"
                + "\t8. Complete a shipping transation\n"
                + "\t9. Show completed shipping transations\n"
                + "\t10. Exit program.\n";
        String packageMessage = "\nPlease type description of package with the following pattern:\n"
                            + "\n TRACKING# SPECIFICATION CLASS ";

        System.out.println(welcomeMessage);

        String selection = in.nextLine();

        while (!selection.equals("10")) {

            switch (selection) {
                case "1":
                    packages.showPackageOrders();
                    break;
                case "2":
                    System.out.println("Please enter the type of package you wish to add: (Envelope, Box, Crate, or Drum)");
                    String type = in.nextLine();
                    String inTemp, temp[];
                    switch(type){
                        case "Envelope":
                            System.out.print(packageMessage + "HEIGHT WIDTH\n"
                                + "example:\nGFR23 Books Retail 62 45\n");
                            inTemp = in.nextLine();
                            temp = inTemp.split(" ");

                            if (temp.length != 5) {
                                System.out.println("Not correct number of fields to process.");
                                break;
                            }
                            packageDatabase.addOrder(temp[0], type, temp[1], temp[2], temp[3], temp[4]);
                            break;
                        case "Box":
                            System.out.print(packageMessage + "LARGEST-DIMENSION VOLUME\n"
                                + "example:\nAB123 Fragile First-Class 30 2500\n");
                            inTemp = in.nextLine();
                            temp = inTemp.split(" ");

                            if (temp.length != 5) {
                                System.out.println("Not correct number of fields to process.");
                                break;
                            }
                            packageDatabase.addOrder(temp[0], type, temp[1], temp[2], temp[3], temp[4]);
                            break;
                        case "Crate":
                            System.out.print(packageMessage + "MAX-LOAD-WEIGHT CONTENT\n"
                                + "example:\nZ1Y9X N/A First-Class 50.46 Leprechauns\n");
                            inTemp = in.nextLine();
                            temp = inTemp.split(" ");

                            if (temp.length != 5) {
                                System.out.println("Not correct number of fields to process.");
                                break;
                            }
                            packageDatabase.addOrder(temp[0], type, temp[1], temp[2], temp[3], temp[4]);
                            break;
                        case "Drum":
                            System.out.print(packageMessage + "MATERIAL DIAMETER\n"
                                + "example:\n8U4NW Do-not-Bend Priority Plastic 35\n");
                            inTemp = in.nextLine();
                            temp = inTemp.split(" ");

                            if (temp.length != 5) {
                                System.out.println("Not correct number of fields to process.");
                                break;
                            }
                            packageDatabase.addOrder(temp[0], type, temp[1], temp[2], temp[3], temp[4]);
                            break;
                        default:
                            System.out.println("That is not a valid type. Returning to main menu.");
                    }
                    break;
                case "3":
                    packages.showPackageOrders();

                    System.out.println("\nPlease enter the tracking # of the package order to delete from the database.\n");
                    String orderToDelete = in.nextLine();
                    packages.removeOrder(orderToDelete);
                    break;
                case "4":
                    System.out.println("\nEnter the Tracking # of the order you wish to see.\n");
                    String trackingNum = in.nextline();

                    shippingstore.searchPackageOrder(trackingNum);
                    break;
                case "5":
                    //show users
                    break;
                case "6":
                    //add user
                case "7":
                    //update user
                case "8":
                    //complete a transation
                case "9":
                    //show completed transactions
                case "10":
                case "help":
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or \"help\" to list the commands.");
                    break;

            }//endof switch

            System.out.println("Please enter another command or 'h' to list the commands.\n");
            selection = in.nextLine();

            in.nextLine();
        }//endof while

        in.close();
        shippingstore.flush();
        
        System.out.println("Done!");

    }
}
