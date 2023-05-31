import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The item class is the parent class of move, book and journal, and contains some common
 * attributes, such as type, id, title, year, etc. At the same time, it also implements the
 * Comparable interface for sorting and using
 */
public class Item implements Comparable<Item> {

  private String type;

  private Integer id;

  private String title;

  private Integer year;

  private Integer maxBorrowingTime;

  private double rating;

  private boolean status;

  private Integer numOfReviewer;

  private Date dueDate;

  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

  public Item(String type, Integer id, String title, Integer year) {
    this.type = type;
    this.id = id;
    this.title = title;
    this.year = year;
    setRating(0);
    setNumOfReviewer(0);
    setStatus(true);
  }

  public String getType() {
    return type;
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Integer getYear() {
    return year;
  }

  public Integer getMaxBorrowingTime() {
    return maxBorrowingTime;
  }

  public void setMaxBorrowingTime(Integer maxBorrowingTime) {
    this.maxBorrowingTime = maxBorrowingTime;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Integer getNumOfReviewer() {
    return numOfReviewer;
  }

  public void setNumOfReviewer(Integer numOfReviewer) {
    this.numOfReviewer = numOfReviewer;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  //get brief description include id type and title
  public String getBriefDescription() {
    return String.format("ID: %-10sType: %-15sTitle: %s", getId(), getType(), getTitle());
  }

  //get sorted description include rating reviews id type and title
  public String getSortedDescription() {
    return String.format("Average rating: %-5sNumber of reviewers: %-5s%s",
        String.format("%.2f", getRating()), getNumOfReviewer(), getBriefDescription());
  }

  //item detail information
  public String getDetail() {
    return String.format("Type: %s\nTitle: %s\nID: %s\nYear: %s\nAverage Rating: %s\n"
            + "Number of reviewers: %s\nStatus: %s", getType(), getTitle(), getId(), getYear(),
        String.format("%.1f", getRating()), getNumOfReviewer(),
        isStatus() ? "available" : "on loan");
  }

  //item detail information include due date
  public String getBorrowedDetail() {
    return String.format("Type: %s\nTitle: %s\nID: %s\nYear: %s\nAverage Rating: %s\n"
            + "Number of reviewers: %s\nStatus: %s\nDue Date: %s", getType(), getTitle(), getId(),
        getYear(),
        String.format("%.1f", getRating()), getNumOfReviewer(),
        isStatus() ? "available" : "on loan",
        getDueDateStr());
  }

  //return due date str, format is yyyy/MM/dd
  public String getDueDateStr() {
    return sdf.format(getDueDate());
  }

  //compareTo method, used to sort by rating first then by id
  @Override
  public int compareTo(Item o) {
    if (Double.compare(o.getRating(), getRating()) == 0) {
      return getId().compareTo(o.getId());
    } else {
      return Double.compare(o.getRating(), getRating());
    }
  }
}
