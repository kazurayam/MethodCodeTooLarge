import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

import com.kms.katalon.core.configuration.RunConfiguration

import my.JavapRunner

/**
 * How to run shell command in java : https://www.baeldung.com/run-shell-command-in-java
 */

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path binLibDir = projectDir.resolve('bin/lib')
List<Path> clsFiles = Files.walk(binLibDir)
	.filter { Files.isRegularFile(it) }
	.filter { it.getFileName().toString().startsWith('TempTestCase') }
	.filter { it.getFileName().toString().endsWith('.class') }
	.collect(Collectors.toList())
//println clsFiles

clsFiles.each { f ->
	JavapRunner runner = new JavapRunner(binLibDir, f)
	String output = runner.run()
	println(output)
}
