import annotation.Part;
import annotation.Problem;
import consts.Const;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File answers = new File(Const.ANSWER_DIR);

        System.out.println("input problem number");
        int problemNo = Integer.parseInt(br.readLine());
        System.out.println("input part number");
        int partNo = Integer.parseInt(br.readLine());

        for (String path : Objects.requireNonNull(answers.list())) {
            File entry = new File(answers, path);
            Class<?> clazz = classLoader.loadClass(Const.PACKAGE_NAME + "." + fileNameToClassName(entry.getName()));
            Problem problem = clazz.getAnnotation(Problem.class);

            if (problem.no() == problemNo) {
                for (Method method : clazz.getMethods()) {
                    Part part = method.getAnnotation(Part.class);
                    if (part != null && part.no() == partNo) {
                        System.out.println(method.invoke(clazz.getDeclaredConstructor().newInstance()));
                        System.out.println("Executed part" + partNo + " of problem" + problemNo + ", " + getMethodNameOfClass(clazz, method) + ".");
                        return;
                    }
                }
            }
        }

        throw new NoSuchMethodException("method not found, part" + partNo + " of problem" + problemNo + ".");
    }

    private static String fileNameToClassName(String name) {
        return name.substring(0, name.length() - ".java".length());
    }

    private static String getMethodNameOfClass(Class<?> clazz, Method method) {
        return clazz.getSimpleName() + "#" + method.getName();
    }
}
