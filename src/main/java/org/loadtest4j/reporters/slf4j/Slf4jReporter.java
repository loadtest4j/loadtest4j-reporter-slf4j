package org.loadtest4j.reporters.slf4j;

import org.loadtest4j.Diagnostics;
import org.loadtest4j.RequestCount;
import org.loadtest4j.ResponseTime;
import org.loadtest4j.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Report a loadtest4j {@link Result} via SLF4J. (Requires a compatible appender to be present on the classpath).
 */
public class Slf4jReporter implements Consumer<Result> {

    private static final Logger LOG = LoggerFactory.getLogger(Slf4jReporter.class);

    private final Consumer<String> logger;

    Slf4jReporter(Consumer<String> logger) {
        this.logger = logger;
    }

    public Slf4jReporter() {
        this(LOG::info);
    }

    @Override
    public synchronized void accept(Result result) {
        final Diagnostics diagnostics = result.getDiagnostics();

        final long duration = diagnostics.getDuration().toMillis();
        final double rps = diagnostics.getRequestsPerSecond();

        final RequestCount requestCount = diagnostics.getRequestCount();
        final long ok = requestCount.getOk();
        final long ko = requestCount.getKo();
        final long total = requestCount.getTotal();

        final ResponseTime responseTime = result.getResponseTime();
        final long min = responseTime.getPercentile(0).toMillis();
        final long p50 = responseTime.getPercentile(50).toMillis();
        final long p75 = responseTime.getPercentile(75).toMillis();
        final long p90 = responseTime.getPercentile(90).toMillis();
        final long p95 = responseTime.getPercentile(95).toMillis();
        final long p99 = responseTime.getPercentile(99).toMillis();
        final long max = responseTime.getPercentile(100).toMillis();

        final FormattingStringBuilder b = new FormattingStringBuilder();

        b.println();
        b.println("Summary");
        b.println("================================================================================");
        b.println();
        b.println("Global Information");
        b.println("--------------------------------------------------------------------------------");
        b.println();
        b.println("    duration (ms)        %55d", duration);
        b.println("    mean requests/sec    %55f", rps);
        b.println();
        b.println("Request Count");
        b.println("--------------------------------------------------------------------------------");
        b.println();
        b.println("    ok                   %55d", ok);
        b.println("    ko                   %55d", ko);
        b.println("    total                %55d", total);
        b.println();
        b.println("Response Time");
        b.println("--------------------------------------------------------------------------------");
        b.println();
        b.println("    min (ms)             %55d", min);
        b.println("    p50 (ms)             %55d", p50);
        b.println("    p75 (ms)             %55d", p75);
        b.println("    p90 (ms)             %55d", p90);
        b.println("    p95 (ms)             %55d", p95);
        b.println("    p99 (ms)             %55d", p99);
        b.println("    max (ms)             %55d", max);
        b.println();

        logger.accept(b.get());
    }

    private static class FormattingStringBuilder implements Supplier<String> {

        private final StringBuffer sb = new StringBuffer();

        synchronized void println() {
            sb.append("\n");
        }

        synchronized void println(String format, Object... args) {
            sb.append(String.format(format, args));
            println();
        }

        @Override
        public String get() {
            return sb.toString();
        }
    }
}
