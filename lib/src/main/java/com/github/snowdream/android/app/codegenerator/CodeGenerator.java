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

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hui.yang on 2014/6/22.
 */
public class CodeGenerator {
    private Class mClass = null;
    private boolean isDebug = true;
    private String path = null;

    public CodeGenerator() {
    }

    public void enableDebug(boolean isDebug) {
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
        mClass = clazz;
        return this;
    }

    public final CodeGenerator addInnerClass(final Class clazz) {
        check();
        mClass.addClass(clazz);
        return this;
    }

    public final CodeGenerator setPath(final String path) {
        check();
        if (path != null) {
            StringBuilder buf = new StringBuilder();
            buf.append(path);
            buf.append(Mark.FILE_SEPERATOR);
            buf.append(mClass.name);
            if (!mClass.name.endsWith(".java")) {
                buf.append(".java");
            }
            this.path = buf.toString();
        }
        return this;
    }

    private void check() {
        if (mClass == null) {
            throw new NullPointerException("The Class is Null.");
        }
    }

    public String generate() {
        check();

        StringBuilder buf = new StringBuilder();
        buf.append(mClass.generate());

        if (isDebug) {
            System.out.print(buf.toString());
        }
        writeToFile(buf.toString());
        return buf.toString();
    }

    public void writeToFile(String str) {
        if (str == null || str == "") {
            Log.w("Warn", "The is no data to write.");
            return;
        }
        File file = null;
        FileOutputStream fop = null;
        try {
            file = new File(path);
            fop = new FileOutputStream(file);

            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            // get the content in bytes
            byte[] contentInBytes = str.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
