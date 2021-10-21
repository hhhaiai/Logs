package me.hhhaiai.log.parsers;

import android.content.Intent;

import junit.framework.TestCase;

import org.junit.Assert;

import java.lang.reflect.Field;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2021/10/21 3:14 下午
 * @author: sanbo
 */
public class FieldParserTest extends TestCase {

    public void testParserObject() {
    }

    public void testParserField() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        testBuild();
        testIntent();
    }

    public void testIntent() throws NoSuchFieldException, IllegalAccessException {
        Field[] fs = Intent.class.getDeclaredFields();
        Assert.assertNotNull("Intent类对象获取检测", fs);
        for (Field f : fs) {
            String line = FieldParser.parserField(f);
            System.out.println(line);
            Assert.assertNotNull("Intent单个对象检测:" + f.toGenericString(), line);
        }
    }

    public void testBuild() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Class<?> cz = Class.forName("android.os.Build");
        Assert.assertNotNull("Build类存在检测", cz);
        Field[] fs = cz.getDeclaredFields();
        Assert.assertNotNull("Build类对象获取检测", fs);
        for (Field f : fs) {
            String line = FieldParser.parserField(f);
            System.out.println(line);
            Assert.assertNotNull("Build单个对象检测:" + f.toGenericString(), line);
        }
    }
}