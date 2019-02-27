package com.cf.sessiontest.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8  -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM1 {
    static class OOMObject {

    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<HeapOOM1.OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
