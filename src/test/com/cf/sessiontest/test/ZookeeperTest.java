package cf.sessiontest.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZookeeperTest {
    private static String address = "10.10.1.231:2181";
    private static String CHARSET = "utf-8";

    /**
     * 创建永久节点
     */
    @Test
    public void createPersistentNode(){
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));

        try {
            client.start();
            String path = "/cf/PERSISTENT/b";

            client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, "hello, zk".getBytes());
            System.out.println("b");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client!=null)
                client.close();
        }
    }

    /**
     * 创建永久有序节点
     */
    @Test
    public void createPersistentSequentialNode(){
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));

        try {
            client.start();
            String path = "/cf/PERSISTENT_SEQUENTIAL/b";

            client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, "hello, zk".getBytes());
            System.out.println("b");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client!=null)
                client.close();
        }
    }

    /**
     * 创建临时节点
     */
    @Test
    public void createEphemeralNode(){
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));

        try {
            client.start();
            String path = "/cf/EPHEMERAL/b";

            client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, "hello, zk".getBytes());
            System.out.println("b");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client!=null)
                client.close();
        }
    }

    /**
     * 创建临时有序节点
     */
    @Test
    public void createEphemeralSequentialNode(){
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));

        try {
            client.start();
            String path = "/cf/EPHEMERAL_SEQUENTIAL/b";

            for(int i=0;i<10;i++){
                client.create()
                        .creatingParentContainersIfNeeded()
                        .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path, "hello, zk".getBytes());
            }
            System.out.println("b");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client!=null)
                client.close();
        }
    }

    /**
     * 节点监听
     * @throws Exception
     */
    @Test
    public void pathChildrenCache() throws Exception{
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));
        try{
            client.start();
            String path = "/cf/pathChildren/change";

            PathChildrenCache cache = new PathChildrenCache(client, path, true);
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener(new PathChildrenCacheListener() {
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            System.out.println("CHILD_ADDED," + event.getData().getPath());
                            break;
                        case CHILD_UPDATED:
                            System.out.println("CHILD_UPDATED," + event.getData().getPath());
                            break;
                        case CHILD_REMOVED:
                            System.out.println("CHILD_REMOVED," + event.getData().getPath());
                            break;
                        default:
                            break;
                    }
                }
            });
            client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, "hello, zk".getBytes());
            Thread.sleep(1000);
            client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
            Thread.sleep(1000);
            client.delete().forPath(path + "/c1");
            Thread.sleep(Integer.MAX_VALUE);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client!=null)
                client.close();
        }
    }

    @Test
    public void createLock(){

    }

    @Test
    public void test() throws Exception{
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));

        try{
            client.start();

            String path = "/cf/persistent_sequential/b";

            //a. 创建永久性节点
            client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, "hello, zk".getBytes());
            System.out.println("b");

//            //b. 创建临时节点
//            client.create().withMode(CreateMode.EPHEMERAL).forPath("/temptest5", "hello".getBytes());
//
//            System.out.println("c");
//            //c.获取节点值
//            byte[] buf = client.getData().forPath(path);
//            System.out.println("get data path:"+path+", data:"+new String(buf));
//
//            System.out.println("d");
//            //d.设置节点值
//            client.setData().inBackground().forPath(path, "ricky".getBytes());
//
//            System.out.println("e");
//            //e.checkExists
//            Stat stat = client.checkExists().forPath(path);
//            if(stat==null){
//                System.out.println("exec create path:"+path);
//            }else {
//                System.out.println("exec getData");
//            }
//
//            System.out.println("f");
//            //f.删除节点
//            client.delete().deletingChildrenIfNeeded().forPath(path);
//
//            byte[] buf2 = client.getData().forPath(path);
//            System.out.println("get data path:"+path+", data:"+new String(buf2));

        }finally {
            if(client!=null)
                client.close();
        }
    }
}
