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
        String email = "[a-z]@gmail.com";
        Pattern patternPhone = Pattern.compile(phone);
        Pattern patternEmail = Pattern.compile(email);
        String[] components = data.split("\\s+");
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        Matcher matcherPhone = patternPhone.matcher(components[INDEX_PHONE]);
        Matcher matcherEmail = patternEmail.matcher(components[INDEX_EMAIL]);
        if (components.length != 4) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (!matcherPhone.find()) {
            throw new IllegalArgumentException();
        }
        if (!matcherEmail.find()) {
            throw new IllegalArgumentException();
        }
         else {
            storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
        }
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