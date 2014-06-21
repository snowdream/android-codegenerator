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

/**
 * Created by hui.yang on 2014/6/22.
 */
public class CodeGenerator {
    private Class mClass = null;
    private boolean isDebug = true;

    public CodeGenerator() {
    }
    public void enableDebug(boolean isDebug){
        this.isDebug = isDebug;
    }

    public final CodeGenerator addField(final Field field) {
        check();
        mClass.addField(field);
        return this;
    }

    public final CodeGenerator addMethod(final Method method) {
        check();
        mClass.addMethod(method);
        return this;
    }

    public final CodeGenerator addClass(final Class clazz) {
        mClass= clazz;
        return this;
    }

    public final CodeGenerator addInnerClass(final Class clazz) {
        check();
        mClass.addClass(clazz);
        return this;
    }

    private void check(){
        if (mClass == null){
            throw new NullPointerException("The Class is Null.");
        }
    }

    public String generate() {
        check();

        StringBuilder buf = new StringBuilder();
        buf.append(mClass.generate());

        if (isDebug){
            System.out.print(buf.toString());
        }

        return buf.toString();
    }
}
