/*
 * Copyright (C) 2014 Snowdream Mobile <yanghui1986527@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.snowdream.android.app.codegenerator;

import junit.framework.TestCase;

import java.lang.reflect.Modifier;

/**
 * Created by hui.yang on 2014/6/22.
 */
public class CodeGeneratorTest extends TestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGenerateField() throws Exception {
        String str = null;
        ClassItem clazz = new ClassItem("com.example.demo","KV_", Modifier.PUBLIC,"class");
        clazz.addCopyright("/** Automatically generated file. DO NOT MODIFY */");
        clazz.addImport("android.content.SharedPreferences");
        clazz.addImport("android.content.SharedPreferences.Editor");
        clazz.addImport("com.autonavi.common.CC");
        clazz.addImport("com.example.demo.model.TaskConfig");

        ClassItem innerclazz = new ClassItem("g0", Modifier.PUBLIC | Modifier.STATIC,"TaskConfig");
        FieldItem fieldItem = new FieldItem("sp",Modifier.PROTECTED,"SharedPreferences","CC.getApplication().getSharedPreferences(\"config\", 0)");
        fieldItem.generateGetandSetMethod(true);
        innerclazz.addField(fieldItem);

        FieldItem fieldItem1 = new FieldItem("editor",Modifier.PRIVATE,"Editor");
        innerclazz.addField(fieldItem1);
        clazz.addClass(innerclazz);

        CodeGenerator generator = new CodeGenerator();
        generator.addClass(clazz);
        generator.setPath("/sdcard/snowdream");
        str = generator.generate();
        assertNotNull(str);
    }
}
