package org.loadtest4j.reporters.slf4j;

import org.junit.Test;
import org.loadtest4j.Result;
import org.loadtest4j.reporters.slf4j.utils.ResultFactory;
import org.loadtest4j.reporters.slf4j.utils.TestResource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class Slf4jReporterTest {

    @Test
    public void shouldReport() {
        // Given
        final List<String> logger = new ArrayList<>();
        final Consumer<Result> sut = new Slf4jReporter(logger::add);

        // When
        sut.accept(ResultFactory.fakeResult());

        // Then
        assertThat(logger)
                .hasSize(1)
                .containsExactly("\n" + TestResource.read("example-report.md") + "\n");
    }
}
