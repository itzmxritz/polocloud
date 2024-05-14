package dev.httpmarco.polocloud.runner;

import dev.httpmarco.polocloud.runner.loader.CloudClassLoader;
import dev.httpmarco.polocloud.runner.impl.CloudBaseRunner;
import dev.httpmarco.polocloud.runner.impl.CloudInstanceRunner;
import lombok.SneakyThrows;

import java.util.Arrays;

public class RunnerBoostrap {

    public static CloudRunner RUNNER;
    public static final CloudClassLoader LOADER = new CloudClassLoader();

    @SneakyThrows
    public static void main(String[] args) {
        RUNNER = Arrays.asList(args).contains("--instance") ? new CloudInstanceRunner() : new CloudBaseRunner();

        Thread.currentThread().setContextClassLoader(LOADER);

        // clone needed runtime files
        RUNNER.run();

        Class.forName(RUNNER.mainEntry(), true, LOADER).getMethod("main", String[].class).invoke(null, (Object) args);
    }
}
