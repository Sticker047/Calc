import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String[] ARAB = "0123456789".split("");
    private static final String[] ROME = {"I", "V", "X"};
    private static List<String> str = Arrays.asList("I II III IV V VI VII VIII IX X".split(" "));
    private static List<String> str2 = Arrays.asList("X XX XXX XL L LX LXX LXXX XC C".split(" "));

    private enum Type {
        ARAB,
        ROME
    }

    private static Type type;

    public static void main(String[] args) throws Exception {
//        System.out.println(calc("1 + 4"));
//        System.out.println(calc("VI / III"));
//        System.out.println(calc("VII * II"));
//        System.out.println(calc("I + 1"));
//        System.out.println(calc("1 + 2 + 3"));

        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }


    public static String calc(String input) throws Exception {
        checkInput(input);
        input = input.replace(" ", "");
        if (type == Type.ARAB) {
            String[] args = input.split("\\+");
            if (args.length == 2) {
                for (String el : args) {
                    if (Integer.parseInt(el) > 10 || Integer.parseInt(el) < 0) {
                        throw new Exception();
                    }
                }
                return String.valueOf(Integer.parseInt(args[0]) + Integer.parseInt(args[1]));
            } else if (args.length == 1) {
                args = args[0].split("-");
                if (args.length == 2) {
                    for (String el : args) {
                        if (Integer.parseInt(el) > 10 || Integer.parseInt(el) <= 10) {
                            throw new Exception();
                        }
                    }
                    return String.valueOf(Integer.parseInt(args[0]) - Integer.parseInt(args[1]));
                } else if (args.length == 1) {
                    args = args[0].split("\\*");
                    if (args.length == 2) {
                        for (String el : args) {
                            if (Integer.parseInt(el) > 10 || Integer.parseInt(el) <= 10) {
                                throw new Exception();
                            }
                        }
                        return String.valueOf(Integer.parseInt(args[0]) * Integer.parseInt(args[1]));
                    } else if (args.length == 1) {
                        args = args[0].split("/");
                        if (args.length == 2) {
                            for (String el : args) {
                                if (Integer.parseInt(el) > 10 || Integer.parseInt(el) <= 10) {
                                    throw new Exception();
                                }
                            }
                            return String.valueOf(Integer.parseInt(args[0]) / Integer.parseInt(args[1]));
                        } else {
                            throw new Exception();
                        }
                    } else {
                        throw new Exception();
                    }
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } else if (type == Type.ROME) {
            if (input.contains("+")) {
                String[] split = input.split("\\+");
                if (split.length != 2) {
                    throw new Exception();
                }
                if (str.contains(split[0]) && str.contains(split[1])) {
                    int result = (str.indexOf(split[0]) + 1) + (str.indexOf(split[1]) + 1);
                    if (result <= 0) {
                        throw new Exception();
                    } else {
                        if (result < 11) {
                            return str.get(result - 1);
                        }
                    }
                    return convertFromRomeToArab(result);
                } else {
                    throw new Exception();
                }
            } else if (input.contains("-")) {
                String[] split = input.split("-");
                if (split.length != 2) {
                    throw new Exception();
                }
                if (str.contains(split[0]) && str.contains(split[1])) {
                    return convertFromRomeToArab((str.indexOf(split[0]) + 1) - (str.indexOf(split[1]) + 1));
                } else {
                    throw new Exception();
                }
            } else if (input.contains("*")) {
                String[] split = input.split("\\*");
                if (split.length != 2) {
                    throw new Exception();
                }
                if (str.contains(split[0]) && str.contains(split[1])) {
                    return convertFromRomeToArab((str.indexOf(split[0]) + 1) * (str.indexOf(split[1]) + 1));
                } else {
                    throw new Exception();
                }
            } else if (input.contains("/")) {
                String[] split = input.split("/");
                if (split.length != 2) {
                    throw new Exception();
                }
                if (str.contains(split[0]) && str.contains(split[1])) {
                    return convertFromRomeToArab((str.indexOf(split[0]) + 1) / (str.indexOf(split[1]) + 1));
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    private static void checkInput(String input) throws Exception {
        boolean hasArab = false;
        boolean hasRome = false;

        for (String letter : ARAB) {
            if (input.contains(letter)) {
                hasArab = true;
                type = Type.ARAB;
                break;
            }
        }
        for (String letter : ROME) {
            if (input.contains(letter)) {
                hasRome = true;
                type = Type.ROME;
                break;
            }
        }

        if (hasArab && hasRome) {
            throw new Exception();
        }
    }

    private static String convertFromRomeToArab(int input) throws Exception {
        if (input <= 0) {
            throw new Exception();
        }
        String res = "";
        if (input / 10 > 0) {
            res = res.concat(str2.get(input / 10 - 1));
        }
        res = res.concat(str.get(input % 10 - 1));
        return res;
    }


}