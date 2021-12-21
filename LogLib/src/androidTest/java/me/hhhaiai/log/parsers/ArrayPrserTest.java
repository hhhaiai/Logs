package me.hhhaiai.log.parsers;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2021/12/21 4:18 PM
 * @author: sanbo
 */
public class ArrayPrserTest extends TestCase {

    public void testParserObject() {
        String[] ss =new String[]{"1","2"};
        int[] si1=new int[]{1,2};
        Arrays.asList(ss);
        Arrays.asList(si1);
    }
}