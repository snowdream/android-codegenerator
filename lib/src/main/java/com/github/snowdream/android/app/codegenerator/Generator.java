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


import java.lang.reflect.Modifier;

/**
 * Created by hui.yang on 2014/6/22.
 */
public abstract class Generator {
    private int modifiers = 0x00000000;
    protected String name = null;
    protected String type = null;

    private Generator() {
        check();
    }

    public Generator(String name, int modifiers, String type) {
        check();

        this.name = name;
        this.modifiers = modifiers;
        this.type = type;
    }

    protected abstract String generate();

    protected void check() {
        if (name != null && name != "") {
            throw new EmptyException("The name is null or empty.");
        }

        if (type != null && type != "") {
            throw new EmptyException("The type is null or empty.");
        }
    }

    /**
     * Returns a string containing the string representation of all modifiers
     * present in the specified modifiers. Modifiers appear in the order
     * specified by the Java Language Specification.
     */
    protected String generateModifierString() {
        StringBuilder buf = new StringBuilder();
        if (Modifier.isPublic(modifiers)) {
            buf.append("public ");
        }
        if (Modifier.isProtected(modifiers)) {
            buf.append("protected ");
        }
        if (Modifier.isPrivate(modifiers)) {
            buf.append("private ");
        }
        if (Modifier.isAbstract(modifiers)) {
            buf.append("abstract ");
        }
        if (Modifier.isStatic(modifiers)) {
            buf.append("static ");
        }
        if (Modifier.isFinal(modifiers)) {
            buf.append("final ");
        }
        if (Modifier.isTransient(modifiers)) {
            buf.append("transient ");
        }
        if (Modifier.isVolatile(modifiers)) {
            buf.append("volatile ");
        }
        if (Modifier.isSynchronized(modifiers)) {
            buf.append("synchronized ");
        }
        if (Modifier.isNative(modifiers)) {
            buf.append("native ");
        }
        if (Modifier.isStrict(modifiers)) {
            buf.append("strictfp ");
        }
        if (Modifier.isInterface(modifiers)) {
            buf.append("interface ");
        }
        if (buf.length() == 0) {
            return "";
        }
        buf.setLength(buf.length() - 1);
        return buf.toString();
    }
}
