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
public class ClassItem extends Generator {
    private Set<FieldItem> fields = null;
    private Set<MethodItem> methods = null;
    private Set<ClassItem> classes = null;
    private Set<InterfaceItem> interfaces = null;
    private Set<InterfaceItem> aimplements = null;
    private Set<String>  packagenames = null;
    private boolean isInner = false;

    public ClassItem(String name, int modifiers, String type) {
        super(name, modifiers, type);
        check();

        fields = new HashSet<FieldItem>();
        methods = new HashSet<MethodItem>();
        classes = new HashSet<ClassItem>();
        interfaces = new HashSet<InterfaceItem>();
        aimplements = new HashSet<InterfaceItem>();
        packagenames = new HashSet<String>();
    }

    public void addImports(String packagename) {
        packagenames.add(packagename);
    }

    public void addImplement(InterfaceItem ainterface) {
        aimplements.add(ainterface);
    }

    public void addField(FieldItem field) {
        fields.add(field);
    }

    public void addMethod(MethodItem method) {
        methods.add(method);
    }

    public void addClass(ClassItem clazz){
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
        for (FieldItem field: fields){
            if (field == null){
                continue;
            }
            buf.append(field.generate());
            buf.append(Mark.LINE_SEPERATOR);
        }

        //generate get and set method for fileds when the autoCreateGetandSet of it is true.
        for (FieldItem field: fields){
            if (field == null){
                continue;
            }

            if (!field.autoCreateGetandSet){
                continue;
            }


            buf.append(MethodItem.generateGetMethodForField(field));
            buf.append(MethodItem.generateSetMethodForField(field));
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
