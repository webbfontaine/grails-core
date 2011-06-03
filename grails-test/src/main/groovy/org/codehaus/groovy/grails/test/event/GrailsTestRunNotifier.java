/*
 * Copyright 2011 SpringSource
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
package org.codehaus.groovy.grails.test.event;

import org.codehaus.groovy.grails.cli.logging.GrailsConsole;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;

/**
 * A RunNotifier that logs the the GrailsConsole
 *
 * @author Graeme Rocher
 * @since 1.4
 */
public class GrailsTestRunNotifier extends RunNotifier{

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    int progress = 0;
    int total;
    private GrailsConsole console = GrailsConsole.getInstance();

    public GrailsTestRunNotifier(int total) {
        this.total = total;
    }

    @Override
    public void fireTestRunFinished(Result result) {
        super.fireTestRunFinished(result);
    }

    @Override
    public void fireTestStarted(Description description) throws StoppedByUserException {
        console.indicateProgress(++progress, total);
        super.fireTestStarted(description);
    }

    @Override
    public void fireTestFailure(Failure failure) {
        console.error("FAILURE: " + failure.getDescription().getDisplayName());
        console.error(failure.getMessage());
        super.fireTestFailure(failure);
    }
}
