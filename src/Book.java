/**
 * Book class extends Item class, max borrowing time is 28
 */
public class Book extends Item {

  private String author;

  private Integer numOfPage;

  public Book(String type, Integer id, String title, Integer year,
      String author, Integer numOfPage) {
    super(type, id, title, year);
    this.author = author;
    this.numOfPage = numOfPage;
    setMaxBorrowingTime(28);
  }

  public String getAuthor() {
    return author;
  }

  public Integer getNumOfPage() {
    return numOfPage;
  }

  @Override
  public String getDetail() {
    return String.format(
        "%s\nAuthor: %s\nNumber of pages: %s\nMax number of days for borrowing: %s",
        super.getDetail(),
        getAuthor(), getNumOfPage(), getMaxBorrowingTime());
  }

  //
  @Override
  public String getBorrowedDetail() {
    return String.format(
        "%s\nAuthor: %s\nNumber of pages: %s\nMax number of days for borrowing: %s",
        super.getBorrowedDetail(),
        getAuthor(), getNumOfPage(), getMaxBorrowingTime());
  }

}
