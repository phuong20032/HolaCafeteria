package common;

import java.util.regex.*;

public class InOutUtils {

    public static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]{6,20}$");
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    public static boolean isValidFullName(String fullName) {
        return fullName.matches("^[a-zA-Z\\s]+$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^0[0-9]+$");
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidAddress(String address) {
        if (address == null || address.isEmpty()) {
            return false;
        }

        if (!address.matches("^[a-zA-Z0-9- ]+$")) {
            return false;
        }

        return true;
    }

    
    public static boolean isValidCategory(String category) {
        if (category == null || category.length() < 2 || category.length() > 20) {
            return false;
        }
        String pattern = "^[A-Za-z _]+$";
        return category.matches(pattern);
    }
}
