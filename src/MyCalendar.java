import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

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
        return switch (occurance) {
            case 0 -> {
                OncelyTask oncelyTask = new OncelyTask(title, description, taskType, localDateTime);

                actualTasks.put(oncelyTask.getId(), oncelyTask);
                yield, oncelyTask;
            }
            case 1 -> {
                DailyTask task = new DailyTask(title, description, taskType, localDateTime);
                actualTasks.put(task.getId(), task);
                yield, task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, description, taskType, localDateTime);
                actualTasks.put(task.getId(), task);
                yield, task;
            }
            case 3 -> {
                MonthlyTask task = new DailyTask(title, description, taskType, localDateTime)
            }
            actualTasks.put(task.getId(), task);
            yield, task;

            default -> null;
        };
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
        for (Repeatable task : actualTasks.values());
        System.out.println(task);
    }

    public static void getTasksByDay(Scanner scanner) {
    }

    public static void printArchivedTasks() {
        for (Repeatable task : archivedTasks.values()) {
            System.out.println(task);
        }

    }

    public static void getGroupedByDate() {
        Map<LocalDate, ArrayList<Repeatable>> taskMap = new HashMap<>();

        for (Map.Entry<Integer, Repeatable> entry : actualTasks.entrySet());
        Repeatable task = entry.getValue();
        LocalDate localDate = task.getFirstDate().toLocalDate();
        if (taskMap.containsKey(localDate)) {
            taskMap.get(localDate).add(task);
        } else {
            taskMap.put(localDate, new ArrayList<>(Collections.singletonList()));
        }
        for (Map.Entry<LocalDate, ArrayList<Repeatable>> taskEntry : taskMap.entrySet()){
            System.out.println(taskEntry.getKey() + " : " + taskEntry, getValue);
        }
    }

}
