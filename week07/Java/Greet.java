import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Greet {

//    //        Requirement 1
//    public String greet(String name) {
//        return "Hello, " + name + ".";
//    }
//
//    //        Requirement 2
//    public String standIn(String _name) {
//        if (_name == null) {
//            return "Hello, my friend.";
//        } else {
//            return "Hello, " + _name + ".";
//        }
//    }
//
//    //        Requirement 3
//    public String shouting(String name) {
//        if (name.equals(name.toUpperCase())) {
//            return "HELLO, " + name + "!";
//        } else {
//            return "Hello, " + name + ".";
//        }
//    }
//
//
//    //        Requirement 4
//    public String greetAll(String[] names) {
//        return "Hello, " + names[0] + " and " + names[1] + ".";
//    }
//
//
//    public String greetAllWithObject(Object name) {
//        if (name instanceof String[]) {
//            String[] names = (String[]) name;
//            if (names.length == 2) {
//                return "Hello, " + names[0] + " and " + names[1] + ".";
//            }
//        }
//        return "Hello, my friends";
//    }
//
//    //    Requirement 5
//    public String greetEveryone(Object name) {
//
//        String result = "";
//
//        if (name instanceof String[]) {
//            String[] names = (String[]) name;
//            result = String.join(", ", names);
//        }
//        return result + ".";
//    }
//
//    public static String greetEveryone2(String[] names) {
//        if (names.length <= 2) {
//            return String.join(" and ", names);
//        } else {
//            String commaSeparated = IntStream.range(0, names.length - 1)
//                    .mapToObj(i -> names[i])
//                    .collect(Collectors.joining(", "));
//            return "Hello, " + commaSeparated + ", and " + names[names.length - 1] + ".";
//        }
//    }

    // 6

//    public static String mixedNames(String[] names) {
//        List<String> normalNames = Arrays.stream(names)
//                .filter(name -> !name.equals(name.toUpperCase()))
//                .collect(Collectors.toList());
//        List<String> shoutedNames = Arrays.stream(names)
//                .filter(name -> name.equals(name.toUpperCase()))
//                .collect(Collectors.toList());
//
//        String normalGreeting = normalNames.isEmpty() ? "" :
//                "Hello, " + String.join(" and ", normalNames) + ".";
//        String shoutedGreeting = shoutedNames.isEmpty() ? "" :
//                "AND HELLO " + String.join(" AND HELLO ", shoutedNames) + "!";
//
//        return normalGreeting + (normalNames.isEmpty() || shoutedNames.isEmpty() ? "" : " ") + shoutedGreeting;
//    }

    // 7

//    public static String namesWithComma(String[] names) {
//        // Flatten names with commas into a list of individual names
//        String[] allNames = Arrays.stream(names)
//                .flatMap(name -> Arrays.stream(name.split("\\s*,\\s*"))) //  \\s*,\\s* både mellemrum før og efter komma fjernes, splittes ved komma
//                .toArray(String[]::new);
//
//        if (allNames.length == 1) {
//            return "Hello, " + allNames[0] + ".";
//        }
//
//        String lastTwoNames = String.join(" and ", allNames[allNames.length - 2], allNames[allNames.length - 1]);
//        if (allNames.length == 2) {
//            return "Hello, " + lastTwoNames + ".";
//        }
//
//        String commaSeparatedNames = Arrays.stream(allNames, 0, allNames.length - 2)
//                .collect(Collectors.joining(", "));
//
//        return "Hello, " + commaSeparatedNames + ", " + lastTwoNames + ".";
//    }

    private final static String helloFriend = "Hello, my friend.";

    public static String allMethods(Object o) {
        if (o == null) {
            return helloFriend;
        } else if (o instanceof String) {
            if (o.equals((((String) o).toUpperCase()))) {
                return "HELLO, " + o + "!";
            } else {
                return "Hello, " + o + ".";
            }
        } else if (o instanceof String[]) {
            String[] names = (String[]) o;
            for (String s : names) {
                if(s.contains("\"")){
                    List<String> processedNames = new ArrayList<>();
                    StringBuilder buffer = new StringBuilder();
                    boolean withinQuotes = false;

                    // Process each name, respecting quoted sections
                    for (String name : names) {
                        if (name.startsWith("\"")) {
                            withinQuotes = true;
                            buffer.append(name.substring(1)); // Remove starting quote
                        } else if (name.endsWith("\"")) {
                            withinQuotes = false;
                            buffer.append(" ").append(name, 0, name.length() - 1); // Remove ending quote
                            processedNames.add(buffer.toString());
                            buffer.setLength(0); // Clear the buffer
                        } else if (withinQuotes) {
                            buffer.append(" ").append(name);
                        } else {
                            processedNames.add(name);
                        }
                    }

                    // Now handle the formatted list with commas and "and"
                    return "Hello, " + String.join(" and ", processedNames) + ".";
                }
                else if (s.contains(",")) {
                    // Flatten names with commas into a list of individual names
                    String[] allNames = Arrays.stream(names)
                            .flatMap(name -> Arrays.stream(name.split("\\s*,\\s*"))) //  \\s*,\\s* både mellemrum før og efter komma fjernes, splittes ved komma
                            .toArray(String[]::new);

                    if (allNames.length == 1) {
                        return "Hello, " + allNames[0] + ".";
                    }

                    String lastTwoNames = String.join(" and ", allNames[allNames.length - 2], allNames[allNames.length - 1]);
                    if (allNames.length == 2) {
                        return "Hello, " + lastTwoNames + ".";
                    }

                    String commaSeparatedNames = Arrays.stream(allNames, 0, allNames.length - 2)
                            .collect(Collectors.joining(", "));

                    return "Hello, " + commaSeparatedNames + ", " + lastTwoNames + ".";
                } else if (s.equals(s.toUpperCase())) {
                    List<String> normalNames = Arrays.stream(names)
                            .filter(name -> !name.equals(name.toUpperCase()))
                            .collect(Collectors.toList());
                    List<String> shoutedNames = Arrays.stream(names)
                            .filter(name -> name.equals(name.toUpperCase()))
                            .collect(Collectors.toList());

                    String normalGreeting = normalNames.isEmpty() ? "" :
                            "Hello, " + String.join(" and ", normalNames) + ".";
                    String shoutedGreeting = shoutedNames.isEmpty() ? "" :
                            "AND HELLO " + String.join(" AND HELLO ", shoutedNames) + "!";
                    return normalGreeting + (normalNames.isEmpty() || shoutedNames.isEmpty() ? "" : " ") + shoutedGreeting;
                }
            }
            if (names.length == 2) {
                return "Hello, " + names[0] + " and " + names[1] + ".";
            } else if (names.length <= 2) {
                return String.join(" and ", names);
            } else {
                String commaSeparated = IntStream.range(0, names.length - 1)
                        .mapToObj(i -> names[i])
                        .collect(Collectors.joining(", "));
                return "Hello, " + commaSeparated + ", and " + names[names.length - 1] + ".";
            }
        }
        return helloFriend;
    }

}