package net.sourceforge.squirrel_sql.client.session.objectstree.objectpanel;
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
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;

import net.sourceforge.squirrel_sql.fw.datasetviewer.DataSetException;
import net.sourceforge.squirrel_sql.fw.datasetviewer.DataSetViewerTablePanel;
import net.sourceforge.squirrel_sql.fw.datasetviewer.DataSetViewer;
import net.sourceforge.squirrel_sql.fw.datasetviewer.IDataSetViewerDestination;
import net.sourceforge.squirrel_sql.fw.datasetviewer.ResultSetDataSet;
import net.sourceforge.squirrel_sql.fw.util.log.ILogger;
import net.sourceforge.squirrel_sql.fw.util.log.LoggerController;

import net.sourceforge.squirrel_sql.client.session.ISession;

public class ResultSetPanel extends JScrollPane {
	/** Logger for this class. */
	private static ILogger s_log = LoggerController.createLogger(ResultSetPanel.class);

	private boolean _fullyCreated = false;
	private ResultSetDataSet _ds;
	private DataSetViewer _viewer;

	public void load(ISession session, ResultSet rs, int[] cols,
						String destClassName) {
		try {
			// Lazily create the user interface.
			if (!_fullyCreated) {
				createUserInterface(session, destClassName);
				_fullyCreated = true;
			}

			try {
				_ds.setResultSet(rs, cols);
				_viewer.show(_ds);
			} finally {
				rs.close();
			}

		} catch (Exception ex) {
			s_log.error("Error", ex);
		}
	}

	private void createUserInterface(ISession session, String destClassName)
			throws DataSetException {
		_viewer = new DataSetViewer();
		_viewer.setDestination(destClassName);
		_ds = new ResultSetDataSet();
		setViewportView(_viewer.getDestinationComponent());
	}
}

