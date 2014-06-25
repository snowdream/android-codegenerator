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
public class FieldItem extends Generator {
    protected String value = null;
    protected boolean autoCreateGetandSet = true;

    public FieldItem(String name, int modifiers, String type) {
        super(name, modifiers, type);
        check();
    }

    public FieldItem(String name, int modifiers, String type, String value) {
        super(name, modifiers, type);
        this.value = value;
        check();
    }

    @Override
    public String generate() {
        StringBuilder buf = new StringBuilder();
        buf.append(generateModifierString());
        buf.append(Mark.SPACE);
        buf.append(type);
        buf.append(Mark.SPACE);
        buf.append(name);
        if (value != null && value != "") {
            buf.append(Mark.EQUAL);
            buf.append(value);
        }
        buf.append(Mark.SEMICOLON);
        return buf.toString();
    }

    @Override
    protected void check() {
    }

    public FieldItem createGetAndSetMethod(boolean isAuto) {
        autoCreateGetandSet = isAuto;
        return this;
    }
}
