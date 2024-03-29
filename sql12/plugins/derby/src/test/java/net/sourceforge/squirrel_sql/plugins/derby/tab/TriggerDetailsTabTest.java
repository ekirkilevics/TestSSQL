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
package net.sourceforge.squirrel_sql.plugins.derby.tab;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;

import net.sourceforge.squirrel_sql.client.IApplication;
import net.sourceforge.squirrel_sql.client.session.ISession;
import net.sourceforge.squirrel_sql.client.session.SessionManager;
import net.sourceforge.squirrel_sql.client.session.mainpanel.objecttree.tabs.AbstractBasePreparedStatementTabTest;
import net.sourceforge.squirrel_sql.fw.id.IIdentifier;
import net.sourceforge.squirrel_sql.fw.sql.IDatabaseObjectInfo;
import net.sourceforge.squirrel_sql.fw.sql.ISQLConnection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TriggerDetailsTabTest extends AbstractBasePreparedStatementTabTest
{
	
	private ISession mockSession = Mockito.mock(ISession.class);
	private IIdentifier mockSessionIdentifier = Mockito.mock(IIdentifier.class);
	private IApplication mockApplication = Mockito.mock(IApplication.class);
	private SessionManager mockSessionManager = Mockito.mock(SessionManager.class);
	private ISQLConnection mockSQLConnection = Mockito.mock(ISQLConnection.class);
	private IDatabaseObjectInfo mockDatabaseObjectInfo = Mockito.mock(IDatabaseObjectInfo.class);
	private PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
	
	@Before
	public void setUp() throws Exception
	{
		classUnderTest = new TriggerDetailsTab();
		
		when(mockSession.getIdentifier()).thenReturn(mockSessionIdentifier);
		when(mockSession.getApplication()).thenReturn(mockApplication);
		when(mockSession.getSQLConnection()).thenReturn(mockSQLConnection);
		when(mockApplication.getSessionManager()).thenReturn(mockSessionManager);
		when(mockSessionManager.getSession(mockSessionIdentifier)).thenReturn(mockSession);
		when(mockSQLConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		
	}

	@Test
	public void testCreateStatement() throws Exception 
	{
		TriggerDetailsTab tabToTest = new TriggerDetailsTab(); 	
		tabToTest.setSession(mockSession);
		tabToTest.setDatabaseObjectInfo(mockDatabaseObjectInfo);
		PreparedStatement result = tabToTest.createStatement();
		assertEquals(mockPreparedStatement, result);
	}
	
}
