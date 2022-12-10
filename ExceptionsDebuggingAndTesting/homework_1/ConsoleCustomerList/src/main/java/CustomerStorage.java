import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws Exception {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        String phone = "[+][7,8][0-9]{10}";
        String email = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        Pattern patternPhone = Pattern.compile(phone);
        Pattern patternEmail = Pattern.compile(email);
        String[] components = data.split("\\s+");
        Matcher matcherPhone = patternPhone.matcher(components[INDEX_PHONE]);
        Matcher matcherEmail = patternEmail.matcher(components[INDEX_EMAIL]);
        if (components.length != 4) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (!matcherPhone.find()) {
            throw new IllegalArgumentException("Wrong format of phone");
        }
        if (!matcherEmail.find()) {
            throw new IllegalAccessException("Wrong format of email");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}