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
    private Set<Interface> interfaces = null;
    private Set<Interface> aimplements = null;
    private Set<String>  packagenames = null;
    private boolean isInner = false;

    public Class(String name, int modifiers, String type) {
        super(name, modifiers, type);
        check();

        fields = new HashSet<Field>();
        methods = new HashSet<Method>();
        classes = new HashSet<Class>();
        interfaces = new HashSet<Interface>();
        aimplements = new HashSet<Interface>();
        packagenames = new HashSet<String>();
    }

    public void addImports(String packagename) {
        packagenames.add(packagename);
    }

    public void addImplement(Interface ainterface) {
        aimplements.add(ainterface);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public void addClass(Class clazz){
        if (clazz != null){
            clazz.setInner(true);
        }
        classes.add(clazz);
    }

    @Override
    public String generate() {
        StringBuilder buf = new StringBuilder();
        //generate imports
        for (String packagename: packagenames ){
            if (packagename == null || packagename ==""){
                continue;
            }
            buf.append("import ");
            buf.append(packagename);
            buf.append(Mark.SEMICOLON);
            buf.append(Mark.RETURN);
        }
       if (!packagenames.isEmpty()){
           buf.append(Mark.LINE_SEPERATOR);
       }

        //generate class
        buf.append(generateModifierString());
        buf.append(Mark.SPACE);
        buf.append(type);
        buf.append(Mark.SPACE);
        buf.append(name);
        buf.append(Mark.LEFT_BRACE);
        buf.append(Mark.LINE_SEPERATOR);

        //generate field
        for (Field field: fields){
            if (field == null){
                continue;
            }
            buf.append(field.generate());
            buf.append(Mark.LINE_SEPERATOR);
        }

        //generate get and set method for fileds when the autoCreateGetandSet of it is true.
        for (Field field: fields){
            if (field == null){
                continue;
            }

            if (!field.autoCreateGetandSet){
                continue;
            }


            buf.append(Method.generateGetMethodForField(field));
            buf.append(Method.generateSetMethodForField(field));
        }

        //generate class(close)
        buf.append(Mark.RIGHT_BRACE);

        return  buf.toString();
    }

    @Override
    protected void check() {
    }

    public boolean isInner() {
        return isInner;
    }

    public void setInner(boolean isInner) {
        this.isInner = isInner;
    }
}
