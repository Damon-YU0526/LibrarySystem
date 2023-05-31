import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The LibraryStart class is the startup class, which is the entry point of the whole program
 */
public class LibraryStart {

  private Library library;

  private Scanner scanner;

  public LibraryStart() {
    displayInfo();
    library = new Library();
    library.readItemsFromFile("library.txt");
    scanner = new Scanner(System.in);
  }

  //display info for the course and my information
  public void displayInfo() {
    System.out.println("--------------------------------------------\n"
        + "Assignment 2 Semester 1 2023\n"
        + "Submitted by: HAOWU YU 23012098\n"
        + "--------------------------------------------");
  }

  //display all items in library
  public void displayAllItems() {
    System.out.println("List of all items in the library:");
    for (Item item : library.getAllItems()) {
      System.out.println(item.getBriefDescription());
    }
  }

  //display menu
  public void disPlayMenu() {
    System.out.println("\nEnter 'q' to quit\n"
        + "or enter 's' to sort (first by average rating and then by ID) and display all items\n"
        + "or enter 'i' to search by ID\n"
        + "or enter any other key to search by phrase in title");
  }

  //user choose method
  public void run() {
    displayAllItems();
    do {
      disPlayMenu();
      String input = scanner.nextLine();
      if (input.equals("q")) {
        System.exit(0);
      } else if (input.equals("s")) {
        Collections.sort(library.getAllItems());
        displaySortedItems();
      } else if (input.equals("i")) {
        searchItemById();
      } else {
        searchItemByTitle();
      }
    } while (true);
  }

  //search item by title
  private void searchItemByTitle() {
    while (true) {
      System.out.println(
          "Enter phrase in title to start search, or 'b' to go back to choose search method");
      String input = scanner.nextLine();
      if (input.equals("b")) {
        return;
      }
      List<Item> items = library.searchItemByTitle(input);
      if (items.isEmpty()) {
        System.out.println("The title without item contains the input text");
        return;
      }
      for (int i = 0; i < items.size(); i++) {
        System.out.println("* " + (i + 1) + ":");
        System.out.println(items.get(i).getBriefDescription());
      }
      System.out.println(
          "\nEnter item number to select item, or enter any other key to continue searching");
      String key = scanner.nextLine();
      if (isNumber(key)) {
        int index = Integer.parseInt(key);
        if (index >= 1 && index <= items.size()) {
          Item item = items.get(index - 1);
          boolean restart;
          if (item.isStatus()) {
            restart = printAvailableItem(item);
          } else {
            restart = printOnLoanItem(item);
          }
          if (restart) {
            return;
          }
        } else {
          System.out.println("Input number should be in [1 - " + items.size() + "]");
        }
      }
    }
  }

  //search item by id
  private void searchItemById() {
    //back to previous menu
    while (true) {
      System.out.println("Enter ID to start search, or 'b' to go back to choose search method");
      String input = scanner.nextLine();
      if (input.equals("b")) {
        break;
      } else {
        if (!isNumber(input)) {
          System.out.println("Please input valid ID, ID is number");
          continue;
        }
        Item item = library.searchItemById(Integer.parseInt(input));
        if (item == null) {
          System.out.println("ID is not exist in this library");
          continue;
        }
        System.out.println(item.getBriefDescription());
        System.out.println(
            "\nEnter 'i' to search other item by ID, or enter any other key to select this item");
        String choose = scanner.nextLine();
        if (choose.equals("i")) {
          continue;
        }
        boolean restart;
        if (item.isStatus()) {
          restart = printAvailableItem(item);
        } else {
          restart = printOnLoanItem(item);
        }
        if (restart) {
          return;
        }
      }
    }

  }

  //print available item
  private boolean printAvailableItem(Item item) {
    System.out.println("\nSelected item is:");
    System.out.println(item.getDetail());
    System.out.println(
        "\nEnter 'b' to borrow, enter 'a' to rate this item or any other key to restart");
    String input = scanner.nextLine();
    //borrow
    if (input.equals("b")) {
      if (item.isStatus()) {
        item.setStatus(false);
      }
      Date date = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE, item.getMaxBorrowingTime());
      Date dueDate = calendar.getTime();
      item.setDueDate(dueDate);
      System.out.println("This item's due date is " + item.getDueDateStr());
      return printOnLoanItem(item);
    }
    //rate this item
    else if (input.equals("a")) {
      System.out.println("Please enter you rating (0 - 10)");
      int rating = scanner.nextInt();
      scanner.nextLine();
      if (rating < 0 || rating > 10) {
        System.out.println("rating should be in (0 - 10)");
        return false;
      }
      double originRating = item.getRating();
      int newReviewNum = item.getNumOfReviewer() + 1;
      double targetRating = (originRating + rating) / newReviewNum;
      item.setRating(targetRating);
      item.setNumOfReviewer(newReviewNum);
      System.out.println(
          "This item's new average rating is " + String.format("%.1f", targetRating));
      return printAvailableItem(item);
    } else {
      return true;
    }
  }

  //print on loan item
  private boolean printOnLoanItem(Item item) {
    System.out.println("\nSelected item is:");
    System.out.println(item.getBorrowedDetail());
    System.out.println(
        "\nEnter 'r' to return the item, enter 'a' to rate this item or any other key to restart");
    String input = scanner.nextLine();
    //return
    if (input.equals("r")) {
      item.setStatus(true);
      item.setDueDate(null);
      System.out.println("This item is returned");
      return printAvailableItem(item);
    }
    //rate this item
    else if (input.equals("a")) {
      System.out.println("Please enter you rating (0 - 10)");
      int rating = scanner.nextInt();
      scanner.nextLine();
      if (rating < 0 || rating > 10) {
        System.out.println("rating should be in (0 - 10)");
        return false;
      }
      double originRating = item.getRating();
      int newReviewNum = item.getNumOfReviewer() + 1;
      double targetRating = (originRating + rating) / newReviewNum;
      item.setRating(targetRating);
      item.setNumOfReviewer(newReviewNum);
      System.out.println(
          "This item's new average rating is " + String.format("%.1f", targetRating));

      return printOnLoanItem(item);
    } else {
      return true;
    }
  }

  //Determine whether the input is a number
  private boolean isNumber(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public void displaySortedItems() {
    System.out.println("List of all items in the library:");
    for (Item item : library.getAllItems()) {
      System.out.println(item.getSortedDescription());
    }
  }

  public static void main(String[] args) {
    LibraryStart start = new LibraryStart();
    start.run();
  }

}
