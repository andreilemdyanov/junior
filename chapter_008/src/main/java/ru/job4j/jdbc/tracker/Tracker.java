package ru.job4j.jdbc.tracker;

        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;

/**
 * Class Tracker.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 26.12.2017
 */
public class Tracker {
    /**
     * Поле соединение.
     */
    DBConnection conn;

    /**
     * Конструктор default.
     */
    public Tracker() {
    }

    /**
     * Конструктор с параметром.
     *
     * @param conn соединение.
     */
    public Tracker(DBConnection conn) {
        this.conn = conn;
    }

    /**
     * Метод добавления объекта в БД.
     * После добавления, поля объекта id и create обновляются через сеттеры из БД.
     *
     * @param item элемент для добавления.
     * @return item добавленный элемент.
     */
    public Item add(Item item) {
        String sql = "INSERT INTO item (name, description, create_date) VALUES (?,?, CURRENT_TIMESTAMP)";
        String sql1 = "SELECT create_date, id FROM item WHERE name = ? AND description = ?";
        try (PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
             PreparedStatement pstmt1 = conn.getConn().prepareStatement(sql1)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDesc());
            pstmt.executeUpdate();
            pstmt1.setString(1, item.getName());
            pstmt1.setString(2, item.getDesc());
            ResultSet rs = pstmt1.executeQuery();
            rs.next();
            item.setCreate(rs.getTimestamp("create_date"));
            item.setId(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод для замены элемента по id.
     *
     * @param item заменяющий элемент.
     */
    public Item update(Item item) {
        String sql = "UPDATE item SET name = ?, description = ?, create_date = CURRENT_TIMESTAMP WHERE id = ?";
        String sql1 = "SELECT create_date FROM item WHERE id = ?";
        try (PreparedStatement pstmt = conn.getConn().prepareStatement(sql);
             PreparedStatement pstmt1 = conn.getConn().prepareStatement(sql1)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDesc());
            pstmt.setInt(3, item.getId());
            pstmt.executeUpdate();
            pstmt1.setInt(1, item.getId());
            ResultSet rs1 = pstmt1.executeQuery();
            rs1.next();
            item.setCreate(rs1.getTimestamp("create_date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод для удаления элемента.
     *
     * @param id элемент который надо удалить.
     */
    public void delete(int id) {
        String sql = "DELETE FROM item WHERE id = ?";
        try (PreparedStatement pstmt = conn.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод ищет элемент по имени.
     *
     * @param name имя элемента для поиска.
     * @return result возвращает список с совпадающими именами.
     */
    public ArrayList<Item> findByName(String name) {
        String sql = "SELECT * FROM item WHERE name = ?";
        ArrayList<Item> result = null;
        try (PreparedStatement pstmt = conn.getConn().prepareStatement(sql);) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            result = this.generateItem(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод для поиска элемента по id.
     *
     * @param id для поиска.
     * @return result возвращает найденный элемент или null.
     */
    public Item findById(int id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        Item result = null;
        try (PreparedStatement pstmt = conn.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDesc(rs.getString("description"));
            item.setCreate(rs.getTimestamp("create_date"));
            result = item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод возвращает список всех объектов.
     *
     * @return копия.
     */
    public ArrayList<Item> getAll() {
        String sql = "SELECT * FROM item";
        ArrayList<Item> result = null;
        try (PreparedStatement pstmt = conn.getConn().prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            result = this.generateItem(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод для генерации списка объектов из запроса.
     *
     * @param rs результат запроса.
     * @return список объектов.
     * @throws SQLException исключения.
     */
    private ArrayList<Item> generateItem(ResultSet rs) throws SQLException {
        ArrayList<Item> list = new ArrayList<>();
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDesc(rs.getString("description"));
            item.setCreate(rs.getTimestamp("create_date"));
            list.add(item);
        }
        return list;
    }
}
