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

import android.test.AndroidTestCase;

import java.lang.reflect.Modifier;

/**
 * Created by hui.yang on 2014/6/22.
 */
public class CodeGeneratorTest extends AndroidTestCase {
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
        FieldItem field = new FieldItem("isGood", Modifier.PUBLIC,"boolean","false");
        field.createGetAndSetMethod(true);
        ClassItem clazz = new ClassItem("Love", Modifier.PUBLIC,"class");
        clazz.addImports("com.github.snowdream.android.app.codegenerator");
        clazz.addField(field);

        CodeGenerator generator = new CodeGenerator();
        generator.addClass(clazz);
        generator.setPath("/sdcard/snowdream");
        str = generator.generate();
        assertNotNull(str);
    }
}
