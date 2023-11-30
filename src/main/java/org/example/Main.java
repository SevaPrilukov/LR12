package org.example;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.concurrent.CountDownLatch;
import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(items.class)
                .configure("hibernate_configuration.xml")
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            for (int i = 0; i < 40; i++) {
                items items = new items();
                session.save(items);
            }
            session.getTransaction().commit();


            CountDownLatch latch = new CountDownLatch(8);
            for (int i = 0; i < 8; i++) {
                new Thread(() -> Threading_PESSIMISTIC(factory, latch)).start();
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            session = factory.getCurrentSession();
            session.beginTransaction();
            Long totalVal = (Long) session.createQuery("SELECT SUM(val) FROM items").uniqueResult();
            System.out.println("Сумма: " + totalVal);
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
        long endTime = System.currentTimeMillis();
        //Object o = session.createNativeQuery("SELECT SUM(val) FROM ITEMS;").getSingleResult();
        System.out.println("Время выполнения: " + (endTime - startTime) / 1000.0 + " секунд");
    }

    private static void Threading_PESSIMISTIC(SessionFactory factory, CountDownLatch latch) {
        System.out.println("Поток начинается: " + Thread.currentThread().getName());

        for (int i = 0; i < 20000; i++) {
            Session session = factory.getCurrentSession();
            Long rnd = ((long)(Math.random() * 40) + 1);

            try {
                session.beginTransaction();
                items items = session.find(items.class, rnd, LockModeType.PESSIMISTIC_WRITE);
                items.setVal(items.getVal() + 1);
                sleep(5);
                session.save(items);
                session.getTransaction().commit();
            } catch (HibernateException | OptimisticLockException | InterruptedException e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
//            synchronized (Main.class) {
//                count++;
//            }
        }
        System.out.println("Поток закончид работу: " + Thread.currentThread().getName());
        latch.countDown();
    }
}
