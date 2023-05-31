/**
 * Journal class extends Item class, max borrowing time is 14
 */
public class Journal extends Item {

  private Integer volume;

  private Integer number;

  public Journal(String type, Integer id, String title, Integer year,
      Integer volume, Integer number) {
    super(type, id, title, year);
    this.volume = volume;
    this.number = number;
    setMaxBorrowingTime(14);
  }

  public Integer getVolume() {
    return volume;
  }

  public void setVolume(Integer volume) {
    this.volume = volume;
  }

  public Integer getNumber() {
    return number;
  }

  @Override
  public String getDetail() {
    return String.format(
        "%s\nVolume: %s\nNumber: %s\nMax number of days for borrowing: %s",
        super.getDetail(),
        getVolume(), getNumber(), getMaxBorrowingTime());
  }

  @Override
  public String getBorrowedDetail() {
    return String.format(
        "%s\nVolume: %s\nNumber: %s\nMax number of days for borrowing: %s",
        super.getBorrowedDetail(),
        getVolume(), getNumber(), getMaxBorrowingTime());
  }
}
