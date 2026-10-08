package es.gui.passwordgenerator;

import java.util.*;

public class Password {
    private String password;
    private String keyword;
    private boolean hasLowerCase;
    private boolean hasUpperCase;
    private boolean hasNumbers;
    private boolean hasSpecialChars;
    private boolean hasPrefix;
    private boolean hasSufix;
    private int length;

    private final ArrayList<Character> specialCarsList = new ArrayList<>(Arrays.asList(
            '!', '#', '%', '^', '&', '*', '_', '-',
            ':', ';', '<', '>', '?', '/', '~', '@', '$'
            ));

    private static final Map<Character, List<String>> leetMap = new HashMap<>();
    static {
        leetMap.put('a', Arrays.asList("@", "A", "4","a"));
        leetMap.put('e', Arrays.asList("3", "E", "e"));
        leetMap.put('i', Arrays.asList("1", "I", "!", "i", "^"));
        leetMap.put('o', Arrays.asList("0", "O", "o"));
        leetMap.put('s', Arrays.asList("5", "S", "$", "s"));
    }

    public Password () {
        password = "";
        keyword = "";
        hasLowerCase = false;
        hasUpperCase = false;
        hasNumbers = false;
        hasSpecialChars = false;
        hasSufix = false;
        hasPrefix = false;
        length = 0;
    }

    public boolean anyOptionSelected() {
        return hasLowerCase || hasUpperCase || hasNumbers || hasSpecialChars;
    }

    public boolean hasKeyword() {
        return !this.keyword.isBlank() && !this.keyword.isEmpty();
   }

    public void generateMainPassword() {
        Random rnd = new Random();
        int availableLength = length;
        int auxLength = 0;

        if (hasKeyword()) {
            // We use availableLength for the sufix and prefix
            availableLength -= keyword.length();

            if (hasSufix && availableLength > 0){
                auxLength = rnd.nextInt(availableLength) + 1;
                generateChain(auxLength);
                availableLength -= auxLength;
            }

            password += anyOptionSelected() ? transformKeyword(keyword) : keyword;

            if (hasPrefix) {
                generateChain(availableLength);
            }
        }
        else {
            generateChain(length); // This would be the old generatePassword
        }
        length = password.length();
    }

    private String transformKeyword(String keyword) {
        // This method will tranform certain chars of the keyword (50% chance)
        String newKeyword = "";
        for (int i = 0; i < keyword.length(); i++) {
            newKeyword += transformChar(keyword.charAt(i));
        }
        return newKeyword;
    }

    private String transformChar(char c) {
        // This method chances a certain character into another one (similar)
        Random rnd = new Random();
        int selector;
        String transformed = "";
        transformed += c;

        if (isLeetChar(c) && anyOptionSelected()) {
            // We assure that we pass the leet char as lower because the keys are lower case in the map
            List<String> leetElements = leetMap.get(Character.toLowerCase(c)); // We obtain the list of leet elements
            List<String> validElements = new ArrayList<>();
            for (String element : leetElements) {
                if (checkValid(element)) validElements.add(element);
            }
            if (!validElements.isEmpty()) {
                transformed = validElements.get(rnd.nextInt(validElements.size()));
            }
        }
        else {
            if(hasLowerCase && hasUpperCase) {
                // 50 50 chance to choose
                selector = rnd.nextInt(2);
                if(selector == 0) transformed = transformed.toUpperCase();
                else transformed = transformed.toLowerCase();
            }
            else if (hasLowerCase) {
                transformed = transformed.toLowerCase();
            }
            else if (hasUpperCase) {
                transformed = transformed.toUpperCase();
            }
            // Keep the character when the selected options offer no similar replacement.
        }

        return transformed;
    }


    private boolean checkValid(String s) {
        char letter = s.charAt(0);
        return Character.isLowerCase(letter) && hasLowerCase ||
                Character.isUpperCase(letter) && hasUpperCase ||
                Character.isDigit(letter) && hasNumbers ||
                specialCarsList.contains(letter) && hasSpecialChars;
    }

    private boolean isLeetChar(char c) {
        // In case the argument could be lower or upper case
        // we turn it all into lower case
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 's';
    }

    public void generateChain(int length) { // This method returns the final password result
        Random rnd = new Random();
        int i = 0;

        if (anyOptionSelected()) {
            while(i < length) {
                int selector = rnd.nextInt(10); // 0 .. 9
                char newChar = nextPasswordChar(selector);
                if (newChar != ' ') {
                    password += newChar;
                    i++;
                }
            }
        }
        // The password String is created and updated at the end of method
    }


    /*
    *  Probabilities:
    *  lower case & upper case 30%
    *  numbers & special characters 20 %
    */
    private char nextPasswordChar (int selector) {
        char character = ' ';
        int pos;
        Random rnd = new Random();

        if (hasLowerCase && 0 <= selector && selector <= 2) { // lower case
            pos = rnd.nextInt((int) 'z' - (int) 'a' + 1); // 0 .. 25 + 1
            character = (char) (pos + (int) 'a'); // We need to make sure to add the minimum that is 97 'a'
        }
        else if (hasUpperCase && 3 <= selector && selector <= 5) { // Upper case
            pos = rnd.nextInt((int) 'Z' - (int) 'A' + 1);
            character = (char) (pos + (int) 'A');
        }
        else if (hasNumbers && 6 <= selector && selector <=7 ) { // number
            pos = rnd.nextInt(10);
            character = (char) (pos + '0'); // convert number to char adding '0'
        }
        else if (hasSpecialChars && selector >= 8){ // Special character
            pos = rnd.nextInt(specialCarsList.size());
            character = specialCarsList.get(pos);
        }

        return character;
    }

    public void setHasLowerCase(boolean val) {
        hasLowerCase = val;
    }

    public void setHasUpperCase(boolean val) {
        hasUpperCase = val;
    }

    public void setHasNumber(boolean val) {
        hasNumbers = val;
    }

    public void setHasSpecialChars(boolean val) {
        hasSpecialChars = val;
    }

    public void setLength (int length) {
        this.length = length;
    }
    
    public String getPassword() {
        return password;
    }

    public void setHasPrefix(boolean hasPrefix) {
        this.hasPrefix = hasPrefix;
    }

    public void setHasSufix(boolean hasSufix) {
        this.hasSufix = hasSufix;
    }

    public void setKeyword(String keyword) {
        // Also remove all blank spaces
        this.keyword = keyword.replace(" ", ""); // Removes all blank spaces
    }

    public int getLength() {
        return length;
    }
}
