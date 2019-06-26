package streams;

import model.Role;
import model.User;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsUseCase {

    public static List map(List<String> list) {
        return list.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static List filter (List<String> list) {
        return list.stream().filter(s -> s.length() < 4).collect(Collectors.toList());
    }

    public static List flatten (List<List<String>> list) {
        return list.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public static List permissionsDistinct (User user) {
        return user.getRoles()
                   .stream()
                   .map(Role::getPermissions)
                   .flatMap(List::stream)
                   .distinct()
                   .collect(Collectors.toList());
    }

    public static User oldestUser (List<User> users) {
        return users.stream()
                    .max(Comparator.comparing(User::getAge))
                    .get();
    }

    public static BigDecimal totalUsersMoney (List<User> users) {
        return users.stream()
                .map(User::getMoney)
                .reduce(BigDecimal::add)
                .get();
    }
}
