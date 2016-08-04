package andrey019.dao;


import andrey019.model.dao.TodoList;
import andrey019.model.dao.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository("todoListDao")
public class TodoListDaoImpl implements TodoListDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public boolean save(TodoList todoList) {
        try {
            entityManager.persist(todoList);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(TodoList todoList) {
        try {
            todoList = entityManager.merge(todoList);
            entityManager.remove(todoList);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public TodoList getById(long id) {
        return entityManager.find(TodoList.class, id);
    }

    @Override
    public List<TodoList> getByUserId(long id) {
        @SuppressWarnings("unchecked")
        List<TodoList> result = entityManager.createQuery("select c from TodoList c where c.user = :userIdParam")
                .setParameter("userIdParam", id).getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public List<TodoList> getByUsers(long... ids) {
        @SuppressWarnings("unchecked")
        List<TodoList> result = entityManager.createQuery("select list from TodoList list inner join list.users user where user.id in :userIds")
                .setParameter("userIds", ids).getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }
}