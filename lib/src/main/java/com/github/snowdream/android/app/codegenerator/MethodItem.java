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
public class MethodItem extends Generator {

    public MethodItem(String name, int modifiers, String type) {
        super(name, modifiers, type);
        check();
    }

    @Override
    public String generate() {
        return null;
    }

    @Override
    protected void check() {
    }

    public static final String generateGetMethodForField(FieldItem field) {
        StringBuilder buf = new StringBuilder();
        buf.append("public");
        buf.append(Mark.SPACE);
        buf.append(field.type);
        buf.append(Mark.SPACE);
        buf.append("get");
        buf.append(StringUtil.toUpperCaseFirstOne(field.name));
        buf.append("()");
        buf.append(Mark.LEFT_BRACE);
        buf.append(Mark.RETURN);
        buf.append(Mark.TAB);
        buf.append("return this.");
        buf.append(field.name);
        buf.append(Mark.SEMICOLON);
        buf.append(Mark.RETURN);
        buf.append(Mark.RIGHT_BRACE);
        return buf.toString();
    }

    public static final String generateSetMethodForField(FieldItem field) {
        StringBuilder buf = new StringBuilder();
        buf.append("public");
        buf.append(Mark.SPACE);
        buf.append("void");
        buf.append(Mark.SPACE);
        buf.append("set");
        buf.append(StringUtil.toUpperCaseFirstOne(field.name));
        buf.append("(");
        buf.append(field.type);
        buf.append(Mark.SPACE);
        buf.append(field.name);
        buf.append(")");
        buf.append(Mark.LEFT_BRACE);
        buf.append(Mark.RETURN);
        buf.append(Mark.TAB);
        buf.append("this.");
        buf.append(field.name);
        buf.append(Mark.EQUAL);
        buf.append(field.name);
        buf.append(Mark.SEMICOLON);
        buf.append(Mark.RETURN);
        buf.append(Mark.RIGHT_BRACE);
        return buf.toString();
    }
}
