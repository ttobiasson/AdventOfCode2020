import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class part2 {
    final static String OPERATORS = "+-*";
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.lines(Path.of("input.txt")).iterator();
        addAllLines(iter);   
    }

    private static void addAllLines(Iterator<String> iter) {
        List<Double>ans = new ArrayList<Double>();
        while(iter.hasNext()){
            String line = iter.next();
            List<String> tokens = tokenize(line);
            List<String> postfix = infixToPostfix(tokens);
            
            ans.add(evalPostfix(postfix));
        }
        long answer = ans.stream().map(Double::longValue).reduce(Long::sum).get();
        System.out.println(answer);
    }
    private static List<String> infixToPostfix(List<String>infix){
        List <String> postfix = new ArrayList<>();
        Deque<String> op  = new ArrayDeque<>();
        Deque<String> que = new ArrayDeque<>();
    
        for(int p = 0; p < infix.size(); p++) {
            String a = infix.get(p);

            if (a.matches("[0-9]+")){
                que.push(a);
            }
            if ("+-*/^".indexOf(a) != -1){
                while(!(op.isEmpty()) && ((precedence(op.peek()) >= precedence(a)) && assoc(a) == 'l') && !a.equals("(")){
                    que.push(op.pop());
                }
                op.push(a);
            }
            if(a.equals("(")){
                op.push(a);
            }
            if(a.equals(")")){
                while(op.size() > 0 && !op.peek().equals("(")){
                    que.push(op.pop());
                }
                op.pop();
            }
        }
    
    
        while(!op.isEmpty()){
            que.push(op.pop());
        }
        int s = que.size();
    
        for(int n = 0; n < s; n++){
            postfix.add(n, que.pop());
        }
    
        Collections.reverse(postfix);
    
        return postfix;
    }
    private static double evalPostfix(List<String> postfix) {
        Deque<String> num = new ArrayDeque<>();

        for(String x : postfix) {
            if(!OPERATORS.contains(x)){
                num.push(x);
            }else {
                double num2;
                double num1;
                
                num2 = Double.valueOf(num.pop());
                num1 = Double.valueOf(num.pop());
                
                int op      = OPERATORS.indexOf(x);

                switch (op) {
                    case 0:
                        num.push(String.valueOf(num1 + num2));
                        break;

                    case 1:
                        num.push(String.valueOf(num1 - num2));
                        break;

                    case 2:
                        num.push(String.valueOf(num1 * num2));
                        break;

                    case 3:
                        num.push(String.valueOf(num1 / num2));
                        break;

                    case 4:
                        num.push(String.valueOf(Math.pow(num1, num2)));
                        break;

                }
            }
        }
        double returnValue = Double.valueOf(num.pop());
        return returnValue;
    }
    private static List<String> tokenize(String expr) {
        
        String[] arrExpr = expr.replaceAll("\\(", "( ")
                               .replaceAll("\\)", " )")
                               .split(" ");

        List<String> xs = Arrays.asList(arrExpr);
        
        return xs;
    }
    private static int precedence(String a){
        char ch = a.charAt(0);
        int p = 0;
        if(ch == '+'){p = 3;}
        if(ch == '-'){p = 2;}
        if(ch == '*'){p = 2;}

        return p;
    }
    private static char assoc(String a){
        char ch = a.charAt(0);
        char r;
        if(ch == '^'){
            r = 'r';
        }
        else {r = 'l';}

        return r;

    }
}