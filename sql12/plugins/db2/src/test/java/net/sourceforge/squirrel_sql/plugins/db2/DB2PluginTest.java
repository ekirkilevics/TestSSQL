/*
 * Copyright (C) 2008 Rob Manning
 * manningr@users.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.squirrel_sql.plugins.db2;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import net.sourceforge.squirrel_sql.client.plugin.AbstractSessionPluginTest;
import net.sourceforge.squirrel_sql.client.plugin.DatabaseProductVersionData;
import net.sourceforge.squirrel_sql.client.plugin.IPlugin;
import net.sourceforge.squirrel_sql.client.plugin.PluginSessionCallback;

import org.junit.Test;

public class DB2PluginTest extends AbstractSessionPluginTest implements DatabaseProductVersionData
{

	@Override
	protected IPlugin getPluginToTest() throws Exception
	{
		return new DB2Plugin();
	}

	@Override
	protected String getDatabaseProductName()
	{
		return "DB2";
	}

	@Override
	protected String getDatabaseProductVersion()
	{
		return null;
	}

	@Test
	public void testSessionStarted() throws SQLException
	{
		when(mockDatabaseMetaData.getDriverName()).thenReturn(DB2Plugin.JCC_DRIVER_NAME[0]);
		PluginSessionCallback result = ((DB2Plugin) super.classUnderTest).sessionStarted(mockSession);
		assertNotNull(result);
	}

}
