package streams;

import model.Permission;
import model.Role;
import model.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static streams.StreamsUseCase.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamsUseCaseSpec {

    @Test
    public void toUpperCaseShouldConvertCollectionElementsToUpperCase() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("MY", "NAME", "IS", "JOHN", "DOE");
        assertThat(map(collection)).hasSameElementsAs(expected);
    }

    @Test
    public void filterShouldFilterStringsLessThan4Characters() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("My", "is", "Doe");
        assertThat(filter(collection)).hasSameElementsAs(expected);
    }

    @Test
    public void flattenShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");
        assertThat(flatten(collection)).hasSameElementsAs(expected);
    }

    @Test
    public void permissionsDistincShouldFlattenUserPermissions() {

        Role role = new Role("R");
        role.addPermission(new Permission("P1"));
        role.addPermission(new Permission("P2"));
        role.addPermission(new Permission("P3"));
        role.addPermission(new Permission("P4"));

        User user = new User("u");
        user.addRole(role);

        role = new Role("R1");
        role.addPermission(new Permission("P5"));
        role.addPermission(new Permission("P6"));
        role.addPermission(new Permission("P3"));
        role.addPermission(new Permission("P4"));
        user.addRole(role);

        List<Permission> expected = asList(new Permission("P1"), new Permission("P2"), new Permission("P3"),
                new Permission("P4"), new Permission("P5"), new Permission("P6"));

        assertThat(permissionsDistinct(user)).hasSameElementsAs(expected);
    }

    @Test
    public void oldestUserShouldReturnTheOldestUserByAge() {
        User userAge10 = new User("userAge10", 10);
        User userAge20 = new User("userAge20", 20);
        User userAge30 = new User("userAge30", 30);

        List<User> example = asList(userAge10, userAge20, userAge30);

        assertThat(oldestUser(example)).isEqualToComparingFieldByField(userAge30);
    }

    @Test
    public void reduce () {

        User userMoney11 = new User("userAge10", new BigDecimal("1.1"));
        User userMoney12 = new User("userAge20", new BigDecimal("1.2"));
        User userMoney33 = new User("userAge30", new BigDecimal("3.3"));

        List<User> users = asList(userMoney11, userMoney12, userMoney33);

        assertThat(totalUsersMoney(users)).isEqualTo(new BigDecimal("5.6"));
    }
}
