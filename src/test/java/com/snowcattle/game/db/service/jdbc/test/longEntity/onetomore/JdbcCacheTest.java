package com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore;

import com.snowcattle.game.db.service.jdbc.entity.MoreOrder;
import com.snowcattle.game.db.service.jdbc.service.entity.impl.MoreOrderService;
import com.snowcattle.game.db.service.jdbc.test.TestConstants;
import com.snowcattle.game.db.service.proxy.EntityProxyFactory;
import com.snowcattle.game.db.service.proxy.EntityServiceProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangwenping on 17/3/20.
 */
public class JdbcCacheTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"bean/*.xml"});
        MoreOrderService moreOrderService = getMoreOrderProxyService(classPathXmlApplicationContext);
        insertTest(classPathXmlApplicationContext, moreOrderService);
        insertBatchTest(classPathXmlApplicationContext, moreOrderService);
        MoreOrder order = getTest(classPathXmlApplicationContext, moreOrderService);
        List<MoreOrder> orderList = getOrderList(classPathXmlApplicationContext, moreOrderService);
        updateTest(classPathXmlApplicationContext, moreOrderService, order);
        updateBatchTest(classPathXmlApplicationContext, moreOrderService, orderList);
        deleteTest(classPathXmlApplicationContext, moreOrderService, order);
        deleteBatchTest(classPathXmlApplicationContext, moreOrderService, orderList);
        getBatchOrderList(classPathXmlApplicationContext, moreOrderService);

    }

    public static void deleteBatchTest(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService orderService, List<MoreOrder> orderList) throws Exception {
        com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.deleteBatchTest(classPathXmlApplicationContext, orderService, orderList);
    }

    public static List<MoreOrder> getBatchOrderList(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService moreOrderService) throws Exception {
        EntityProxyFactory entityProxyFactory = (EntityProxyFactory) classPathXmlApplicationContext.getBean("entityProxyFactory");
        MoreOrder moreOrder = new MoreOrder();
        MoreOrder proxyOrder = entityProxyFactory.createProxyEntity(moreOrder);
//        MoreOrder proxyOrder = moreOrder;
        proxyOrder.setUserId(TestConstants.userId);
        proxyOrder.setStatus("测试列表插入" + com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.batchStart);
//        MoreOrderService moreOrderService = getMoreOrderProxyService(classPathXmlApplicationContext);
        List<MoreOrder> orderList = moreOrderService.getEntityList(proxyOrder);
        System.out.println(orderList);
        return orderList;
    }

    public static void updateBatchTest(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService orderService, List<MoreOrder> orderList) throws Exception {
        com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.updateBatchTest(classPathXmlApplicationContext, orderService, orderList);
    }

    public static void insertBatchTest(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService moreOrderService) throws Exception {
        int startSize = com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.batchStart;
        int endSize = startSize + 10;
        List<MoreOrder> list = new ArrayList<>();
        for (int i = startSize; i < endSize; i++) {
            MoreOrder order = new MoreOrder();
            order.setUserId(TestConstants.userId);
            order.setId((long)i);
            order.setStatus("测试列表插入" + i);
            list.add(order);
        }

        moreOrderService.insertEntityBatch(list);
    }

    public static List<MoreOrder> getOrderList(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService moreOrderService) throws Exception {
        return com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.getMoreOrderList(classPathXmlApplicationContext, moreOrderService);
    }

    public static void insertTest(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService moreOrderService) {
        com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.insertTest(classPathXmlApplicationContext, moreOrderService);
    }

    public static MoreOrder getTest(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService moreOrderService) {
       return com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.getTest(classPathXmlApplicationContext, moreOrderService);
    }

    public static void updateTest(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService moreOrderService, MoreOrder moreOrder) throws Exception {
       com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.updateTest(classPathXmlApplicationContext, moreOrderService, moreOrder);
    }

    public static void deleteTest(ClassPathXmlApplicationContext classPathXmlApplicationContext, MoreOrderService moreOrderService, MoreOrder moreOrder) throws Exception {
      com.snowcattle.game.db.service.jdbc.test.longEntity.onetomore.JdbcTest.deleteTest(classPathXmlApplicationContext, moreOrderService, moreOrder);
    }

    public static MoreOrderService getMoreOrderProxyService(ClassPathXmlApplicationContext classPathXmlApplicationContext) throws Exception {
        MoreOrderService moreOrderService = (MoreOrderService) classPathXmlApplicationContext.getBean("moreOrderService");
        EntityServiceProxyFactory entityServiceProxyFactory = (EntityServiceProxyFactory) classPathXmlApplicationContext.getBean("entityServiceProxyFactory");
        moreOrderService = entityServiceProxyFactory.createProxyService(moreOrderService);
        return moreOrderService;
    }
}
