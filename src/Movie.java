/**
 * Movie class extends Item class, max borrowing time is 7
 */
public class Movie extends Item {

  private String director;

  public Movie(String type, Integer id, String title, Integer year, String director) {
    super(type, id, title, year);
    this.director = director;
    setMaxBorrowingTime(7);
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  @Override
  public String getDetail() {
    return String.format("%s\nDirector: %s\nMax number of days for borrowing: %s",
        super.getDetail(),
        getDirector(), getMaxBorrowingTime());
  }

  @Override
  public String getBorrowedDetail() {
    return String.format("%s\nDirector: %s\nMax number of days for borrowing: %s",
        super.getBorrowedDetail(),
        getDirector(), getMaxBorrowingTime());
  }

}
