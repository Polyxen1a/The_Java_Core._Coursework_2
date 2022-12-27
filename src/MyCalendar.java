import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyCalendar {

    private static final Map<Integer, Repeatable> actualTasks = new HashMap<>();

    public static void addTask(Scanner scanner) throws WrongInputException {
        scanner.nextLine();
        System.out.println("Введите название задачи: ");
        String title = ValidateUtils.checkString(scanner.nextLine());
        System.out.println("Введите описание задачи: ");
        String description = ValidateUtils.checkString(scanner.nextLine());
        System.out.println(" Введите тип задачи: 0 - Рабочая 0 - Личная");
        TaskType taskType = TaskType.values()[scanner.nextInt()];
        System.out.println("Введите повторяемость задачи: 0 - Однократная, 1 - Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
        int occurance = scanner.nextInt();
        System.out.println("Введите дату dd.mm.yyyy HH:mm ");
        scanner.nextLine();
    }

    private static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occurance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.mm.yyy HH:mm"));
            Repeatable task = null;
            task = createTask(occurance, title, description, taskType, eventDate);
            System.out.println("Создана задача " + task);

        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат dd.mm.yyyy. HH:mm и попробуйте ещё раз");
            createEvent(scanner, title, description, taskType, occurance);
        }
    }

    private static Repeatable createTask(int occurance, String title, String description, TaskType taskType, LocalDateTime eventDate) {
        return null;
    }

    public static void editTask(Scanner scanner) {

    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи \n");
        printActualTasks();
        System.out.println("Для удаления введите ID задачи \n");
        int id = scanner.nextInt();
        if (actualTasks.containsKey(id)) {
            actualTasks.remove(id);
        }
    }

    private static void printActualTasks() {
    }

    public static void getTasksByDay(Scanner scanner) {
    }

    public static void printArchivedTasks() {

    }

    public static void getGroupedByDate() {

    }
}
