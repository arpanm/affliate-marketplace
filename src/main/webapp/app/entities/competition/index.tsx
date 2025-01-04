import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Competition from './competition';
import CompetitionDetail from './competition-detail';
import CompetitionUpdate from './competition-update';
import CompetitionDeleteDialog from './competition-delete-dialog';

const CompetitionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Competition />} />
    <Route path="new" element={<CompetitionUpdate />} />
    <Route path=":id">
      <Route index element={<CompetitionDetail />} />
      <Route path="edit" element={<CompetitionUpdate />} />
      <Route path="delete" element={<CompetitionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CompetitionRoutes;
