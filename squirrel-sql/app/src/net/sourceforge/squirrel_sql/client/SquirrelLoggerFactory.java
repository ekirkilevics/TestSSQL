package net.sourceforge.squirrel_sql.client;
/*
 * Copyright (C) 2001 Colin Bell
 * colbell@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;

import net.sourceforge.squirrel_sql.fw.util.log.ILogger;
import net.sourceforge.squirrel_sql.fw.util.log.ILoggerFactory;
import net.sourceforge.squirrel_sql.fw.util.log.Log4jLogger;
//import net.sourceforge.squirrel_sql.fw.util.log.LoggerController;

public class SquirrelLoggerFactory implements ILoggerFactory {
//	private static ILogger s_log = LoggerController.createLogger(SquirrelLoggerFactory.class);

	public SquirrelLoggerFactory(IApplication app) {
		super();
		String logFileName = app.getApplicationFiles().getExecutionLogFile().getPath();
		Category.getRoot().removeAllAppenders();
		try {
			FileAppender fa = new FileAppender(new PatternLayout("%-4r [%t] %-5p %c %x - %m%n"/*PatternLayout.TTCC_CONVERSION_PATTERN*/), logFileName);
			fa.setFile(logFileName);
			BasicConfigurator.configure(fa);
		} catch (IOException ex) {
			BasicConfigurator.configure();
		}
	}

	public ILogger createLogger(Class clazz) {
		return new MyLogger(clazz);
	}

	private final static class MyLogger extends Log4jLogger {
		MyLogger(Class clazz) {
			super(clazz);
		}
	}
}
