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

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hui.yang on 2014/6/22.
 */
public class Class extends Generator {
    private Set<Field> fields = null;
    private Set<Method> methods = null;
    private Set<Class> classes = null;

    public Class(String name) {
        super(name);

        fields = new HashSet<Field>();
        methods = new HashSet<Method>();
        classes = new HashSet<Class>();
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public void addClass(Class clazz){
        classes.add(clazz);
    }

    @Override
    public String generate() {
        check();

        StringBuilder buf = new StringBuilder();

        //generate field
        for (Field field: fields){
            buf.append(field.generate());
            buf.append(Mark.BLANK_LINE);
        }

        return  buf.toString();
    }

    @Override
    protected void check() {
    }
}
