package com.zkyproject1.onlineorder.dao;

import com.zkyproject1.onlineorder.entity.MenuItem;
import com.zkyproject1.onlineorder.entity.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        // try {
        //  } catch {
        //  } finally {
        //  }
        //这种写法需要写finally 去关掉session
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createCriteria(Restaurant.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) // deduplicate
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return new ArrayList<>();
    }

        // try with resource
    // try() {
    // } catch {
    // }
    // 就不用写finally,自动默认关掉session
    public List<MenuItem> getAllMenuItem(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
