package my

import java.nio.file.Path

public class JavapRunner {
	
	private Path dir
	private Path clsFile
	
	JavapRunner(Path dir, Path clsFile) {
		this.dir = dir
		this.clsFile = clsFile	
		println("dir=${dir}")
		println("clsFile=${clsFile}")
	}
	
	String run() {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		ProcessBuilder pb = new ProcessBuilder()
		if (isWindows) {
			pb.command("cmd.exe", "/c", "javap -c ${clsFile.getFileName().toString()}");
		} else {
			println(clsFile.getFileName().toString())
			pb.command("sh", "-c", "javap -c ${clsFile.getFileName().toString()}");
		}
		pb.directory(dir.toFile())
		Process process = pb.start()
		int ret = process.waitFor()
		// print stdout and stderr
		InputStream is = process.getInputStream()
		InputStream es = process.getErrorStream()
		return consumeCommandOutput(is,es)
	}
	
	private String consumeCommandOutput(InputStream stdout, InputStream stderr) {
		StringBuilder sb = new StringBuilder()
		String line = null
		BufferedReader rout = new BufferedReader(
			new InputStreamReader(stdout, "utf-8"))
		while ((line = rout.readLine()) != null) {
			sb.append(line + "\n")
		}
		BufferedReader rerr = new BufferedReader(
			new InputStreamReader(stderr, "utf-8"))
		while ((line = rerr.readLine()) != null) {
			sb.append(line + "\n")
		}
		return sb.toString()
	}
	
}
