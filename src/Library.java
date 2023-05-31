import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Library class, used to store book, movie and journal, including the way of querying by id and
 * title
 */
public class Library {

  //list to store all items include book, movie and journal
  private List<Item> allItems;

  public Library() {
    this.allItems = new ArrayList<>();
  }

  //read all items from file
  public void readItemsFromFile(String filePath) {
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        //process type
        String[] values = line.split(",");
        String type = values[0];
        Item item = null;
        if (type.equals("Movie")) {
          //add move object
          item = new Movie(values[0], Integer.parseInt(values[1]), values[2],
              Integer.parseInt(values[3]), values[4]);
        } else if (type.equals("Book")) {
          //add book object
          item = new Book(values[0], Integer.parseInt(values[1]), values[2],
              Integer.parseInt(values[3]), values[4], Integer.parseInt(values[5]));
        } else if (type.equals("Journal")) {
          //add journal object
          item = new Journal(values[0], Integer.parseInt(values[1]), values[2],
              Integer.parseInt(values[3]), Integer.parseInt(values[4]),
              Integer.parseInt(values[5]));
        }
        allItems.add(item);
      }

    } catch (Exception e) {
      System.out.println("Read items from file error!");
    }
  }

  public List<Item> getAllItems() {
    return allItems;
  }


  //search item by input id
  public Item searchItemById(Integer id) {
    for (Item item : allItems) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  //search item by title, title is a fuzzy query
  public List<Item> searchItemByTitle(String title) {
    List<Item> items = new ArrayList<>();
    for (Item item : allItems) {
      String itemTitle = item.getTitle().toLowerCase();
      if (itemTitle.contains(title.toLowerCase())) {
        items.add(item);
      }
    }
    return items;
  }
}
