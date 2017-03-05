package net.amygdalum.gildedroseweb;

import static java.util.Arrays.asList;

import java.nio.file.Paths;
import java.util.List;

import net.amygdalum.testrecorder.DefaultTestRecorderAgentConfig;
import net.amygdalum.testrecorder.ScheduledTestGenerator;
import net.amygdalum.testrecorder.SnapshotConsumer;

public class AgentConfig extends DefaultTestRecorderAgentConfig {

	@Override
	public SnapshotConsumer getSnapshotConsumer() {
		return new ScheduledTestGenerator(getInitializer())
			.withDumpMaximum(100)
			.withDumpOnShutDown(true)
			.withDumpOnCounterInterval(5)
			.withClassName("${class}${counter}Test")
			.withDumpTo(Paths.get("target/generated"));
	}
	
	@Override
	public long getTimeoutInMillis() {
		return 100_000;
	}

	@Override
	public List<String> getPackages() {
		return asList("com.gildedrose");
	}
	
	@Override
	public Class<? extends Runnable> getInitializer() {
		return null;
	}
}
