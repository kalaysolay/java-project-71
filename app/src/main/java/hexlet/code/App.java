package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer>{


        @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
        boolean usageHelpRequested;

        @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
        boolean versionInformationRequested;

        @Override
        public Integer call() throws Exception {
            return 0;
        }

        public static void main(String... args) {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
    }
}