import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CompetitionWinner from './competition-winner';
import CompetitionWinnerDetail from './competition-winner-detail';
import CompetitionWinnerUpdate from './competition-winner-update';
import CompetitionWinnerDeleteDialog from './competition-winner-delete-dialog';

const CompetitionWinnerRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CompetitionWinner />} />
    <Route path="new" element={<CompetitionWinnerUpdate />} />
    <Route path=":id">
      <Route index element={<CompetitionWinnerDetail />} />
      <Route path="edit" element={<CompetitionWinnerUpdate />} />
      <Route path="delete" element={<CompetitionWinnerDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CompetitionWinnerRoutes;
