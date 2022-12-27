import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatable {
    public YearlyTask(String title, String description, TaskType taskType, LocalDateTime date) throws WrongInputException {
        super(title, description, taskType, date);
    }

    @Override
    public boolean chechOccucance(LocalDateTime requestedDate) {
        return getFirstDate().getYear() == requestedDate.getYear();
    }

    @Override
    public boolean checkOccurance(LocalDateTime localDateTime) {
        return false;
    }
}
