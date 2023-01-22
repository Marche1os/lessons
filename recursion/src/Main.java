import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        System.out.println(BracketsGenerator.generate(2));

        List<User> users = new ArrayList<>();

        users.add(new User(27));
        users.add(new User(100));
        users.add(new User(18));
        users.add(new User(15));
        users.add(new User(13));
        users.add(new User(19));

        System.out.println("Second max = " + MaxValueFounder.getSecondMaximum(users).age);

    }
}

class User implements Comparable<User> {
    int age;
    String name;

    public User(int age) {
        this.age = age;
        name = "Name by " + age;
    }

    @Override
    public int compareTo(@NotNull User o) {
        return age - o.age;
    }
}