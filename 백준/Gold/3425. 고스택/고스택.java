import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            ArrayList<String> commands = new ArrayList<>();

            while (true) {
                String com = br.readLine();
                if (com.equals("QUIT")) {
                    System.out.print(sb.toString());
                    return;
                }
                if (com.equals("END"))
                    break;
                commands.add(com);
            }

            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                ArrayDeque<Integer> stack = new ArrayDeque<>();
                stack.push(Integer.parseInt(br.readLine()));
                boolean ok = true;
                for (String com : commands) {
                    if (com.contains("NUM")) {
                        int num = Integer.parseInt(com.split(" ")[1]);
                        ok = commandNum(stack, num);
                    } else {
                        switch (com) {
                            case "POP":
                                ok = commandPop(stack);
                                break;
                            case "INV":
                                ok = commandInv(stack);
                                break;
                            case "DUP":
                                ok = commandDup(stack);
                                break;
                            case "SWP":
                                ok = commandSwp(stack);
                                break;
                            case "ADD":
                                ok = commandAdd(stack);
                                break;
                            case "SUB":
                                ok = commandSub(stack);
                                break;
                            case "MUL":
                                ok = commandMul(stack);
                                break;
                            case "DIV":
                                ok = commandDiv(stack);
                                break;
                            case "MOD":
                                ok = commandMod(stack);
                                break;
                        }
                    }
                    if (!ok) {
                        break;
                    }
                }
                if (!ok || stack.size() != 1) {
                    sb.append("ERROR\n");
                } else {
                    sb.append(stack.pop()).append("\n");
                }
            }
            sb.append("\n");
        }
    }

    public static boolean commandNum(ArrayDeque<Integer> stack, int num) {
        stack.push(num);
        return true;
    }

    public static boolean commandPop(ArrayDeque<Integer> stack) {
        if (stack.isEmpty()) {
            return false;
        }
        stack.pop();
        return true;
    }

    public static boolean commandInv(ArrayDeque<Integer> stack) {
        if (stack.isEmpty()) {
            return false;
        }
        int num = stack.pop();
        stack.push(num * -1);
        return true;
    }

    public static boolean commandDup(ArrayDeque<Integer> stack) {
        if (stack.isEmpty()) {
            return false;
        }
        int num = stack.pop();
        stack.push(num);
        stack.push(num);
        return true;
    }

    public static boolean commandSwp(ArrayDeque<Integer> stack) {
        if (stack.isEmpty() || stack.size() < 2) {
            return false;
        }
        int temp1 = stack.pop();
        int temp2 = stack.pop();

        stack.push(temp1);
        stack.push(temp2);
        return true;
    }

    public static boolean commandAdd(ArrayDeque<Integer> stack) {
        if (stack.isEmpty() || stack.size() < 2) {
            return false;
        }
        long num1 = stack.pop();
        long num2 = stack.pop();
        long num = num2 + num1;
        if (Math.abs(num) > 1_000_000_000) {
            return false;
        }
        stack.push((int) num);
        return true;
    }

    public static boolean commandSub(ArrayDeque<Integer> stack) {
        if (stack.isEmpty() || stack.size() < 2) {
            return false;
        }
        long num1 = stack.pop();
        long num2 = stack.pop();
        long num = num2 - num1;
        if (Math.abs(num) > 1_000_000_000) {
            return false;
        }
        stack.push((int) num);
        return true;
    }

    public static boolean commandMul(ArrayDeque<Integer> stack) {
        if (stack.isEmpty() || stack.size() < 2) {
            return false;
        }
        long num1 = stack.pop();
        long num2 = stack.pop();
        long num = num2 * num1;
        if (Math.abs(num) > 1_000_000_000) {
            return false;
        }
        stack.push((int) num);
        return true;
    }

    public static boolean commandDiv(ArrayDeque<Integer> stack) {
        if (stack.isEmpty() || stack.size() < 2) {
            return false;
        }
        long num1 = stack.pop();
        long num2 = stack.pop();
        if (num1 == 0) {
            return false;
        }
        long result = num2 / num1;
        if (Math.abs(result) > 1_000_000_000) {
            return false;
        }
        stack.push((int) result);
        return true;
    }

    public static boolean commandMod(ArrayDeque<Integer> stack) {
        if (stack.isEmpty() || stack.size() < 2) {
            return false;
        }
        long num1 = stack.pop();
        long num2 = stack.pop();
        if (num1 == 0) {
            return false;
        }
        long result = num2 % num1;
        if (Math.abs(result) > 1_000_000_000) {
            return false;
        }
        stack.push((int) result);
        return true;
    }
}
