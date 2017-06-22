package pro.ru.job4j.generic;

/**
 * Class Role.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class Role extends Base {
    /**
     * Конструктор.
     *
     * @param id ай ди.
     */
    public Role(String id) {
        this.setId(id);
    }

    /**
     * Переопределение equals.
     *
     * @param obj объект.
     * @return равны?
     */
    @Override
    public boolean equals(Object obj) {
        String o = ((Role) obj).getId();
        return this.getId().equals(o);
    }

    public static void main(String[] args) {
        RoleStore role = new RoleStore(new SimpleArray<>(5));
        role.addRole(new Role("1"));
        Role b = new Role("2");
        role.addRole(b);
        role.addRole(new Role("3"));
        role.addRole(new Role("4"));
        role.addRole(new Role("5"));
        System.out.println(role);
        role.updateRole(3, new Role("6"));
        System.out.println(role);
        role.deleteRole(b);
        System.out.println(role);

        UserStore user = new UserStore(new SimpleArray<>(5));
        user.addUser(new User("1"));
        User c = new User("2");
        user.addUser(c);
        user.addUser(new User("3"));
        user.addUser(new User("4"));
        user.addUser(new User("5"));
        System.out.println(user);
        user.updateUser(3, new User("6"));
        System.out.println(user);
        user.deleteUser(c);
        System.out.println(user);

    }
}
