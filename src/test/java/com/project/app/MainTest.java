package com.project.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

public class MainTest {

	@Test
	public void validateMainWithTestExample() throws FileNotFoundException, IOException
	{
		TestAppender testAppender = new TestAppender();
		Logger log = Logger.getLogger("com.project.main");
		log.addAppender(testAppender);
		String[] args = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource("input.txt").getFile());
		args = new String[] {inputFile.getAbsolutePath()};
		Main.main(args);
		LoggingEvent loggingEvent = testAppender.events.get(0);
		// check expected log for the first mower
		assertEquals("1 3 N", loggingEvent.getMessage().toString());
		loggingEvent = testAppender.events.get(1);
		// check expected log for the second mower
		assertEquals("5 1 E", loggingEvent.getMessage().toString());
	}

	public static class TestAppender extends AppenderSkeleton{
		public List<LoggingEvent> events = new ArrayList<LoggingEvent>();
		public boolean requiresLayout() {return false;}
		@Override
		protected void append(LoggingEvent event) {
			events.add(event);
		}
		@Override
		public void close() {
			// TODO Auto-generated method stub
		}     
	}
}
