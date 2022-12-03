package prog;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

import cart.Item;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to your shopping cart: ");

        Console cons = System.console();

        List<Item> shoppingCart = new LinkedList<Item>();

        float total = 0;

        String input = "";

        while (!input.equals("stop")) {
            input = cons.readLine(">");
            input = input.trim().toLowerCase();
            String[] tempArray = input.split(" ");

            switch (tempArray[0]) {
                case "list":
                    if (shoppingCart.size() == 0) {
                        System.out.println("There are no items in your shopping cart.\n");
                    } else {
                        for (int i = 0; i < shoppingCart.size(); i++) {
                            System.out.printf("%02d. %s     %d pcs       $%.2f\n", i + 1, shoppingCart.get(i).getName(),
                                    shoppingCart.get(i).getQuantity(), shoppingCart.get(i).getPrice());
                        }
                    }
                    break;
                case "add":
                    try {
                        float price = Float.parseFloat(tempArray[3]);
                        int quantity = Integer.parseInt(tempArray[2]);
                        Item tempItem = new Item(tempArray[1], price, quantity);
                        int count = 0;
                        if (shoppingCart.size() == 0) {
                            shoppingCart.add(tempItem);
                            System.out.printf("%s added to cart\n", tempArray[1]);
                        } else {
                            for (int i = 0; i < shoppingCart.size(); i++) {
                                if ((shoppingCart.get(i).getName().toLowerCase().equals(tempArray[1]))) {
                                    System.out.printf("You already have %s in your cart\n", tempArray[1]);
                                    String average = cons.readLine("Average your %s prices? (y/n)", tempArray[1]);
                                    while ((!average.trim().toLowerCase().equals("y"))
                                            && (!average.trim().toLowerCase().equals("n"))) {
                                        average = cons.readLine("y/n only");
                                    }
                                    if (average.toLowerCase().trim().equals("y")) {
                                        int tmpQty = shoppingCart.get(i).getQuantity();
                                        shoppingCart.get(i).setQuantity(tmpQty + quantity);
                                        float tmpPrice = shoppingCart.get(i).getPrice();
                                        float subTotal = tmpPrice * tmpQty + price * quantity;
                                        float avePrice = subTotal / (tmpQty + quantity);
                                        shoppingCart.get(i).setPrice(avePrice);
                                    } else {
                                        break;
                                    }

                                } else {
                                    count++;
                                    continue;
                                }
                            }
                            if (count == shoppingCart.size()) {
                                shoppingCart.add(tempItem);
                                System.out.printf("%s added to cart\n", tempArray[1]);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter numbers only for <quantity> and <price>\n");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please enter the correct format \"add <item> <quantity> <price>\"");
                    }
                    break;
                case "delete":
                    try {
                        int idx = Integer.parseInt(tempArray[1]);
                        if ((idx < 0) || (idx > shoppingCart.size())) {
                            System.out.println("You have entered an invalid number\n");
                        } else {
                            System.out.printf("%s(s) has/have been removed from cart\n", shoppingCart.get(idx - 1));
                            shoppingCart.remove(idx - 1);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter numbers only after \"delete\"\n");
                    }
                    break;
                case "checkout":
                    for (int i = 0; i < shoppingCart.size(); i++) {
                        total += shoppingCart.get(i).getQuantity() * shoppingCart.get(i).getPrice();
                    }
                    System.out.printf("Total amount is $%.2f", total);
                    input = "stop";
                    break;
                case "stop":
                    System.out.println("You have exited the cart");
                    break;
                default:
                    System.out.println("""
                            Wrong command entered.
                            Please enter:
                            add <item> <quantity> <quantity> to add items
                            list to list all items
                            delete <item number> to delete item
                            checkout to calculate the total price
                            stop to stop the program\n
                            """);
            }

        }

    }

}
