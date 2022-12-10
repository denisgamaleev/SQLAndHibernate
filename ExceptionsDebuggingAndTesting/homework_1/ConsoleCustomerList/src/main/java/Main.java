import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static Logger logger;
    private static Logger errorLogger;
    private static Logger infoLogger;
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        logger=LogManager.getRootLogger();
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            if (tokens[0].equals("add")) {
                try {
                    logger.info("Пользователь вызвал add");
                    executor.addCustomer(tokens[1]);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    logger.error("не верный формат ввода : ");
                    System.out.println("не верный формат ввода : " + ADD_COMMAND);
                } catch (IllegalAccessException ex) {
                    logger.error("не верный формат email : ");
                    System.out.println("не верный формат email: ");
                } catch (IllegalArgumentException ex) {
                    logger.error("не верный формат phone : ");
                    System.out.println("не верный формат phone : ");
                } catch (Exception ex) {
                    logger.error("Ошибка неизвестного формата");
                    System.out.println("Что то пошло не так");
                }
            } else if (tokens[0].equals("list")) {
                logger.info("Пользователь вызвал list");
                executor.listCustomers();
            } else if (tokens[0].equals("remove")) {
                try {
                    logger.info("Пользователь вызвал remove");
                    executor.removeCustomer(tokens[1]);
                }catch (ArrayIndexOutOfBoundsException ex){
                    logger.error("Пользователь вызвал remove");
                    System.out.println("Укажите кого нужно удалить из списка");
                }
            } else if (tokens[0].equals("count")) {
                logger.info("Пользователь вызвал count");
                System.out.println("There are " + executor.getCount() + " customers");
            } else if (tokens[0].equals("help")) {
                logger.info("Пользователь вызвал help");
                System.out.println(helpText);
            } else {
                System.out.println(COMMAND_ERROR);
            }
        }
    }
}
