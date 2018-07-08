package jsp;

import java.util.Calendar;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class User {

    private final int id;
    private final String name;
    private final String login;
    private final String password;
    private final String email;
    private final Calendar createDate;
    private final Role role;
    private final String country;
    private final String city;

    public User(Builder builder) {
        this.id = builder.nestedId;
        this.name = builder.nestedName;
        this.login = builder.nestedLogin;
        this.password = builder.nestedPassword;
        this.email = builder.nestedEmail;
        this.createDate = builder.nestedCreateDate;
        this.role = builder.nestedRole;
        this.country = builder.nestedCountry;
        this.city = builder.nestedCity;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public static class Builder {
        private int nestedId;
        private String nestedName;
        private String nestedLogin;
        private String nestedPassword;
        private String nestedEmail;
        private Calendar nestedCreateDate;
        private Role nestedRole;
        private String nestedCountry;
        private String nestedCity;

        public Builder setNestedId(int nestedId) {
            this.nestedId = nestedId;
            return this;
        }

        public Builder setNestedName(String nestedName) {
            this.nestedName = nestedName;
            return this;
        }

        public Builder setNestedLogin(String nestedLogin) {
            this.nestedLogin = nestedLogin;
            return this;
        }

        public Builder setNestedPassword(String nestedPassword) {
            this.nestedPassword = nestedPassword;
            return this;
        }

        public Builder setNestedEmail(String nestedEmail) {
            this.nestedEmail = nestedEmail;
            return this;
        }

        public Builder setNestedCreateDate(Calendar nestedCreateDate) {
            this.nestedCreateDate = nestedCreateDate;
            return this;
        }

        public Builder setNestedRole(Role nestedRole) {
            this.nestedRole = nestedRole;
            return this;
        }

        public Builder setNestedCountry(String nestedCountry) {
            this.nestedCountry = nestedCountry;
            return this;
        }

        public Builder setNestedCity(String nestedCity) {
            this.nestedCity = nestedCity;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id
                + ", name="
                + name
                + ", login="
                + login
                + ", password="
                + password
                + ", email="
                + email
                + ", createDate="
                + createDate.getTime()
                + ", role="
                + role
                + ", country="
                + country
                + ", city="
                + city
                + '}';
    }
}

