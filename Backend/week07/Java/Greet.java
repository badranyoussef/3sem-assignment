import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Greet {

    private final static String helloFriend = "Hello, my friend.";

    public static String allMethods(Object o) {
//        Requirement 2
        if (o == null) {
            return helloFriend;

        } else if (o instanceof String) {
//        Requirement 3
            if (o.equals((((String) o).toUpperCase()))) {
                return "HELLO, " + o + "!";
//        Requirement 1
            } else {
                return "Hello, " + o + ".";
            }
        } else if (o instanceof String[]) {
            String[] names = (String[]) o;
            List<String> normalNames = new ArrayList<>();
            List<String> shoutedNames = new ArrayList<>();
            List<String> splitted = new ArrayList<>();

            for (String s : names) {

//              Requirement 8
                if (s.startsWith("\"") && s.endsWith("\"")) {
                    s = s.substring(1, s.length() - 1);
                    normalNames.add(s);
                    // Hvis navnet indeholder et komma, behandles det som flere navne, ellers som et enkelt navn.
                    if (s.contains(",")) {
                        String[] splitNames = s.split(",\\s*");
                        for (String splitName : splitNames) {
                            splitted.add(splitName);
                            // Hvis et af de splittede navne er skrevet med store bogstaver, tilføjes det til råbende navne.

                        }
                        return "Hello, "+ names[0] + " and " + splitted.get(0)+", " + splitted.get(1) + "." ;
                    }
                }

//                Requirement 7
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

//                Requirement 6
                } else if (s.equals(s.toUpperCase())) {
                    normalNames = Arrays.stream(names)
                            .filter(name -> !name.equals(name.toUpperCase()))
                            .collect(Collectors.toList());
                    shoutedNames = Arrays.stream(names)
                            .filter(name -> name.equals(name.toUpperCase()))
                            .collect(Collectors.toList());

                    String normalGreeting = normalNames.isEmpty() ? "" :
                            "Hello, " + String.join(" and ", normalNames) + ".";
                    String shoutedGreeting = shoutedNames.isEmpty() ? "" :
                            "AND HELLO " + String.join(" AND HELLO ", shoutedNames) + "!";
                    return normalGreeting + (normalNames.isEmpty() || shoutedNames.isEmpty() ? "" : " ") + shoutedGreeting;
                }
            }
//            Requirement 4
            if (names.length == 2) {
                return "Hello, " + names[0] + " and " + names[1] + ".";
//            Requirement 5
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

// 8
//    public static String names (Object o) {
//        if (o instanceof String[]) {
//            String[] names = (String[]) o;
//            List<String> normalNames = new ArrayList<>();
//            List<String> shoutedNames = new ArrayList<>();
//            // Itererer igennem hvert navn i arrayet.
//            for (String name : names) {
//                // Hvis navnet er omsluttet af anførselstegn, fjernes disse og navnet tilføjes som en enkelt streng.
//                if (name.startsWith("\"") && name.endsWith("\"")) {
//                    name = name.substring(1, name.length() - 1);
//                    normalNames.add(name);
//                    // Hvis navnet indeholder et komma, behandles det som flere navne, ellers som et enkelt navn.
//                if (name.contains(",")) {
//                        String[] splitNames = name.split(",\\s*");
//                        for (String splitName : splitNames) {
//                            // Hvis et af de splittede navne er skrevet med store bogstaver, tilføjes det til råbende navne.
//                            if (splitName.equals(splitName.toUpperCase())) {
//                                shoutedNames.add(splitName);
//                            }
//                            // Ellers tilføjes det til normale navne.
//                            else {
//                                normalNames.add(splitName);
//                                return "Hello, "+ names[0] + " and " + normalNames.get(0)+".";
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return "TEST";
//    }
}
