package com.pressir;

import com.didiglobal.pressir.thrift.Main;
import com.pressir.idl.service.Service;
import org.apache.thrift.transport.TTransportException;
import org.junit.Before;

import java.util.Objects;


public class MainTest {

    private String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
    private String thriftConf = path + "thrift.conf";
    private String paramConf = path + "data.text";

    @Before
    public void before() {
        new Thread(() -> {
            try {
                Service.run();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }).start();
    }

//    @Test(expected = ParameterException.class)
//    public void should_throw_exception_when_no_Duration_given() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
//        String thriftConf = System.getProperty("user.dir") + "/src/test/resources/thrift.yml";
//        String paramConf = System.getProperty("user.dir") + "/src/test/resources/data.text";
//        Main.main("-p", thriftConf, "-d", paramConf, "-u", "127.0.0.1:8090/Soda/getInfos");
//    }
//
//    @Test
//    public void main_test_no_cOrq_exit() {
//        String thriftConf = System.getProperty("user.dir") + "/src/test/resources/thrift.yml";
//        String paramConf = System.getProperty("user.dir") + "/src/test/resources/data.text";
//        Main.main("-D", "10", "-p", thriftConf, "-d", paramConf, "-u", "127.0.0.1:8090/Soda/getInfos");
//    }
//
//    @Test
//    public void main_test_both_cAndq_exit() {
//        String thriftConf = System.getProperty("user.dir") + "/src/test/resources/thrift.yml";
//        String paramConf = System.getProperty("user.dir") + "/src/test/resources/data.text";
//        Main.main("-q", "100", "-c", "1", "-D", "10", "-p", thriftConf, "-d", paramConf, "-u", "127.0.0.1:8090/Soda/getInfos");
//    }

    @org.junit.Test
    public void should_running_when_given_right_params_on_throughtput() {
        Main.main("-q", "100", "-t", "20s", "-e", thriftConf, "-u", "127.0.0.1:8090/Soda/getInfos?@"+paramConf);
    }

    @org.junit.Test
    public void should_running_when_given_right_params_on_concurrency() {
        Main.main("-c", "2", "-t", "20s", "-e", thriftConf, "-u", "127.0.0.1:8090/Soda/getInfos?@"+paramConf);
    }
}
