/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.nativebinaries.test.tasks

import org.gradle.api.Incubating
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecResult
import org.gradle.process.internal.ExecAction
import org.gradle.process.internal.ExecActionFactory

/**
 * Runs a compiled and installed test executable.
 */
@Incubating
public class RunTestExecutable extends ConventionTask {
    /**
     * The executable binary to run.
     */
    @InputFile File testExecutable

    /**
     * The directory where the results should be generated.
     */
    @OutputDirectory File outputDir

    private ExecAction execAction;
    private ExecResult execResult;

    public RunTestExecutable() {
        execAction = getServices().get(ExecActionFactory.class).newExecAction();
    }

    @TaskAction
    void exec() {
        // TODO:DAZ Inspect the exit value and report accordingly (with link to outputs)
        execAction.setExecutable(getTestExecutable())
        execAction.setWorkingDir(getOutputDir())
        execResult = execAction.execute();
    }
}
